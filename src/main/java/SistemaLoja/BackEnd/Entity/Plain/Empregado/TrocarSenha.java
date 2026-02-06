package SistemaLoja.BackEnd.Entity.Plain.Empregado;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de obter os campos a fim de trocar a senha do Usuário")
public record TrocarSenha(String cpf, String email, String senha) {
    @JsonCreator
    public TrocarSenha(@JsonProperty("cpf") String cpf,
                       @JsonProperty("email") String email,
                       @JsonProperty("senha") String senha) {
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }
}
