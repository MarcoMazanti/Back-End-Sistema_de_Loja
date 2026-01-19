package SistemaLoja.BackEnd.Exception;

public class LoginNaoAutorizadoException extends RuntimeException {
    public LoginNaoAutorizadoException(String message) {
        super(message);
    }
}
