package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Login;
import SistemaLoja.BackEnd.Service.EmpregadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empregado")
public class EmpregadoController {
    @Autowired
    private EmpregadoService empregadoService;

    @GetMapping
    public ResponseEntity<List<Empregado>> getAllEmpregados() {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.coletarTodosEmpregados());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Empregado> getEmpregadoById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.coletarEmpregadoById(id));
    }

    @GetMapping("/filial_id/{filialId}")
    public ResponseEntity<List<Empregado>> getAllEmpregadosByFilialId(@PathVariable Integer filialId) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.coletarAllEmpregadoByFilialId(filialId));
    }

    @PostMapping("/login")
    public ResponseEntity<Empregado> efetuarLogin(@RequestBody Login login) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.efetuarLogin(login));
    }

    @PostMapping("/{idRequerinte}")
    public ResponseEntity<Empregado> postNewEmpregado(@PathVariable Integer idRequerinte, @RequestBody Empregado empregado) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.postarNovoEmpregado(idRequerinte, empregado));
    }

    @PutMapping("/{idRequerinte}")
    public ResponseEntity<Empregado> putEmpregado(@PathVariable Integer idRequerinte, @RequestBody Empregado empregado) {
        return  ResponseEntity.status(HttpStatus.OK).body(empregadoService.atualizarEmpregado(idRequerinte, empregado));
    }

    @DeleteMapping("/{idRequerinte}/id/{id}")
    public ResponseEntity<String> deleteEmpregado(@PathVariable Integer idRequerinte, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadoService.deletarEmpregar(idRequerinte, id));
    }
}
