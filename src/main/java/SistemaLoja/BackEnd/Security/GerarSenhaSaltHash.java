package SistemaLoja.BackEnd.Security;

import SistemaLoja.BackEnd.Exception.SenhaNaoPermitidaException;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class GerarSenhaSaltHash {
    public static String encriptarSenha(String senha) {
        String regex = "^(?=.*[0-9])(?=.*[!@#$%^&*()\\-+]).+$";

        if (senha.length() < 8) throw new SenhaNaoPermitidaException("Senha com menos de 8 caracteres!");
        if (!senha.matches(regex)) throw new SenhaNaoPermitidaException("A senha deve ter número e caracteres especiais!");

        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean validarSenha(String senhaFront, String senhaBancoDeDados) {
        return BCrypt.checkpw(senhaFront, senhaBancoDeDados);
    }
}
