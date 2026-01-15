package SistemaLoja.BackEnd.Exception;

public class RegistroJaExistenteException extends RuntimeException {
    public RegistroJaExistenteException(String message) {
        super(message);
    }
}
