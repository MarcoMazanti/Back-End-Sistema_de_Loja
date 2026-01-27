package SistemaLoja.BackEnd.Entity.Plain.Pagamento;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonPropertyOrder({"pagamento", "itemPagamentoList"})
@Schema(description = "Responsável por armazenar todos os dados de uma compra.")
public class PagamentoPayload {
    private Pagamento pagamento;
    private List<ItemPagamento> itemPagamentoList;
}
