package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TipoCargo;
import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Exception.*;
import SistemaLoja.BackEnd.Repository.FilialRepository;
import SistemaLoja.BackEnd.Service.Interface.RequestRequerinteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialService extends ServiceAbstract<Filial> implements RequestRequerinteInterface<Filial> {
    @Autowired
    private FilialRepository filialRepository;

    @Override
    public List<Filial> listar() {
        List<Filial> listaFiliais = filialRepository.findAll();

        if (listaFiliais.isEmpty()) throw new TabelaVaziaException("Tabela Filial está vazia!");
        return listaFiliais;
    }

    @Override
    public Filial buscarPorId(Integer id) {
        Optional<Filial> optionalFilial = filialRepository.findById(id);

        if (optionalFilial.isEmpty()) throw new RegistroInexistenteException("Registro Filial não encontrado pelo ID: " + id);
        return optionalFilial.get();
    }

    @Override
    public Filial salvar(Integer idRequerinte, Filial filial) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Filial> optionalFilial = filialRepository.findByCnpj(filial.getCnpj());
        if (optionalFilial.isPresent()) throw new RegistroJaExistenteException("Registro de Filial já existente!");

        return filialRepository.save(filial);
    }

    @Override
    public Filial atualizar(Integer idRequerinte, Filial filial) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Filial> optionalFilial = filialRepository.findById(filial.getId());

        if (optionalFilial.isEmpty()) throw new RegistroInexistenteException("Endpoint apenas para atualização e a filial enviada é inexistente!");
        Filial filialAntiga = optionalFilial.get();

        if (!filialAntiga.equals(filial)) throw new AtualizacaoNaoPermitidaException("Não é permitido alterar campos UNIQUE!");

        return filialRepository.save(filial);
    }

    @Override
    public void remover(Integer idRequerinte, Integer id) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Filial> optionalFilial = filialRepository.findById(id);
        if (optionalFilial.isEmpty()) throw new RegistroInexistenteException("Endpoint apenas para deletar e a filial enviada já é inexistente!");

        filialRepository.deleteById(id);
    }

    @Override
    protected void verificarPermissaoRequerinte(Integer idRequerinte) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(idRequerinte);

        if (optionalEmpregado.isEmpty()) throw new RegistroInexistenteException("Impossível acessar conta de empregado com base no idRequerinte!");
        if (!optionalEmpregado.get().getCargo().equals(TipoCargo.DONO)) throw new RequerinteNaoAutorizadoException("Conta Requerinte sem permissão para criar nova filial!");
    }
}
