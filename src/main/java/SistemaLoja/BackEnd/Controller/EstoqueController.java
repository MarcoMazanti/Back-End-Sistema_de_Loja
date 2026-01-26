package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Estoque.Estoque;
import SistemaLoja.BackEnd.Service.EstoqueService;
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
@RequestMapping("/api/estoque")
@Tag(name = "Estoque", description = "Gerenciamento do Estoque da Loja")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @Operation(summary = "Obter todos Estoques", description = "Obtem todos os estoques presentes no banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não possui nenhum registro na tabela de Estoque.")
    })
    @GetMapping
    public ResponseEntity<List<Estoque>> getAllEstoque() {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.listar());
    }

    @Operation(summary = "Obter Estoque por ID", description = "Obtem o estoque com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado o item no estoque com base no ID fornecido.")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Estoque> getEstoqueById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.buscarPorId(id));
    }

    @Operation(summary = "Obter todos Estoques por idFilial", description = "Obtem todos os estoques com base na filial fornecida.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não possui nenhum registro de estoque presente nesta filial.")
    })
    @GetMapping("/id_filial/{idFilial}")
    public ResponseEntity<List<Estoque>> getAllEstoqueByIdFilial(@PathVariable Integer idFilial) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.listarPorIdFilial(idFilial));
    }

    @Operation(summary = "Obter todos Estoques por idFornecedor", description = "Obtem todos os estoques com base no fornecedor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não possui nenhum registro de estoque presente com este fornecedor.")
    })
    @GetMapping("/id_fornecedor/{idFornecedor}")
    public ResponseEntity<List<Estoque>> getAllEstoqueByIdFornecedor(@PathVariable Integer idFornecedor) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.listarPorIdFornecedor(idFornecedor));
    }

    @Operation(summary = "Salvar Estoque", description = "Salva um novo item no estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Já existe este item nesta filial com o mesmo nome e fornecedor.")
    })
    @PostMapping
    public ResponseEntity<Estoque> postNovoEstoque(@RequestBody Estoque estoque) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.salvar(estoque));
    }

    @Operation(summary = "Atualizar Estoque", description = "Atualiza um item presente no estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado o registro do item com base no nome, idFilial e idFornecedor disponibilizados."),
            @ApiResponse(responseCode = "406", description = "Não é permitido alterar o nome, idFilial ou idFornecedor.")
    })
    @PutMapping
    public ResponseEntity<Estoque> putAtualizarEstoque(@RequestBody Estoque estoque) {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.atualizar(estoque));
    }

    @Operation(summary = "Deletar Estoques", description = "Deleta um item presente no estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado este ID no Estoque.")
    })
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteEstoqueById(@PathVariable Integer id) {
        estoqueService.remover(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
