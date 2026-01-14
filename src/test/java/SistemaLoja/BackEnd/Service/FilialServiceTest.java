package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Repository.EmpregadoRepository;
import SistemaLoja.BackEnd.Repository.FilialRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FilialServiceTest {
    @InjectMocks
    private FilialService filialService;

    @Mock
    private FilialRepository filialRepository;

    @Mock
    private EmpregadoRepository empregadoRepository;

    private List<Filial> filialList = new ArrayList<>();

    @BeforeEach
    public void beforeEachTest() {
        filialList.add(new Filial(1, "12345678901234", "18996357788", 10, "Endereço Completo", "BR", "SP", "SP", "BR-SP-SP-FIL-1"));
        filialList.add(new Filial(2, "01234567890123","89963577881", 20, "Endereço Completo", "BR", "SP", "SP", "BR-SP-SP-FIL-1"));
    }

    @Test
    void obterTodasFiliais() {
        Mockito.when(filialRepository.findAll()).thenReturn(filialList);
        List<Filial> listaFiliais = filialService.obterTodasFiliais();

        Assertions.assertEquals(2, listaFiliais.size());
    }

    @Test
    void publicarNovaFilial() {
    }

    @Test
    void atualizarFilial() {
    }

    @Test
    void deletarFilial() {
    }
}