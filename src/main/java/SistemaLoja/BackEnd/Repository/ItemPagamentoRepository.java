package SistemaLoja.BackEnd.Repository;

import SistemaLoja.BackEnd.Entity.Plain.Pagamento.ItemPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPagamentoRepository extends JpaRepository<ItemPagamento, Integer> {
    List<ItemPagamento> findAllByIdPagamento(Integer idPagamento);
    List<ItemPagamento> findAllByIdItem(Integer idItem);
}
