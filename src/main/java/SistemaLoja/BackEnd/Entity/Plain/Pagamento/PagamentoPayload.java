package SistemaLoja.BackEnd.Entity.Plain.Pagamento;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonPropertyOrder({"pagamento", "itemPagamentoList"})
public class PagamentoPayload {
    private Pagamento pagamento;
    private List<ItemPagamento> itemPagamentoList;
}
