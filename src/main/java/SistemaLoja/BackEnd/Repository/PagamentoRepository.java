package SistemaLoja.BackEnd.Repository;

import SistemaLoja.BackEnd.Entity.Plain.Pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    List<Pagamento> findAllByIdCliente(Integer idCliente);
    List<Pagamento> findAllByIdFilial(Integer idFilial);
}
