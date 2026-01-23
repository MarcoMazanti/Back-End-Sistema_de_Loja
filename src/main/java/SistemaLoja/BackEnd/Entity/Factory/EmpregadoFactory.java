package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import org.springframework.stereotype.Service;

@Service
public class EmpregadoFactory {
    // Plain → Encripted

    // EmpregadoRecordOne
    public EmpregadoRecordOne plainToEmpregadoRecordOne(Empregado empregado) {
        String id = String.valueOf(empregado.getId());
        String nome = empregado.getNome();
        String cpf = empregado.getCpf();
        String senha = empregado.getSenha();
        String email = empregado.getEmail();
        String telefone = empregado.getTelefone();
        String salario = String.valueOf(empregado.getSalario());
        String cargo = String.valueOf(empregado.getCargo());
        String filialId = String.valueOf(empregado.getFilialId());
        String aniversario = String.valueOf(empregado.getAniversario());
        String dataAdimissao = String.valueOf(empregado.getDataAdimissao());
        String codEmpregado = empregado.getCodEmpregado();

        return new EmpregadoRecordOne(id, nome, cpf, senha, email, telefone, salario, cargo, filialId, aniversario,dataAdimissao, codEmpregado);
    }

    // EmpregadoRecordTwo
    public EmpregadoRecordTwo plainToEmpregadoRecordTwo(Empregado empregado) {
        String nome = empregado.getNome();
        String cpf = empregado.getCpf();
        String email = empregado.getEmail();
        String telefone = empregado.getTelefone();
        String salario = String.valueOf(empregado.getSalario());
        String cargo = String.valueOf(empregado.getCargo());
        String dataAdimissao = String.valueOf(empregado.getDataAdimissao());
        String codEmpregado = empregado.getCodEmpregado();

        return new EmpregadoRecordTwo(nome, cpf, email, telefone, salario, cargo, dataAdimissao, codEmpregado);
    }

    // EmpregadoRecordThree
    public EmpregadoRecordThree plainToEmpregadoThree(Empregado empregado) {
        String nome = empregado.getNome();
        String email = empregado.getEmail();
        String telefone = empregado.getTelefone();
        String codEmpregado = empregado.getCodEmpregado();

        return new EmpregadoRecordThree(nome, email, telefone, codEmpregado);
    }

    // Encripted → Plain
}
