package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Service.FilialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filial")
public class FilialController {
    @Autowired
    private FilialService filialService;

    @GetMapping()
    public ResponseEntity<List<Filial>> getAllFilial() {
        return ResponseEntity.status(HttpStatus.OK).body(filialService.listar());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Filial> getByIdFilial(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(filialService.buscarPorId(id));
    }

    @PostMapping("/{idRequerinte}")
    public ResponseEntity<Filial> postNewFilial(@PathVariable Integer idRequerinte, @RequestBody @Valid Filial filial) {
        return ResponseEntity.status(HttpStatus.OK).body(filialService.salvar(idRequerinte, filial));
    }

    @PutMapping("/{idRequerinte}")
    public ResponseEntity<Filial> putAlterarFilial(@PathVariable Integer idRequerinte, @RequestBody @Valid Filial filial) {
        return ResponseEntity.status(HttpStatus.OK).body(filialService.atualizar(idRequerinte, filial));
    }

    @DeleteMapping("/{idRequerinte}/id/{id}")
    public ResponseEntity<Void> deleteFilial(@PathVariable Integer idRequerinte, @PathVariable Integer id) {
        filialService.remover(idRequerinte, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
