package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Fornecedor.Fornecedor;
import SistemaLoja.BackEnd.Service.FornecedorService;
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
@RequestMapping("/api/fornecedor")
@Tag(name = "Fornecedores", description = "Gerenciamento dos Fornecedores da Loja")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @Operation(summary = "Obter Todos Fornecedores", description = "Obtem todos os fornecedores presentes no Banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não existe nenhum fornecedor registrado.")
    })
    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAllFornecedores() {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.listar());
    }

    @Operation(summary = "Obter Fornecedor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado nenhum fornecedor com este ID.")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarPorId(id));
    }

    @Operation(summary = "Salvar Fornecedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação."),
            @ApiResponse(responseCode = "409", description = "Foi encontrado um fornecedor já cadastrado com o CPF/CNPJ inserido.")
    })
    @PostMapping("/{idRequerinte}")
    public ResponseEntity<Fornecedor> postNovoFornecedor(@PathVariable Integer idRequerinte, @RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.salvar(idRequerinte, fornecedor));
    }

    @Operation(summary = "Atualizar Fornecedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não existe nenhum fornecedor com base no ID oferecido na " +
                    "entidade ou não foi encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação."),
            @ApiResponse(responseCode = "406", description = "Não é permitido alterar o CPF/CNPJ e o ID do fornecedor.")
    })
    @PutMapping("/{idRequerinte}")
    public ResponseEntity<Fornecedor> putFornecedor(@PathVariable Integer idRequerinte, @RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.atualizar(idRequerinte, fornecedor));
    }

    @Operation(summary = "Deletar Fornecedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não existe nenhum fornecedor com base no ID oferecido na " +
                    "entidade ou não foi encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação.")
    })
    @DeleteMapping("/{idRequerinte}/id/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Integer idRequerinte, @PathVariable Integer id) {
        fornecedorService.remover(idRequerinte, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
