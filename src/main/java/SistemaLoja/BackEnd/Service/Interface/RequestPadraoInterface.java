package SistemaLoja.BackEnd.Service.Interface;

public interface RequestPadraoInterface<T> {
    T salvar(T item);
    T atualizar(T item);
    void remover(Integer id);
}
