package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TipoCargo;
import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Exception.RegistroInexistenteException;
import SistemaLoja.BackEnd.Exception.RegistroJaExistenteException;
import SistemaLoja.BackEnd.Exception.TabelaVaziaException;
import SistemaLoja.BackEnd.Exception.TamanhoInvalidoCampoException;
import SistemaLoja.BackEnd.Repository.EmpregadoRepository;
import SistemaLoja.BackEnd.Repository.FilialRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class FilialServiceTest {
    @InjectMocks
    private FilialService filialService;

    @Mock
    private FilialRepository filialRepository;

    @Mock
    private EmpregadoRepository empregadoRepository;

    private final List<Filial> filialList = new ArrayList<>();
    private final List<Empregado> empregadoList = new ArrayList<>();

    // criação inicial de um "banco de dados"
    @BeforeEach
    public void beforeEachTest() {
        filialList.add(new Filial(1, "12345678901234", "18996357788", 10, "Endereço Completo",
                "BR", "SP", "SP", "BR-SP-SP-FIL-1"));
        filialList.add(new Filial(2, "01234567890123","89963577881", 20, "Endereço Completo",
                "BR", "SP", "SP", "BR-SP-SP-FIL-1"));

        empregadoList.add(new Empregado(1,"Ana Silva", "12345678901", "senha123", "ana.silva@email.com",
                "11988887777", new BigDecimal("4500.00"), TipoCargo.EMPREGADO, 1, new Date(), new Date(), "1-EMP-1"));
        empregadoList.add(new Empregado(2,"Bruno Santos", "23456789012", "senha456", "bruno.santos@email.com",
                "11977776666", new BigDecimal("3200.00"), TipoCargo.DONO, 1, new Date(), new Date(), "1-EMP-2"));
        empregadoList.add(new Empregado(3, "Carla Oliveira", "34567890123", "senha789", "carla.o@email.com",
                "21966665555", new BigDecimal("7500.00"), TipoCargo.GERENTE, 2, new Date(), new Date(), "1-EMP-3"));
    }

    // Verificação dos lançamentos de Exceptions
    @Test
    @DisplayName("Verifica se a tabela está vazia")
    void retornarExceptionTabelaVazia() {
        Mockito.when(filialRepository.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertThrows(TabelaVaziaException.class, () -> filialService.listar());
    }

    @Test
    @DisplayName("Exceptio Requerinte Não Autorizado")
    void retornarExceptionRequerinteNaoAutorizado() {
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(RegistroInexistenteException.class, () -> filialService.salvar(1, new Filial()));
        Assertions.assertThrows(RegistroInexistenteException.class, () -> filialService.salvar(3, new Filial()));

    }

    @Test
    @DisplayName("Exception Registro Inexistente em Emprego")
    void retornarExceptionRegistroInexistenteEmpregado() {
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(RegistroInexistenteException.class, () -> filialService.salvar(4, new Filial()));
    }

    @Test
    @DisplayName("Exception Registro Inexistente em Filial")
    void retornarExceptionRegistroInexistenteFilial() {
        Filial filialAtualizada = new Filial(4, "61234567890123", "18996357788", 15, "Endereço Completo",
                "BR", "SP", "SP", "BR-SP-SP-FIL-1");
        Mockito.when(filialRepository.findById(anyInt())).thenReturn(Optional.empty());
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(1)));

        Assertions.assertThrows(RegistroInexistenteException.class, ()-> filialService.atualizar(2, filialAtualizada));
    }

    @Test
    @DisplayName("Exception Registro Já existente")
    void retornarExceptionRegistroJaExistente() {
        Filial novaFilial = new Filial(1, "12345678901234", "18996357788", 10, "Endereço Completo",
                "BR", "SP", "SP", "BR-SP-SP-FIL-1");
        Mockito.when(filialRepository.findByCnpj(anyString())).thenReturn(Optional.ofNullable(filialList.get(0)));
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(1)));

        Assertions.assertThrows(RegistroJaExistenteException.class, () -> filialService.salvar(2, novaFilial));
    }

    @Test
    @DisplayName("Exception Tamanho Inválido para CNPJ")
    void cnpjInvalido() {
        Assertions.assertThrows(TamanhoInvalidoCampoException.class, () -> new Filial("0000", "01234567890",
                "Endereço Completo", "BR", "SP", "SP"));
        Assertions.assertThrows(TamanhoInvalidoCampoException.class, () -> new Filial("0000000000000000", "01234567890",
                "Endereço Completo", "BR", "SP", "SP"));
    }

    @Test
    @DisplayName("Exception Tamanho Inválido para Telefone")
    void telefoneInvalido() {
        Assertions.assertThrows(TamanhoInvalidoCampoException.class, () -> new Filial("00000000000000", "012345",
                "Endereço Completo", "BR", "SP", "SP"));
        Assertions.assertThrows(TamanhoInvalidoCampoException.class, () -> new Filial("00000000000000", "012345678901560568",
                "Endereço Completo", "BR", "SP", "SP"));
    }

    // Verificação dos retornos das funções de FilialService
    @Test
    @DisplayName("Obter todas as Filiais")
    void obterTodasFiliais() {
        Mockito.when(filialRepository.findAll()).thenReturn(filialList);
        List<Filial> listaFiliais = filialService.listar();

        Assertions.assertEquals(2, listaFiliais.size());
    }

    @Test
    @DisplayName("Salvar nova Filial")
    void publicarNovaFilial() {
        Filial novaFilial = new Filial(3, "30123456789012","99635778818", 20, "Endereço Completo", "BR", "SP", "SP", "BR-SP-SP-FIL-1");
        Mockito.when(filialRepository.save(novaFilial)).thenReturn(novaFilial);
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(1)));

        Filial filial = filialService.salvar(2, novaFilial);

        Assertions.assertEquals(novaFilial, filial);
    }

    @Test
    @DisplayName("Atualizar Filial")
    void atualizarFilial() {
        Filial filialAtualizada = new Filial(1, "12345678901234", "18996357788", 20, "Endereço Completo",
                "BR", "SP", "SP", "BR-SP-SP-FIL-1");
        Mockito.when(filialRepository.save(any())).thenReturn(filialAtualizada);
        Mockito.when(filialRepository.findById(anyInt())).thenReturn(Optional.of(filialAtualizada));
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(1)));

        Filial filial = filialService.atualizar(2, filialAtualizada);

        Assertions.assertEquals(filialAtualizada, filial);
    }

    @Test
    @DisplayName("Deletar Filial")
    void deletarFilial() {
        Filial filial = new Filial(1, "12345678901234", "18996357788", 10, "Endereço Completo",
                "BR", "SP", "SP", "BR-SP-SP-FIL-1");
        Mockito.when(filialRepository.findById(anyInt())).thenReturn(Optional.of(filial));
        Mockito.doNothing().when(filialRepository).deleteById(anyInt());
        Mockito.when(empregadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(empregadoList.get(1)));

        filialService.remover(2, filial.getId());
    }
}