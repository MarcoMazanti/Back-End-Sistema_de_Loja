package SistemaLoja.BackEnd.Repository;

import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT c FROM cliente c WHERE c.cpfOrCnpj = :cpfOrCnpj")
    Optional<Cliente> findByCpfOrCnpj(@Param("cpfOrCnpj") String cpfOrCnpj);
}
