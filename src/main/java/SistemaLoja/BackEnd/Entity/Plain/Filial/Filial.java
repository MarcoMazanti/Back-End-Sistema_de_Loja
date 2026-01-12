package SistemaLoja.BackEnd.Entity.Plain.Filial;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "filial")
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(min = 14, max = 14)
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
    @Size(max = 21)
    private String codFilial;

    // Responsável pela volta da FilialRecordTwo quando descriptografar
    public Filial(String codFilial, String cnpj, String telefone, int quantEmpregados, String fullAdress) {
        String[] listaDados = codFilial.split("-");

        this.id = Integer.parseInt(listaDados[4]);
        this.codFilial = codFilial;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.quantEmpregados = quantEmpregados;
        this.fullAdress = fullAdress;
        this.codCountry = listaDados[0];
        this.codEstado = listaDados[1];
        this.codCidade = listaDados[2];
    }

    // Responsável apenas pelos campos NOT NULL no banco
    public Filial(String cnpj, String telefone, String fullAdress, String codCountry, String codEstado, String codCidade) {
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.fullAdress = fullAdress;
        this.codCountry = codCountry;
        this.codEstado = codEstado;
        this.codCidade = codCidade;
    }

    // Responsável quando for envio de dados completos para cadastro
    public Filial(String cnpj, String telefone, int quantEmpregados, String fullAdress, String codCountry, String codEstado, String codCidade, String codFilial) {
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.quantEmpregados = quantEmpregados;
        this.fullAdress = fullAdress;
        this.codCountry = codCountry;
        this.codEstado = codEstado;
        this.codCidade = codCidade;
        this.codFilial = codFilial;
    }
}
