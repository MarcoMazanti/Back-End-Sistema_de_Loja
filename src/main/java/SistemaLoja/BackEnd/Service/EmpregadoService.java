package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Login;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TipoCargo;
import SistemaLoja.BackEnd.Exception.*;
import SistemaLoja.BackEnd.Repository.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static SistemaLoja.BackEnd.Security.GerarSenhaSaltHash.encriptarSenha;
import static SistemaLoja.BackEnd.Security.GerarSenhaSaltHash.validarSenha;

@Service
public class EmpregadoService {
    @Autowired
    private EmpregadoRepository empregadoRepository;

    public List<Empregado> coletarTodosEmpregados() {
        List<Empregado> empregadoList = empregadoRepository.findAll();

        if (empregadoList.isEmpty()) throw new TabelaVaziaException("Tabela Empregado Vazia!");

        return empregadoList;
    }

    public Empregado coletarEmpregadoById(Integer id) {
        Optional<Empregado> empregadoOptional = empregadoRepository.findById(id);

        if (empregadoOptional.isEmpty()) throw new RegistroInexistenteException("Registro de Empregado não encontrado!");

        return empregadoOptional.get();
    }

    public List<Empregado> coletarAllEmpregadoByFilialId(Integer filialId) {
        List<Empregado> empregadoList = empregadoRepository.findAllByFilialId(filialId);

        if (empregadoList.isEmpty()) throw new TabelaVaziaException("Não foi encontrado nenhum empregado desta filial!");

        return empregadoList;
    }

    public Empregado efetuarLogin(Login login) {
        Optional<Empregado> empregadoOptional = empregadoRepository.findByCpf(login.cpf());

        if (empregadoOptional.isEmpty()) throw new RegistroInexistenteException("Não foi encontrado o empregado com base no CPF!");

        if (validarSenha(login.senha(), empregadoOptional.get().getSenha())) return empregadoOptional.get();
        throw new LoginNaoAutorizadoException("Não foi autorizado o login!");
    }

    public Empregado postarNovoEmpregado(Integer idRequerinte, Empregado empregado) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Empregado> optionalEmpregado = empregadoRepository.findByCpf(empregado.getCpf());

        if (optionalEmpregado.isPresent()) throw new RegistroJaExistenteException("Já possui um empregado com este CPF cadastrado!");

        empregado.setSenha(encriptarSenha(empregado.getSenha()));

        return empregadoRepository.save(empregado);
    }

    public Empregado atualizarEmpregado(Integer idRequerinte, Empregado empregado) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Empregado> optionalEmpregado = empregadoRepository.findByCpf(empregado.getCpf());

        if (optionalEmpregado.isEmpty()) throw new RegistroInexistenteException("Não possui uma conta de empregado cadastrado!");

        if (!optionalEmpregado.get().equals(empregado)) throw new AtualizacaoNaoPermitidaException("Não pode alterar os campos de CPF e ID!");

        // Atualiza se for inserir uma nova senha
        if (!Objects.equals(empregado.getSenha(), optionalEmpregado.get().getSenha())) empregado.setSenha(encriptarSenha(empregado.getSenha()));

        return empregadoRepository.save(empregado);
    }

    public String deletarEmpregar(Integer idRequerinte, Integer id) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(id);

        if (optionalEmpregado.isEmpty()) throw new RegistroInexistenteException("Não possui uma conta de empregado cadastrado!");

        empregadoRepository.deleteById(id);
        return "Deletado!";
    }

    private void verificarPermissaoRequerinte(Integer idRequerinte) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(idRequerinte);

        if (optionalEmpregado.isEmpty()) throw  new RegistroInexistenteException("Não foi encontrado o registro do requerinte!");
        if (optionalEmpregado.get().getCargo().equals(TipoCargo.EMPREGADO)) throw new RequerinteNaoAutorizadoException("Conta Requerinte sem permissão!");
    }
}
