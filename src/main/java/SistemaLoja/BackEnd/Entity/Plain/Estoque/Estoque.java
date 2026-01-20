package SistemaLoja.BackEnd.Entity.Plain.Estoque;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    @NotBlank
    @Size(max = 255)
    private String nome;
    @NotNull
    private int idFilial;
    @NotNull
    private int idFornecedor;
    @NotNull
    private BigDecimal preco;
    private int quantidade;
    private String descricao;
    @Column(name = "cod_item", length = 23, insertable = false, updatable = false)
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
