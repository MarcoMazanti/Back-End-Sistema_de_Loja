package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Service.FilialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filial")
@Tag(name = "Filiais", description = "Gerenciados das Filiais da Loja")
public class FilialController {
    @Autowired
    private FilialService filialService;

    @Operation(summary = "Obter todas Filiais", description = "Obtem todas as Filiais existentes no Banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não possui nenhuma filial cadastrada no Banco.")
    })
    @GetMapping()
    public ResponseEntity<List<Filial>> getAllFilial() {
        return ResponseEntity.status(HttpStatus.OK).body(filialService.listar());
    }

    @Operation(summary = "Obter Filial por ID", description = "Obtem a filial com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado a Filial com base no ID fornecido.")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Filial> getByIdFilial(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(filialService.buscarPorId(id));
    }

    @Operation(summary = "Salvar Filial", description = "Salva uma nova filial no Banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação."),
            @ApiResponse(responseCode = "409", description = "Já existe uma filial cadastrada com o CNPJ fornecido.")
    })
    @PostMapping("/{idRequerinte}")
    public ResponseEntity<Filial> postNewFilial(@PathVariable Integer idRequerinte, @RequestBody @Valid Filial filial) {
        return ResponseEntity.status(HttpStatus.OK).body(filialService.salvar(idRequerinte, filial));
    }

    @Operation(summary = "Atualizar Filial", description = "Atualiza a filial com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não possui um registro desta filial cadastrada no Banco ou " +
                    "não foi encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação."),
            @ApiResponse(responseCode = "406", description = "Não é permitido alterar campos ID e CNPJ.")
    })
    @PutMapping("/{idRequerinte}")
    public ResponseEntity<Filial> putAlterarFilial(@PathVariable Integer idRequerinte, @RequestBody @Valid Filial filial) {
        return ResponseEntity.status(HttpStatus.OK).body(filialService.atualizar(idRequerinte, filial));
    }

    @Operation(summary = "Deletar Filial", description = "Deleta a filial com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado nenhuma filial com o ID fornecido ou não" +
                    " foi encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação.")
    })
    @DeleteMapping("/{idRequerinte}/id/{id}")
    public ResponseEntity<Void> deleteFilial(@PathVariable Integer idRequerinte, @PathVariable Integer id) {
        filialService.remover(idRequerinte, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
