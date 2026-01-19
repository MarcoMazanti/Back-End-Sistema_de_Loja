package SistemaLoja.BackEnd.Repository;

import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Integer> {
    List<Empregado> findAllByFilialId(Integer filialId);
    Optional<Empregado> findByCpf(String cpf);
}
