package SistemaLoja.BackEnd.Service.Interface;

public interface RequestRequerinteInterface<T> {
    T salvar(Integer idRequerinte, T item);
    T atualizar(Integer idRequerinte, T item);
    void remover(Integer idRequerinte, Integer id);
}
