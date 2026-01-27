package SistemaLoja.BackEnd.Entity.Plain.Empregado;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de armazenamento de dados para validação de login")
public record Login(String cpf, String senha) {
    @JsonCreator
    public Login(@JsonProperty("cpf") String cpf, @JsonProperty("senha") String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }
}
