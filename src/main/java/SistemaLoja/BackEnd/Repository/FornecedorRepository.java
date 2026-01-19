package SistemaLoja.BackEnd.Repository;

import SistemaLoja.BackEnd.Entity.Plain.Fornecedor.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    @Query("SELECT f FROM fornecedor f WHERE f.cpfOrCnpj = :cpfOrCnpj")
    Optional<Fornecedor> findByCpfOrCnpj(@Param("cpfOrCnpj") String cpfOrCnpj);
}
