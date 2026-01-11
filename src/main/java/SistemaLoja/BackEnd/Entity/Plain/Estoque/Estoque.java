package SistemaLoja.BackEnd.Entity.Plain.Estoque;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Max(255)
    private String nome;
    @NotNull
    private int idFilial;
    @NotNull
    private int idFornecedor;
    @NotNull
    private BigDecimal preco;
    private int quantidade;
    private String descricao;
    @Max(23)
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
}
