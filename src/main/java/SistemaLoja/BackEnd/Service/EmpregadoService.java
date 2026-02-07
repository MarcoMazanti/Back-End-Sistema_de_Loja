package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Login;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TrocarSenha;
import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Exception.*;
import SistemaLoja.BackEnd.Repository.FilialRepository;
import SistemaLoja.BackEnd.Service.Interface.RequestRequerinteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static SistemaLoja.BackEnd.Security.GerarSenhaSaltHash.encriptarSenha;
import static SistemaLoja.BackEnd.Security.GerarSenhaSaltHash.validarSenha;

@Service
public class EmpregadoService extends ServiceAbstract<Empregado> implements RequestRequerinteInterface<Empregado> {
    @Autowired
    private FilialRepository filialRepository;

    @Override
    public List<Empregado> listar() {
        List<Empregado> empregadoList = empregadoRepository.findAll();

        if (empregadoList.isEmpty()) throw new TabelaVaziaException("Tabela Empregado Vazia!");
        return empregadoList;
    }

    @Override
    public Empregado buscarPorId(Integer id) {
        Optional<Empregado> empregadoOptional = empregadoRepository.findById(id);

        if (empregadoOptional.isEmpty()) throw new RegistroInexistenteException("Registro de Empregado não encontrado!");
        return empregadoOptional.get();
    }

    public List<Empregado> listarPorFilialId(Integer filialId) {
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

    public void trocarSenha(TrocarSenha trocarSenha) {
        Empregado empregado = empregadoRepository.findByCpf(trocarSenha.cpf())
                .orElseThrow(() -> new RegistroInexistenteException("Empregado não encontrado!"));

        boolean dadosConferem = Objects.equals(empregado.getCpf(), trocarSenha.cpf()) &&
                Objects.equals(empregado.getEmail(), trocarSenha.email());

        if (!dadosConferem) throw new AtualizacaoNaoPermitidaException("Credenciais (CPF ou Email) inválidas para esta conta!");

        empregado.setSenha(encriptarSenha(trocarSenha.senha()));

        empregadoRepository.save(empregado);
    }

    @Override
    public Empregado salvar(Integer idRequerinte, Empregado empregado) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Empregado> optionalEmpregado = empregadoRepository.findByCpf(empregado.getCpf());

        if (optionalEmpregado.isPresent()) throw new RegistroJaExistenteException("Já possui um empregado com este CPF cadastrado!");

        empregado.setSenha(encriptarSenha(empregado.getSenha()));

        // Adicionar o empregado na respectiva filial
        Optional<Filial> optionalFilial = filialRepository.findById(empregado.getFilialId());

        if (optionalFilial.isEmpty()) throw new RegistroInexistenteException("Não possui uma conta da filial cadastrada!");

        Filial filial = optionalFilial.get();
        filial.setQuantEmpregados(filial.getQuantEmpregados() + 1);

        filialRepository.save(filial);
        return empregadoRepository.save(empregado);
    }

    @Override
    public Empregado atualizar(Integer idRequerinte, Empregado empregado) {
        if (idRequerinte != empregado.getId()) {
            verificarPermissaoRequerinte(idRequerinte);
        } else {
            verificarAlteracaoContaPropria(idRequerinte, empregado);
        }

        Optional<Empregado> optionalEmpregado = empregadoRepository.findByCpf(empregado.getCpf());

        if (optionalEmpregado.isEmpty()) throw new RegistroInexistenteException("Não possui uma conta de empregado cadastrado!");

        if (!optionalEmpregado.get().equals(empregado)) throw new AtualizacaoNaoPermitidaException("Não pode alterar os campos de CPF e ID!");

        return empregadoRepository.save(empregado);
    }

    @Override
    public void remover(Integer idRequerinte, Integer id) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(id);

        if (optionalEmpregado.isEmpty()) throw new RegistroInexistenteException("Não possui uma conta de empregado cadastrado!");

        // Alterar a quantidade de funcionários na respectiva filial
        Optional<Filial> optionalFilial = filialRepository.findById(optionalEmpregado.get().getFilialId());

        if (optionalFilial.isEmpty()) throw new RegistroInexistenteException("Não possui uma conta da filial cadastrada!");

        Filial filial = optionalFilial.get();
        filial.setQuantEmpregados(filial.getQuantEmpregados() - 1);

        filialRepository.save(filial);
        empregadoRepository.deleteById(id);
    }

    private void verificarAlteracaoContaPropria(Integer idRequerinte, Empregado empregadoAtualizar) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(idRequerinte);

        if (optionalEmpregado.isEmpty()) throw new RegistroInexistenteException("Não foi encontrado a conta do Requerinte!");
        Empregado empregado = optionalEmpregado.get();

        if (!empregado.alteracaoPropria(empregadoAtualizar)) throw new AtualizacaoNaoPermitidaException("Não pode alterar dados fundamentais da própria conta!");
    }
}
