package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TipoCargo;
import SistemaLoja.BackEnd.Exception.RegistroInexistenteException;
import SistemaLoja.BackEnd.Exception.RequerenteNaoAutorizadoException;
import SistemaLoja.BackEnd.Repository.EmpregadoRepository;
import SistemaLoja.BackEnd.Service.Interface.SimpleRequestInterface;
import org.hibernate.boot.internal.Abstract;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Abstract
public abstract class ServiceAbstract<T> implements SimpleRequestInterface<T> {
    @Autowired
    EmpregadoRepository empregadoRepository;

    protected void verificarPermissaoRequerinte(Integer idRequerinte) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findById(idRequerinte);

        if (optionalEmpregado.isEmpty()) throw  new RegistroInexistenteException("Não foi encontrado o registro do requerinte!");
        if (optionalEmpregado.get().getCargo().equals(TipoCargo.EMPREGADO)) throw new RequerenteNaoAutorizadoException("Conta Requerinte sem permissão!");
    }
}
