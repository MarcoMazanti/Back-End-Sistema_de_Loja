package SistemaLoja.BackEnd.Entity.Plain.Filial;

import SistemaLoja.BackEnd.Exception.TamanhoInvalidoCampoException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "cnpj", "telefone", "quantEmpregados", "fullAdress", "codCountry", "codEstado", "codCidade", "codFilial"})
@Entity(name = "filial")
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    @NotBlank
    @Size(min = 14, max = 14)
    @Setter(AccessLevel.NONE)
    private String cnpj;
    @NotBlank
    @Size(min = 9, max = 15)
    private String telefone;
    private int quantEmpregados;
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
    @Column(name = "cod_filial", length = 21, insertable = false, updatable = false)
    private String codFilial;

    // Responsável pela volta da FilialRecordTwo quando descriptografar
    public Filial(String codFilial, String cnpj, String telefone, int quantEmpregados, String fullAdress) {
        String[] listaDados = codFilial.split("-");

        this.id = Integer.parseInt(listaDados[4]);
        this.codFilial = codFilial;
        this.setCnpj(cnpj);
        this.setTelefone(telefone);
        this.quantEmpregados = quantEmpregados;
        this.fullAdress = fullAdress;
        this.codCountry = listaDados[0];
        this.codEstado = listaDados[1];
        this.codCidade = listaDados[2];
    }

    // Responsável apenas pelos campos NOT NULL no banco
    public Filial(String cnpj, String telefone, String fullAdress, String codCountry, String codEstado, String codCidade) {
        this.setCnpj(cnpj);
        this.setTelefone(telefone);
        this.fullAdress = fullAdress;
        this.codCountry = codCountry;
        this.codEstado = codEstado;
        this.codCidade = codCidade;
    }

    // Responsável quando for envio de dados completos para cadastro
    public Filial(String cnpj, String telefone, int quantEmpregados, String fullAdress, String codCountry, String codEstado, String codCidade, String codFilial) {
        this.setCnpj(cnpj);
        this.setTelefone(telefone);
        this.quantEmpregados = quantEmpregados;
        this.fullAdress = fullAdress;
        this.codCountry = codCountry;
        this.codEstado = codEstado;
        this.codCidade = codCidade;
        this.codFilial = codFilial;
    }

    public void setCnpj(@NotBlank String cnpj) {
        if (cnpj == null) {
            this.cnpj = null;
            return;
        }

        String regex = "[^0-9]";
        String cnpjRefeito = cnpj.replaceAll(regex, "");

        if (!(cnpjRefeito.length() == 14)) throw new TamanhoInvalidoCampoException("CNPJ inválido!");
        this.cnpj = cnpjRefeito;
    }

    public void setTelefone(String telefone) {
        if (telefone == null) {
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
        Filial filial = (Filial) o;
        return id == filial.id && Objects.equals(cnpj, filial.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj);
    }
}
