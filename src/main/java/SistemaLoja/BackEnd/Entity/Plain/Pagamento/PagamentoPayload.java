package SistemaLoja.BackEnd.Entity.Plain.Pagamento;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagamentoPayload {
    private Pagamento pagamento;
    private List<ItemPagamento> itemPagamentoList;

    public void adicionarItem(ItemPagamento item) {
        itemPagamentoList.add(item);
    }
}
