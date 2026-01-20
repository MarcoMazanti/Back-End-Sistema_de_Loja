package SistemaLoja.BackEnd.Repository;

import SistemaLoja.BackEnd.Entity.Plain.Estoque.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
    Optional<Estoque> findByNomeAndIdFilialAndIdFornecedor(String nome, Integer idFilial, Integer idFornecedor);
    List<Estoque> findAllByIdFilial(Integer idFilial);
    List<Estoque> findAllByIdFornecedor(Integer idFornecedor);
}
