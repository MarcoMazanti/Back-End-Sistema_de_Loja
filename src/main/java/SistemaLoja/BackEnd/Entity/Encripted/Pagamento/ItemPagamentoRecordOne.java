package SistemaLoja.BackEnd.Entity.Encripted.Pagamento;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemPagamentoRecordOne(String id, String idPagamento, String idItem, String nome, String quantidade, String preco) {
    @JsonCreator
    public ItemPagamentoRecordOne(@JsonProperty("id") String id,
                                  @JsonProperty("idPagamento") String idPagamento,
                                  @JsonProperty("idItem") String idItem,
                                  @JsonProperty("nome") String nome,
                                  @JsonProperty("quantidade") String quantidade,
                                  @JsonProperty("preco") String preco) {
        this.id = id;
        this.idPagamento = idPagamento;
        this.idItem = idItem;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
