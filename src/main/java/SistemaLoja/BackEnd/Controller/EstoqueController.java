package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Estoque.Estoque;
import SistemaLoja.BackEnd.Service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<List<Estoque>> getAllEstoque() {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.listar());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Estoque> getEstoqueById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.buscarPorId(id));
    }

    @GetMapping("/id_filial/{idFilial}")
    public ResponseEntity<List<Estoque>> getAllEstoqueByIdFilial(@PathVariable Integer idFilial) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.listarPorIdFilial(idFilial));
    }

    @GetMapping("/id_fornecedor/{idFornecedor}")
    public ResponseEntity<List<Estoque>> getAllEstoqueByIdFornecedor(@PathVariable Integer idFornecedor) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.listarPorIdFornecedor(idFornecedor));
    }

    @PostMapping
    public ResponseEntity<Estoque> postNovoEstoque(@RequestBody Estoque estoque) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.salvar(estoque));
    }

    @PutMapping
    public ResponseEntity<Estoque> putAtualizarEstoque(@RequestBody Estoque estoque) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.atualizar(estoque));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteEstoqueById(@PathVariable Integer id) {
        estoqueService.remover(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
