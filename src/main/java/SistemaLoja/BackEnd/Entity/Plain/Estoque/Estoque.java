package SistemaLoja.BackEnd.Entity.Plain.Estoque;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "nome", "idFilial", "idFornecedor", "preco", "quantidade", "descricao", "codItem"})
@Entity(name = "estoque")
@Schema(description = "Responsável por armazenar todos os dados de um estoque.")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Schema(description = "Número de identificação", example = "1")
    private int id;
    @NotBlank
    @Size(max = 255)
    @Schema(description = "Nome do item", example = "Nome Completo do item", maxLength = 255)
    private String nome;
    @NotNull
    @Schema(description = "Campo relacional apontando para um registro de Filial", example = "2")
    private int idFilial;
    @NotNull
    @Schema(description = "Campo relacional apontando para um registro de Fornecedor", example = "3")
    private int idFornecedor;
    @NotNull
    @Schema(description = "Preço unitário do item")
    private BigDecimal preco;
    @Schema(description = "Quantidade deste item disponível")
    private int quantidade;
    @Schema(description = "Descrição do item em estoque")
    private String descricao;
    @Column(name = "cod_item", length = 23, insertable = false, updatable = false)
    @Schema(description = "Série única do estoque gerado automaticamente", example = "FIL-2-ITM-1")
    private String codItem;

    // Responsável pela volta do EstoqueRecordTwo quando descriptografar
    public Estoque(String nome, int idFornecedor, BigDecimal preco, int quantidade, String descricao, String codItem) {
        String[] listaDados = codItem.split("-");

        this.id = Integer.parseInt(listaDados[3]);
        this.nome = nome;
        this.idFilial = Integer.parseInt(listaDados[1]);
        this.idFornecedor = idFornecedor;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.codItem = codItem;
    }

    // Responsável apenas pelos campos NOT NULL no banco
    public Estoque(String nome, int idFilial, int idFornecedor, BigDecimal preco) {
        this.nome = nome;
        this.idFilial = idFilial;
        this.idFornecedor = idFornecedor;
        this.preco = preco;
    }

    // Responsável quando for envio de dados completos para cadastro
    public Estoque(String nome, int idFilial, int idFornecedor, BigDecimal preco, int quantidade, String descricao, String codItem) {
        this.nome = nome;
        this.idFilial = idFilial;
        this.idFornecedor = idFornecedor;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.codItem = codItem;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return idFilial == estoque.idFilial && idFornecedor == estoque.idFornecedor && Objects.equals(nome, estoque.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idFilial, idFornecedor);
    }
}
