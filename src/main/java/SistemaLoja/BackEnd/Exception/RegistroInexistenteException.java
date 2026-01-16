package SistemaLoja.BackEnd.Exception;

public class RegistroInexistenteException extends RuntimeException {
    public RegistroInexistenteException(String message) {
        super(message);
    }
}
