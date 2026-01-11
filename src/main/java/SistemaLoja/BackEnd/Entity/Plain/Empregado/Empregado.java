package SistemaLoja.BackEnd.Entity.Plain.Empregado;

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

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "empregado")
public class Empregado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Max(255)
    private String nome;
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;
    @NotBlank
    @Max(200)
    private String senha;
    @NotBlank
    @Max(255)
    private String email;
    @Size(min = 9, max = 15)
    private String telefone;
    @NotBlank
    private BigDecimal salario;
    private TipoCargo cargo;
    @NotBlank
    private Integer filialId;
    private Date aniversario;
    private Date dataAdimissao;
    @Max(24)
    private String codEmpregado;

    public Empregado(String nome, String cpf, String senha, String email, BigDecimal salario, Integer filialId) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.salario = salario;
        this.filialId = filialId;
    }
}
