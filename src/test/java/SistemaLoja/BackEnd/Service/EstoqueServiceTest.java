package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Estoque.Estoque;
import SistemaLoja.BackEnd.Repository.EstoqueRepository;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class EstoqueServiceTest {
    @InjectMocks
    private EstoqueService estoqueService;

    @Mock
    private EstoqueRepository estoqueRepository;

    private final List<Estoque> estoqueList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        estoqueList.add(new Estoque(1, "item 1", 1, 1, new BigDecimal("50.00"),
                10, "descrição 1", "FIL-1-ITM-1"));
        estoqueList.add(new Estoque(2, "item 2", 1, 1, new BigDecimal("50.00"),
                10, "descrição 2", "FIL-1-ITM-2"));
    }

    @Test
    void listar() {
        Mockito.when(estoqueRepository.findAll()).thenReturn(estoqueList);
        Assertions.assertEquals(estoqueList, estoqueService.listar());
    }

    @Test
    void buscarPorId() {
        Mockito.when(estoqueRepository.findById(anyInt())).thenReturn(Optional.ofNullable(estoqueList.get(0)));
        Assertions.assertEquals(estoqueList.get(0), estoqueService.buscarPorId(1));
    }

    @Test
    void listarPorIdFilial() {
        Mockito.when(estoqueRepository.findAllByIdFilial(anyInt())).thenReturn(estoqueList);
        Assertions.assertEquals(estoqueList, estoqueService.listarPorIdFilial(1));
    }

    @Test
    void listarPorIdFornecedor() {
        Mockito.when(estoqueRepository.findAllByIdFornecedor(anyInt())).thenReturn(estoqueList);
        Assertions.assertEquals(estoqueList, estoqueService.listarPorIdFornecedor(1));
    }

    @Test
    void salvar() {
        Estoque novoEstoque = new Estoque(3, "item 3", 1, 1, new BigDecimal("50.00"),
                10, "descrição 3", "FIL-1-ITM-3");
        Mockito.when(estoqueRepository.save(novoEstoque)).thenReturn(novoEstoque);
        Mockito.when(estoqueRepository.findByNomeAndIdFilialAndIdFornecedor(anyString(), anyInt(), anyInt())).thenReturn(Optional.ofNullable(null));

        Assertions.assertEquals(novoEstoque, estoqueService.salvar(novoEstoque));
    }

    @Test
    void atualizar() {
        Estoque atualizarEstoque = new Estoque(2, "item 2", 1, 1, new BigDecimal("50.00"),
                        20, "descrição 2", "FIL-1-ITM-2");
        Mockito.when(estoqueRepository.save(atualizarEstoque)).thenReturn(atualizarEstoque);
        Mockito.when(estoqueRepository.findByNomeAndIdFilialAndIdFornecedor(anyString(), anyInt(), anyInt())).thenReturn(Optional.ofNullable(estoqueList.get(1)));

        Assertions.assertEquals(atualizarEstoque, estoqueService.atualizar(atualizarEstoque));
    }

    @Test
    void remover() {
        Mockito.doNothing().when(estoqueRepository).deleteById(anyInt());
        Mockito.when(estoqueRepository.findById(anyInt())).thenReturn(Optional.ofNullable(estoqueList.get(0)));

        estoqueService.remover(1);
    }
}