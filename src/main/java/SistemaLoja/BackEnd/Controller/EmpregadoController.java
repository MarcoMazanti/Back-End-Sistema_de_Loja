package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Service.EmpregadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empregado")
public class EmpregadoController {
    @Autowired
    private EmpregadoService empregadoService;


}
