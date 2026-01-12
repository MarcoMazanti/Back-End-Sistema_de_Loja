package SistemaLoja.BackEnd.Repository;

import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
