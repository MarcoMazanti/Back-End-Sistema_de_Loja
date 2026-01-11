package SistemaLoja.BackEnd.Entity.Plain.Filial;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
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
    @NotBlank
    private String fullAdress;
    @NotBlank
    @Max(3)
    private String codCountry;
    @NotBlank
    @Max(3)
    private String codEstado;
    @NotBlank
    @Max(3)
    private String codCidade;
    @Max(21)
    private String codFilial;

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
}
