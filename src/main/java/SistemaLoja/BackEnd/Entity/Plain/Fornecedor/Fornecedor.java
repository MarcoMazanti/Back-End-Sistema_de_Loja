package SistemaLoja.BackEnd.Entity.Plain.Fornecedor;

import SistemaLoja.BackEnd.Exception.TamanhoInvalidoCampoException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    @NotBlank
    @Size(max = 255)
    private String nome;
    @NotBlank
    @Size(min = 11, max = 14)
    @Setter(AccessLevel.NONE)
    private String cpfOrCnpj;
    @NotBlank
    @Size(max = 255)
    private String email;
    @Size(min = 9, max = 15)
    private String telefone;
    @Column(name = "full_adress", columnDefinition = "TEXT", nullable = false)
    private String fullAdress;
    @NotBlank
    @Size(max = 3)
    private String codCountry;
    @NotBlank
    @Size(max = 3)
    private String codEstado;
    @NotBlank
    @Size(max = 3)
    private String codCidade;
    @Column(name = "cod_fornecedor", length = 25, insertable = false, updatable = false)
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
        String regex = "[^0-9]";
        String cpfOrCnpjRefeito = cpfOrCnpj.replaceAll(regex, "");

        if (!(cpfOrCnpjRefeito.length() == 11 || cpfOrCnpjRefeito.length() == 14)) throw new TamanhoInvalidoCampoException("CPF ou CNPJ inválido!");
        this.cpfOrCnpj = cpfOrCnpjRefeito;
    }

    public void setTelefone(String telefone) {
        String regex = "[^0-9]";
        String telefoneRefeito = telefone.replaceAll(regex, "");

        if (telefoneRefeito.length() < 9 || telefoneRefeito.length() > 15) throw new TamanhoInvalidoCampoException("Número de telefone inválido!");
        this.telefone = telefoneRefeito;
    }
}
