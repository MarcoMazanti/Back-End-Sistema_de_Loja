package SistemaLoja.BackEnd.Entity.Encripted.Empregado;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record EmpregadoRecordOne(String nome, String email, String telefone, String codEmpregado) {
    @JsonCreator
    public EmpregadoRecordOne(@JsonProperty("nome") String nome,
                              @JsonProperty("email") String email,
                              @JsonProperty("telefone") String telefone,
                              @JsonProperty("codEmpregado") String codEmpregado) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.codEmpregado = codEmpregado;
    }
}
