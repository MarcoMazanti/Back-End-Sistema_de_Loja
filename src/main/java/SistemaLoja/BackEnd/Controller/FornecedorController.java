package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Fornecedor.Fornecedor;
import SistemaLoja.BackEnd.Service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAllFornecedores() {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.listar());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarPorId(id));
    }

    @PostMapping("/{idRequerinte}")
    public ResponseEntity<Fornecedor> postNovoFornecedor(@PathVariable Integer idRequerinte, @RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.salvar(idRequerinte, fornecedor));
    }

    @PutMapping("/{idRequerinte}")
    public ResponseEntity<Fornecedor> putFornecedor(@PathVariable Integer idRequerinte, @RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.atualizar(idRequerinte, fornecedor));
    }

    @DeleteMapping("/{idRequerinte}/id/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Integer idRequerinte, @PathVariable Integer id) {
        fornecedorService.remover(idRequerinte, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
