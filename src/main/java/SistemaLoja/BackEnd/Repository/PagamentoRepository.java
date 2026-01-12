package SistemaLoja.BackEnd.Repository;

import SistemaLoja.BackEnd.Entity.Plain.Pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
