package SistemaLoja.BackEnd.Exception;

public class SenhaNaoPermitidaException extends RuntimeException {
    public SenhaNaoPermitidaException(String message) {
        super(message);
    }
}
