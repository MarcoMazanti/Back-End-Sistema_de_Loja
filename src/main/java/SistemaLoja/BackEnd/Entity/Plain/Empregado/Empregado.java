package SistemaLoja.BackEnd.Entity.Plain.Empregado;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "empregado")
public class Empregado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(max = 255)
    private String nome;
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;
    @NotBlank
    @Size(max = 200)
    private String senha;
    @NotBlank
    @Size(max = 255)
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
    @Column(name = "cod_empregado", length = 24, insertable = false, updatable = false)
    private String codEmpregado;

    // Responsável apenas pelos campos NOT NULL no banco
    public Empregado(String nome, String cpf, String senha, String email, BigDecimal salario, Integer filialId) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.salario = salario;
        this.filialId = filialId;
    }

    // Responsável quando for envio de dados completos para cadastro
    public Empregado(String nome, String cpf, String senha, String email, String telefone, BigDecimal salario,
                     TipoCargo cargo, Integer filialId, Date aniversario, Date dataAdimissao, String codEmpregado) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.salario = salario;
        this.cargo = cargo;
        this.filialId = filialId;
        this.aniversario = aniversario;
        this.dataAdimissao = dataAdimissao;
        this.codEmpregado = codEmpregado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empregado empregado = (Empregado) o;
        return id == empregado.id && Objects.equals(cpf, empregado.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
