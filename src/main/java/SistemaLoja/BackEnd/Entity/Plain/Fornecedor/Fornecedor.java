package SistemaLoja.BackEnd.Entity.Plain.Fornecedor;

import SistemaLoja.BackEnd.Exception.TamanhoInvalidoCampoException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "nome", "cpfOrCnpj", "email", "telefone", "fullAdress", "codCountry", "codEstado", "codCidade", "codFornecedor"})
@Entity(name = "fornecedor")
@Schema(description = "Responsável por armazenar todos os dados de um fornecedor.")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Schema(description = "Número de identificação", example = "1")
    private int id;
    @NotBlank
    @Size(max = 255)
    @Schema(description = "Nome da pessoa", example = "Nome Completo", maxLength = 255)
    private String nome;
    @NotBlank
    @Size(min = 11, max = 14)
    @Setter(AccessLevel.NONE)
    @Schema(description = "CPF/CNPJ da pessoa", example = "01234567890", minLength = 11, maxLength = 14)
    private String cpfOrCnpj;
    @NotBlank
    @Size(max = 255)
    @Schema(description = "Email de identificação", example = "marco.aureio@example.com", maxLength = 255)
    private String email;
    @Size(min = 9, max = 15)
    @Schema(example = "55018990123456", minLength = 9, maxLength = 15)
    private String telefone;
    @Column(name = "full_adress", columnDefinition = "TEXT", nullable = false)
    @Schema(description = "Endereço completo", example = "País, Estado, Cidade, Rua, Complemento, Número")
    private String fullAdress;
    @NotBlank
    @Size(max = 3)
    @Schema(example = "BR", maxLength = 3)
    private String codCountry;
    @NotBlank
    @Size(max = 3)
    @Schema(example = "SP", maxLength = 3)
    private String codEstado;
    @NotBlank
    @Size(max = 3)
    @Schema(example = "SP", maxLength = 3)
    private String codCidade;
    @Column(name = "cod_fornecedor", length = 25, insertable = false, updatable = false)
    @Schema(description = "Série única do cliente gerado automaticamente", example = "BR-SP-SP-FNC-1")
    private String codForncedor;

    // Responsável pela volta do FornecedorRecordTwo quando descriptografar
    public Fornecedor(String codForncedor, String nome, String cpfOrCnpj, String email, String telefone, String fullAdress) {
        String[] listaDados = codForncedor.split("-");

        this.id = Integer.parseInt(listaDados[4]);
        this.codForncedor = codForncedor;
        this.nome = nome;
        this.setCpfOrCnpj(cpfOrCnpj);
        this.email = email;
        this.setTelefone(telefone);
        this.fullAdress = fullAdress;
        this.codCountry = listaDados[0];
        this.codEstado = listaDados[1];
        this.codCidade = listaDados[2];
    }

    // Responsável apenas pelos campos NOT NULL no banco
    public Fornecedor(String nome, String cpfOrCnpj, String email, String fullAdress, String codCountry, String codEstado, String codCidade) {
        this.nome = nome;
        this.setCpfOrCnpj(cpfOrCnpj);
        this.email = email;
        this.fullAdress = fullAdress;
        this.codCountry = codCountry;
        this.codEstado = codEstado;
        this.codCidade = codCidade;
    }

    // Responsável quando for envio de dados completos para cadastro
    public Fornecedor(String nome, String cpfOrCnpj, String email, String telefone, String fullAdress, String codCountry,
                   String codEstado, String codCidade, String codForncedor) {
        this.nome = nome;
        this.setCpfOrCnpj(cpfOrCnpj);
        this.email = email;
        this.setTelefone(telefone);
        this.fullAdress = fullAdress;
        this.codCountry = codCountry;
        this.codEstado = codEstado;
        this.codCidade = codCidade;
        this.codForncedor = codForncedor;
    }

    public void setCpfOrCnpj(@NotBlank String cpfOrCnpj) {
        if (cpfOrCnpj == null || cpfOrCnpj.trim().isEmpty()) {
            this.cpfOrCnpj = null;
            return;
        }

        String regex = "[^0-9]";
        String cpfOrCnpjRefeito = cpfOrCnpj.replaceAll(regex, "");

        if (!(cpfOrCnpjRefeito.length() == 11 || cpfOrCnpjRefeito.length() == 14)) throw new TamanhoInvalidoCampoException("CPF ou CNPJ inválido!");
        this.cpfOrCnpj = cpfOrCnpjRefeito;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            this.telefone = null;
            return;
        }

        String regex = "[^0-9]";
        String telefoneRefeito = telefone.replaceAll(regex, "");

        if (telefoneRefeito.length() < 9 || telefoneRefeito.length() > 15) throw new TamanhoInvalidoCampoException("Número de telefone inválido!");
        this.telefone = telefoneRefeito;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return id == that.id && Objects.equals(cpfOrCnpj, that.cpfOrCnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpfOrCnpj);
    }
}
