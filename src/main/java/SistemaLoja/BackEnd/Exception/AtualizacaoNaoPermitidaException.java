package SistemaLoja.BackEnd.Exception;

public class AtualizacaoNaoPermitidaException extends RuntimeException {
    public AtualizacaoNaoPermitidaException(String message) {
        super(message);
    }
}
