package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Login;
import SistemaLoja.BackEnd.Service.EmpregadoService;
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
@RequestMapping("/api/empregado")
@Tag(name = "Empregados", description = "Gerenciamento dos Empregados da Loja")
public class EmpregadoController {
    @Autowired
    private EmpregadoService empregadoService;

    @Operation(summary = "Obter todos Funcionários", description = "Obtem todos os registros de funcionários do Banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não existe nenhum registro de empregados no Banco.")
    })
    @GetMapping
    public ResponseEntity<List<Empregado>> getAllEmpregados() {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.listar());
    }

    @Operation(summary = "Obter Empregado por ID", description = "Otem o Empregado com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado nenhum registro de empregado com base no ID fornecido.")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Empregado> getEmpregadoById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.buscarPorId(id));
    }

    @Operation(summary = "Obtem Empregados por filialId", description = "Otem todos os Empregados de uma filial.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi obtido nenhum empregado esta filial.")
    })
    @GetMapping("/filial_id/{filialId}")
    public ResponseEntity<List<Empregado>> getAllEmpregadosByFilialId(@PathVariable Integer filialId) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.listarPorFilialId(filialId));
    }

    @Operation(summary = "Login", description = "Efetuar login no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o empregado com base no CPF fornecido."),
            @ApiResponse(responseCode = "401", description = "Login não autorizado, senha inválida.")
    })
    @PostMapping("/login")
    public ResponseEntity<Empregado> efetuarLogin(@RequestBody Login login) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.efetuarLogin(login));
    }

    @Operation(summary = "Salvar Empregado", description = "Registra um novo Empregado no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não possui uma Filial com o filialId fornecido ou não foi" +
                    " encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para esta ação."),
            @ApiResponse(responseCode = "409", description = "Já possui um Empregado cadastrado com o mesmo CPF.")
    })
    @PostMapping("/{idRequerinte}")
    public ResponseEntity<Empregado> postNewEmpregado(@PathVariable Integer idRequerinte, @RequestBody Empregado empregado) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.salvar(idRequerinte, empregado));
    }

    @Operation(summary = "Atualizar Empregado", description = "Atualizar o registro do Empregado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado a conta para alteração ou não foi" +
                    " encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para essa ação."),
            @ApiResponse(responseCode = "406", description = "Não pode alterar dados fundamentais da própria conta.")
    })
    @PutMapping("/{idRequerinte}")
    public ResponseEntity<Empregado> putEmpregado(@PathVariable Integer idRequerinte, @RequestBody Empregado empregado) {
        return  ResponseEntity.status(HttpStatus.OK).body(empregadoService.atualizar(idRequerinte, empregado));
    }

    @Operation(summary = "Deletar Empregado", description = "Deleta o registro do Empregado com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado a conta para deleção, erro ao obter a filial para atualização ou não foi" +
                    " encontrado a conta do Requerinte para validação."),
            @ApiResponse(responseCode = "401", description = "Requerinte não possui permissão para essa ação."),
    })
    @DeleteMapping("/{idRequerinte}/id/{id}")
    public ResponseEntity<Void> deleteEmpregado(@PathVariable Integer idRequerinte, @PathVariable Integer id) {
        empregadoService.remover(idRequerinte, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
