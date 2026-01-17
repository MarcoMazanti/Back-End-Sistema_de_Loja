package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Login;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TipoCargo;
import SistemaLoja.BackEnd.Exception.LoginNaoAutorizadoException;
import SistemaLoja.BackEnd.Exception.SenhaNaoPermitidaException;
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
import java.time.LocalDateTime;
import java.util.*;

import static SistemaLoja.BackEnd.Security.GerarSenhaSaltHash.encriptarSenha;
import static org.hibernate.query.restriction.Restriction.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class EmpregadoServiceTest {
    @InjectMocks
    private EmpregadoService empregadoService;
    @Mock
    private EmpregadoRepository empregadoRepository;

    private final List<Empregado> empregadoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        empregadoList.add(new Empregado(1, "Ana Beatriz Silva", "12345678901", encriptarSenha("hash_senha_segur@a_1"),
                "ana.silva@empresa.com", "11988887777", new BigDecimal("5500.00"), TipoCargo.DONO, 1,
                new Date(70, Calendar.JUNE, 12), null, "1-EMP-1"));
        empregadoList.add(new Empregado(2, "Marcos Oliveira", "98765432100", encriptarSenha("senha@%456"),
                "marcos.adm@email.com", "21977776666", new BigDecimal("2800.50"), TipoCargo.GERENTE, 1,
                new Date(95, Calendar.OCTOBER, 20), new Date(), "1-EMP-2"));
    }

    // Teste da SenhaNaoPermitidaException
    @Test
    void validarSenha() {
        Empregado empregado1 = new Empregado("Ricardo Sanches", "44455566677", "hash_", "ricardo.sanches@empresa.com",
                new BigDecimal("9200.00"), 1);
        Empregado empregado2 = new Empregado("Ricardo Sanches", "44455566677", "hash_adsb", "ricardo.sanches@empresa.com",
                new BigDecimal("9200.00"), 1);
        Empregado empregado3 = new Empregado("Ricardo Sanches", "44455566677", "hash_adsb1", "ricardo.sanches@empresa.com",
                new BigDecimal("9200.00"), 1);
        Empregado empregado4 = new Empregado("Ricardo Sanches", "44455566677", "hash_adsb%", "ricardo.sanches@empresa.com",
                new BigDecimal("9200.00"), 1);
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(0)));

        Assertions.assertThrows(SenhaNaoPermitidaException.class, () -> empregadoService.postarNovoEmpregado(1, empregado1));
        Assertions.assertThrows(SenhaNaoPermitidaException.class, () -> empregadoService.postarNovoEmpregado(1, empregado2));
        Assertions.assertThrows(SenhaNaoPermitidaException.class, () -> empregadoService.postarNovoEmpregado(1, empregado3));
        Assertions.assertThrows(SenhaNaoPermitidaException.class, () -> empregadoService.postarNovoEmpregado(1, empregado4));
    }

    // Teste do LoginNaoAutorizadoException
    @Test
    void validarLogin() {
        Mockito.when(empregadoRepository.findByCpf(anyString())).thenReturn(Optional.ofNullable(empregadoList.get(0)));

        Assertions.assertThrows(LoginNaoAutorizadoException.class, () -> empregadoService.efetuarLogin(new Login("12345678901", "hash_adsb%3@")));
    }

    // Teste das funções de EmpregadoService
    @Test
    void coletarTodosEmpregados() {
        Mockito.when(empregadoRepository.findAll()).thenReturn(empregadoList);

        Assertions.assertEquals(empregadoList, empregadoService.coletarTodosEmpregados());
    }

    @Test
    void coletarEmpregadoById() {
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(0)));

        Assertions.assertEquals(empregadoList.get(0), empregadoService.coletarEmpregadoById(1));
    }

    @Test
    void coletarAllEmpregadoByFilialId() {
        Mockito.when(empregadoRepository.findAllByFilialId(anyInt())).thenReturn(empregadoList);

        Assertions.assertEquals(empregadoList, empregadoService.coletarAllEmpregadoByFilialId(1));
    }

    @Test
    void efetuarLogin() {
        Mockito.when(empregadoRepository.findByCpf(anyString())).thenReturn(Optional.ofNullable(empregadoList.get(0)));

        Assertions.assertEquals(empregadoList.get(0), empregadoService.efetuarLogin(new Login("12345678901", "hash_senha_segur@a_1")));
    }

    @Test
    void postarNovoEmpregado() {
        Empregado novoEmpregado = new Empregado("Ricardo Sanches", "44455566677", "hash_s@egura_88", "ricardo.sanches@empresa.com",
                new BigDecimal("9200.00"), 1);
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(0)));
        Mockito.when(empregadoRepository.save(novoEmpregado)).thenReturn(novoEmpregado);

        Assertions.assertEquals(novoEmpregado, empregadoService.postarNovoEmpregado(1, novoEmpregado));
    }

    @Test
    void atualizarEmpregado() {
        Empregado empregadoAtualizado = new Empregado(1, "Ana Beatriz Silva", "12345678901", encriptarSenha("hash_senha_segur@a_1"),
                "ana.silva@empresa.com", "11988887777", new BigDecimal("55000.00"), TipoCargo.DONO, 1,
                new Date(70, Calendar.JUNE, 12), null, "1-EMP-1");
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(0)));
        Mockito.when(empregadoRepository.findByCpf(anyString())).thenReturn(Optional.ofNullable(empregadoList.get(0)));
        Mockito.when(empregadoRepository.save(empregadoAtualizado)).thenReturn(empregadoAtualizado);

        Assertions.assertEquals(empregadoAtualizado, empregadoService.atualizarEmpregado(1, empregadoAtualizado));
    }

    @Test
    void deletarEmpregar() {
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(0)));
        Mockito.doNothing().when(empregadoRepository).deleteById(anyInt());

        Assertions.assertEquals("Deletado!", empregadoService.deletarEmpregar(1, 1));
    }
}