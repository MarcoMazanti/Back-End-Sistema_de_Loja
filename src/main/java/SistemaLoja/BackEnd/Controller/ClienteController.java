package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import SistemaLoja.BackEnd.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listar());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarPorId(id));
    }

    @PostMapping("/{idRequerinte}")
    public ResponseEntity<Cliente> postNovoCliente(@PathVariable Integer idRequerinte, @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.salvar(idRequerinte, cliente));
    }

    @PutMapping("/{idRequerinte}")
    public ResponseEntity<Cliente> putCliente(@PathVariable Integer idRequerinte, @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizar(idRequerinte, cliente));
    }

    @DeleteMapping("/{idRequerinte}/id/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer idRequerinte, @PathVariable Integer id) {
        clienteService.remover(idRequerinte, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
