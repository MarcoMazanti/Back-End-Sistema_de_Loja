package SistemaLoja.BackEnd.Exception;

public class RequerinteNaoAutorizadoException extends RuntimeException {
    public RequerinteNaoAutorizadoException(String message) {
        super(message);
    }
}
