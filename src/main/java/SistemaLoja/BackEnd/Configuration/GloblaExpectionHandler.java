package SistemaLoja.BackEnd.Configuration;

import SistemaLoja.BackEnd.Exception.RegistroInexistenteException;
import SistemaLoja.BackEnd.Exception.RegistroJaExistenteException;
import SistemaLoja.BackEnd.Exception.RequerinteNaoAutorizadoException;
import SistemaLoja.BackEnd.Exception.TabelaVaziaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloblaExpectionHandler {
    // Retorna 404
    @ExceptionHandler(TabelaVaziaException.class)
    public ResponseEntity<String> handleTabelaVazia(TabelaVaziaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Retorna 401
    @ExceptionHandler(RequerinteNaoAutorizadoException.class)
    public ResponseEntity<String> handleNaoAutorizado(RequerinteNaoAutorizadoException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    // Retorna 400
    @ExceptionHandler(RegistroInexistenteException.class)
    public ResponseEntity<String> handleRegistroInexistente(RegistroInexistenteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Retorna 409
    @ExceptionHandler(RegistroJaExistenteException.class)
    public ResponseEntity<String> handleRegistroJaExistente(RegistroJaExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Retorna erros genéricos de maneira formatada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno no servidor: " + ex.getMessage());
    }
}
