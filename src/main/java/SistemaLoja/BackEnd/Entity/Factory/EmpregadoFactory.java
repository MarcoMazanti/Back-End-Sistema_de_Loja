package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Login;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TipoCargo;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.TrocarSenha;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import SistemaLoja.BackEnd.Security.Decript.Descriptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class EmpregadoFactory {
    @Setter
    private SecretKey chaveSimetrica;
    @Autowired
    private Criptografar criptografar;
    @Autowired
    private Descriptografar descriptografar;

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

    // Empregado
    public Empregado encriptedToPlainEmpregado(EmpregadoRecordOne empregadoRecordOne) {
        Integer id = (empregadoRecordOne.id() != null) ? Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.id())) : null;
        String nome = descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.nome());
        String cpf = descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.cpf());
        String senha = descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.senha());
        String email = descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.email());
        String telefone = (empregadoRecordOne.telefone() != null) ? descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.telefone()) : null;
        BigDecimal salario = new BigDecimal(descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.salario()));
        TipoCargo cargo = (empregadoRecordOne.cargo() != null) ? TipoCargo.valueOf(descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.cargo())) : null;
        Integer filialId = Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.filialId()));

        Date aniversario = null;
        if (empregadoRecordOne.aniversario() != null) {
            String aniversarioString = descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.aniversario());
            aniversario = new Date(Long.parseLong(aniversarioString));
        }

        Date dataAdimissao = null;
        if (empregadoRecordOne.dataAdimissao() != null) {
            String dataAdimissaoString = descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.dataAdimissao());
            dataAdimissao = new Date(Long.parseLong(dataAdimissaoString));
        }

        String codEmpregado = (empregadoRecordOne.codEmpregado() != null) ? descriptografar.descriptografar(chaveSimetrica, empregadoRecordOne.codEmpregado()) : null;

        return new Empregado(id, nome, cpf, senha, email, telefone, salario, cargo, filialId, aniversario, dataAdimissao, codEmpregado);
    }

    // Login
    public Login encriptedToPlainLogin(Login login) {
        String cpf = descriptografar.descriptografar(chaveSimetrica, login.cpf());
        String senha = descriptografar.descriptografar(chaveSimetrica, login.senha());

        return new Login(cpf, senha);
    }

    // TrocarSenha
    public TrocarSenha encriptedToPlainTrocarSenha(TrocarSenha trocarSenha) {
        String cpf = descriptografar.descriptografar(chaveSimetrica, trocarSenha.cpf());
        String email = descriptografar.descriptografar(chaveSimetrica, trocarSenha.email());
        String senha = descriptografar.descriptografar(chaveSimetrica, trocarSenha.senha());

        return new TrocarSenha(cpf, email, senha);
    }
}
