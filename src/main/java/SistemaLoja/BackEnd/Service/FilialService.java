package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TipoCargo;
import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Exception.RegistroInexistenteException;
import SistemaLoja.BackEnd.Exception.RegistroJaExistenteException;
import SistemaLoja.BackEnd.Exception.RequerinteNaoAutorizadoException;
import SistemaLoja.BackEnd.Exception.TabelaVaziaException;
import SistemaLoja.BackEnd.Repository.EmpregadoRepository;
import SistemaLoja.BackEnd.Repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialService {
    @Autowired
    private FilialRepository filialRepository;
    @Autowired
    private EmpregadoRepository empregadoRepository;

    public List<Filial> obterTodasFiliais() {
        List<Filial> listaFiliais = filialRepository.findAll();

        if (listaFiliais.isEmpty()) throw new TabelaVaziaException("Tabela Filial está vazia!");
        return listaFiliais;
    }

    public Filial publicarNovaFilial(Integer idRequerinte, Filial filial) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Filial> optionalFilial = filialRepository.findByCnpj(filial.getCnpj());
        if (optionalFilial.isPresent()) throw new RegistroJaExistenteException(("Registro de Filial já existente!"));

        return filialRepository.save(filial);
    }

    public Filial atualizarFilial(Integer idRequerinte, Filial filial) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Filial> optionalFilial = filialRepository.findById(filial.getId());

        if (optionalFilial.isEmpty()) throw new RegistroInexistenteException("Endpoint apenas para atualização e a filial enviada é inexistente!");

        return filialRepository.save(filial);
    }

    public void deletarFilial(Integer idRequerinte, Filial filial) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Filial> optionalFilial = filialRepository.findById(filial.getId());

        if (optionalFilial.isEmpty()) throw new RegistroInexistenteException("Endpoint apenas para deletar e a filial enviada já é inexistente!");

        filialRepository.delete(filial);
    }

    private void verificarPermissaoRequerinte(Integer idRequerinte) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(idRequerinte);

        if (optionalEmpregado.isEmpty()) throw new RegistroInexistenteException("Impossível acessar conta de empregado com base no idRequerinte!");
        if (!optionalEmpregado.get().getCargo().equals(TipoCargo.DONO)) throw new RequerinteNaoAutorizadoException("Conta Requerinte sem permissão para criar nova filial!");
    }
}
