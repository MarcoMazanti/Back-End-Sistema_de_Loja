package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpregadoFactory {
    @Setter
    private String chaveSimetrica;
    @Autowired
    private Criptografar criptografar;

    // Plain → Encripted

    // EmpregadoRecordOne
    public EmpregadoRecordOne plainToEmpregadoRecordOne(Empregado empregado) {
        String id = criptografar.criptografar(chaveSimetrica, String.valueOf(empregado.getId()));
        String nome = criptografar.criptografar(chaveSimetrica, empregado.getNome());
        String cpf = criptografar.criptografar(chaveSimetrica, empregado.getCpf());
        String senha = criptografar.criptografar(chaveSimetrica, empregado.getSenha());
        String email = criptografar.criptografar(chaveSimetrica, empregado.getEmail());
        String telefone = criptografar.criptografar(chaveSimetrica, empregado.getTelefone());
        String salario = criptografar.criptografar(chaveSimetrica, String.valueOf(empregado.getSalario()));
        String cargo = criptografar.criptografar(chaveSimetrica, String.valueOf(empregado.getCargo()));
        String filialId = criptografar.criptografar(chaveSimetrica, String.valueOf(empregado.getFilialId()));
        String aniversario = criptografar.criptografar(chaveSimetrica, String.valueOf(empregado.getAniversario()));
        String dataAdimissao = criptografar.criptografar(chaveSimetrica, String.valueOf(empregado.getDataAdimissao()));
        String codEmpregado = criptografar.criptografar(chaveSimetrica, empregado.getCodEmpregado());

        return new EmpregadoRecordOne(id, nome, cpf, senha, email, telefone, salario, cargo, filialId, aniversario,dataAdimissao, codEmpregado);
    }

    // EmpregadoRecordTwo
    public EmpregadoRecordTwo plainToEmpregadoRecordTwo(Empregado empregado) {
        String nome = criptografar.criptografar(chaveSimetrica, empregado.getNome());
        String cpf = criptografar.criptografar(chaveSimetrica, empregado.getCpf());
        String email = criptografar.criptografar(chaveSimetrica, empregado.getEmail());
        String telefone = criptografar.criptografar(chaveSimetrica, empregado.getTelefone());
        String salario = criptografar.criptografar(chaveSimetrica, String.valueOf(empregado.getSalario()));
        String cargo = criptografar.criptografar(chaveSimetrica, String.valueOf(empregado.getCargo()));
        String dataAdimissao = criptografar.criptografar(chaveSimetrica, String.valueOf(empregado.getDataAdimissao()));
        String codEmpregado = criptografar.criptografar(chaveSimetrica, empregado.getCodEmpregado());

        return new EmpregadoRecordTwo(nome, cpf, email, telefone, salario, cargo, dataAdimissao, codEmpregado);
    }

    // EmpregadoRecordThree
    public EmpregadoRecordThree plainToEmpregadoThree(Empregado empregado) {
        String nome = criptografar.criptografar(chaveSimetrica, empregado.getNome());
        String email = criptografar.criptografar(chaveSimetrica, empregado.getEmail());
        String telefone = criptografar.criptografar(chaveSimetrica, empregado.getTelefone());
        String codEmpregado = criptografar.criptografar(chaveSimetrica, empregado.getCodEmpregado());

        return new EmpregadoRecordThree(nome, email, telefone, codEmpregado);
    }

    // Encripted → Plain
}
