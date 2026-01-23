package SistemaLoja.BackEnd.Exception;

public class RequerenteNaoAutorizadoException extends RuntimeException {
    public RequerenteNaoAutorizadoException(String message) {
        super(message);
    }
}
