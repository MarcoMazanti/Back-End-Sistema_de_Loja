package SistemaLoja.BackEnd.Entity.Plain.Empregado;

import SistemaLoja.BackEnd.Exception.TamanhoInvalidoCampoException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "nome", "cpf", "senha", "email", "telefone", "salario", "cargo", "filialId", "aniversario", "dataAdimissao", "codEmpregado"})
@Entity(name = "empregado")
public class Empregado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    @NotBlank
    @Size(max = 255)
    private String nome;
    @NotBlank
    @Size(min = 11, max = 11)
    @Setter(AccessLevel.NONE)
    private String cpf;
    @NotBlank
    @Size(max = 200)
    private String senha;
    @NotBlank
    @Size(max = 255)
    private String email;
    @Size(min = 9, max = 15)
    private String telefone;
    @NotNull
    private BigDecimal salario;
    private TipoCargo cargo;
    @NotNull
    private Integer filialId;
    private Date aniversario;
    private Date dataAdimissao;
    @Column(name = "cod_empregado", length = 24, insertable = false, updatable = false)
    private String codEmpregado;


    // Responsável apenas pelos campos NOT NULL no banco
    public Empregado(String nome, String cpf, String senha, String email, BigDecimal salario, Integer filialId) {
        this.nome = nome;
        this.setCpf(cpf);
        this.senha = senha;
        this.email = email;
        this.salario = salario;
        this.filialId = filialId;
    }

    // Responsável quando for envio de dados completos para cadastro
    public Empregado(String nome, String cpf, String senha, String email, String telefone, BigDecimal salario,
                     TipoCargo cargo, Integer filialId, Date aniversario, Date dataAdimissao, String codEmpregado) {
        this.nome = nome;
        this.setCpf(cpf);
        this.senha = senha;
        this.email = email;
        this.setTelefone(telefone);
        this.salario = salario;
        this.cargo = cargo;
        this.filialId = filialId;
        this.aniversario = aniversario;
        this.dataAdimissao = dataAdimissao;
        this.codEmpregado = codEmpregado;
    }

    public void setCpf(@NotBlank String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            this.cpf = null;
            return;
        }

        String regex = "[^0-9]";
        String cpfRefeito = cpf.replaceAll(regex, "");

        if (!(cpfRefeito.length() == 11)) throw new TamanhoInvalidoCampoException("CPF inválido!");
        this.cpf = cpfRefeito;
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
        Empregado empregado = (Empregado) o;
        return id == empregado.id && Objects.equals(cpf, empregado.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
