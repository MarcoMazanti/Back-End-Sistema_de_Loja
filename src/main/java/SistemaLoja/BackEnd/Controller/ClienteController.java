package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import SistemaLoja.BackEnd.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@Tag(name = "Clientes", description = "Gerenciamento dos Clientes da Loja")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obter Todos Clientes", description = "Obtem todos os clientes presentes no Banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não existe nenhum cliente registrado.")
    })
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listar());
    }

    @Operation(summary = "Obter Cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado nenhum cliente com este ID.")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarPorId(id));
    }

    @Operation(summary = "Salvar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação."),
            @ApiResponse(responseCode = "409", description = "Foi encontrado um cliente já cadastrado com o CPF/CNPJ inserido.")
    })
    @PostMapping("/{idRequerinte}")
    public ResponseEntity<Cliente> postNovoCliente(@PathVariable Integer idRequerinte, @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.salvar(idRequerinte, cliente));
    }

    @Operation(summary = "Atualizar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não existe nenhum cliente com base no ID oferecido na " +
                    "entidade ou não foi encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação."),
            @ApiResponse(responseCode = "406", description = "Não é permitido alterar o CPF/CNPJ e o ID do cliente.")
    })
    @PutMapping("/{idRequerinte}")
    public ResponseEntity<Cliente> putCliente(@PathVariable Integer idRequerinte, @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizar(idRequerinte, cliente));
    }

    @Operation(summary = "Deletar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não existe nenhum cliente com base no ID oferecido na " +
                    "entidade ou não foi encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação.")
    })
    @DeleteMapping("/{idRequerinte}/id/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer idRequerinte, @PathVariable Integer id) {
        clienteService.remover(idRequerinte, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
