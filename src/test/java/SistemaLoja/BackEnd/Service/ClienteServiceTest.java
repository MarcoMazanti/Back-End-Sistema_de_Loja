package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TipoCargo;
import SistemaLoja.BackEnd.Repository.ClienteRepository;
import SistemaLoja.BackEnd.Repository.EmpregadoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EmpregadoRepository empregadoRepository;

    private final List<Cliente> clienteList = new ArrayList<>();
    private final List<Empregado> empregadoList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        clienteList.add(new Cliente(1, "João da Silva", "12345678901", "joao.silva@email.com",
                "11987654321", "Rua das Flores, 123 - Centro", "055", "035", "001", "055-035-001-CLI-1"
        ));
        clienteList.add(new Cliente(2, "Empresa XPTO LTDA", "12345678000199", "contato@xpto.com",
                "1133445566","Av. Paulista, 1000 - Bela Vista", "BR", "SP", "SP", "BR-SP-SP-CLI-2"
        ));

        empregadoList.add(new Empregado(1,"Ana Silva", "12345678901", "senha123", "ana.silva@email.com",
                "11988887777", new BigDecimal("4500.00"), TipoCargo.EMPREGADO, 1, new Date(), new Date(), "1-EMP-1"));
        empregadoList.add(new Empregado(2,"Bruno Santos", "23456789012", "senha456", "bruno.santos@email.com",
                "11977776666", new BigDecimal("3200.00"), TipoCargo.DONO, 1, new Date(), new Date(), "1-EMP-2"));
        empregadoList.add(new Empregado(3, "Carla Oliveira", "34567890123", "senha789", "carla.o@email.com",
                "21966665555", new BigDecimal("7500.00"), TipoCargo.GERENTE, 2, new Date(), new Date(), "1-EMP-3"));
    }

    @Test
    void listar() {
        Mockito.when(clienteRepository.findAll()).thenReturn(clienteList);

        Assertions.assertEquals(clienteList, clienteService.listar());
    }

    @Test
    void buscarPorId() {
        Mockito.when(clienteRepository.findById(anyInt())).thenReturn(Optional.ofNullable(clienteList.get(0)));

        Assertions.assertEquals(clienteList.get(0), clienteService.buscarPorId(1));
    }

    @Test
    void salvar() {
        Cliente novoCliente = new Cliente("João da Silva", "12345678901", "joao.silva@email.com",
                "11987654321", "Rua das Flores, 123 - Centro", "BR", "SP", "SP", "BR-SP-SP-CLI-1");
        Mockito.when(clienteRepository.findByCpfOrCnpj(anyString())).thenReturn(Optional.ofNullable(null));
        Mockito.when(clienteRepository.save(novoCliente)).thenReturn(novoCliente);
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(1)));

        Assertions.assertEquals(novoCliente, clienteService.salvar(1, novoCliente));
    }

    @Test
    void atualizar() {
        Cliente atualizarClietne = new Cliente(1, "João da Silva", "12345678901", "joao.silva@email.com",
                "11987654321", "Rua das Flores, 123 - Centro", "BR", "SP", "SP", "BR-SP-SP-CLI-1");
        Mockito.when(clienteRepository.findById(anyInt())).thenReturn(Optional.ofNullable(clienteList.get(0)));
        Mockito.when(clienteRepository.save(atualizarClietne)).thenReturn(atualizarClietne);
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(1)));

        Assertions.assertEquals(atualizarClietne, clienteService.atualizar(1, atualizarClietne));
    }

    @Test
    void remover() {
        Mockito.when(clienteRepository.findById(anyInt())).thenReturn(Optional.ofNullable(clienteList.get(0)));
        Mockito.doNothing().when(clienteRepository).deleteById(anyInt());
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(1)));

        clienteService.remover(1, 1);
    }
}