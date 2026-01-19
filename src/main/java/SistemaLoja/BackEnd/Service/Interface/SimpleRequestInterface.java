package SistemaLoja.BackEnd.Service.Interface;

import java.util.List;

public interface SimpleRequestInterface<T> {
    List<T> listar();
    T buscarPorId(Integer id);
}
