package SistemaLoja.BackEnd.Entity.Encripted.Pagamento;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemPagamentoRecordOne(String idPagamento, String idItem, String quantidade) {
    @JsonCreator
    public ItemPagamentoRecordOne(@JsonProperty("idPagamento") String idPagamento,
                                  @JsonProperty("idItem") String idItem,
                                  @JsonProperty("quantidade") String quantidade) {
        this.idPagamento = idPagamento;
        this.idItem = idItem;
        this.quantidade = quantidade;
    }
}
