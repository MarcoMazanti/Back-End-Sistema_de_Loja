package SistemaLoja.BackEnd.Entity.Plain.Empregado;

import SistemaLoja.BackEnd.Exception.TamanhoInvalidoCampoException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Responsável por armazenar todos os dados de um empregado.")
public class Empregado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Schema(description = "Número de identificação", example = "1")
    private Integer id;
    @NotBlank
    @Size(max = 255)
    @Schema(description = "Nome da pessoa", example = "Nome Completo", maxLength = 255)
    private String nome;
    @NotBlank
    @Size(min = 11, max = 11)
    @Setter(AccessLevel.NONE)
    @Schema(description = "CPF da pessoa", example = "01234567890", minLength = 11, maxLength = 11)
    private String cpf;
    @NotBlank
    @Size(max = 200)
    @Schema(description = "Senha salt-hash da pessoa", maxLength = 200)
    private String senha;
    @NotBlank
    @Size(max = 255)
    @Schema(description = "Email de identificação", example = "marco.aureio@example.com", maxLength = 255)
    private String email;
    @Size(min = 9, max = 15)
    @Schema(example = "55018990123456", minLength = 9, maxLength = 15)
    private String telefone;
    @NotNull
    @Schema(example = "1500.00", defaultValue = "0.00")
    private BigDecimal salario;
    @Schema(description = "Enum de cargos armazenado como smallint", example = "1", defaultValue = "0")
    private TipoCargo cargo;
    @NotNull
    @Schema(description = "Campo relacional apontando para um registro de Filial", example = "1")
    private Integer filialId;
    @Schema(description = "Campo opcional preenchido pelo dono ca conta")
    private Date aniversario;
    @Schema(description = "Campo gerado automaticamente", defaultValue = "CURRENT_DATE")
    private Date dataAdimissao;
    @Column(name = "cod_empregado", length = 24, insertable = false, updatable = false)
    @Schema(description = "Série única do empregado gerado automaticamente", example = "FIL-1-EMP-1")
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

    public boolean alteracaoPropria(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empregado empregado = (Empregado) o;

        if (!cargo.equals(TipoCargo.DONO)) {
            return id == empregado.id && nome.equals(empregado.getNome()) && Objects.equals(cpf, empregado.cpf) &&
                    salario.compareTo(empregado.salario) == 0 && cargo.equals(empregado.cargo) && Objects.equals(filialId, empregado.filialId) &&
                    dataAdimissao.compareTo(empregado.dataAdimissao) == 0 && Objects.equals(codEmpregado, empregado.codEmpregado);
        } else {
            return true;
        }
    }
}
