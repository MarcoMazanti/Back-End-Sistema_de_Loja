package SistemaLoja.BackEnd.Configuration;

import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filial")
public class FilialController {
    @Autowired
    private FilialRepository filialRepository;

    @GetMapping()
    public ResponseEntity<?> getAllFilial() {
        try {
            List<Filial> listaFiliais = filialRepository.findAll();

            if (listaFiliais.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tabela vazia!");
            return ResponseEntity.status(HttpStatus.OK).body(listaFiliais);
        } catch (Exception e) {
            System.out.println("Erro ao pegar filiais: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> endpointTeste(@RequestBody Filial filial) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(filialRepository.save(filial));
        } catch (Exception e) {
            System.out.println("Erro na testagem: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
