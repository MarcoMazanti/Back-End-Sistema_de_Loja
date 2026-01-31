package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Fornecedor.Fornecedor;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import SistemaLoja.BackEnd.Security.Decript.Descriptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class FornecedorFactory {
    @Setter
    private SecretKey chaveSimetrica;
    @Autowired
    private Criptografar criptografar;
    @Autowired
    private Descriptografar descriptografar;

    // Plain → Encripted

    // FornecedorRecordOne
    public FornecedorRecordOne plainToFornecedorRecordOne(Fornecedor fornecedor) {
        String id = criptografar.criptografar(chaveSimetrica, String.valueOf(fornecedor.getId()));
        String nome = criptografar.criptografar(chaveSimetrica, fornecedor.getNome());
        String cpfOrCnpj = criptografar.criptografar(chaveSimetrica, fornecedor.getCpfOrCnpj());
        String email = criptografar.criptografar(chaveSimetrica, fornecedor.getEmail());
        String telefone = criptografar.criptografar(chaveSimetrica, fornecedor.getTelefone());
        String fullAdress = criptografar.criptografar(chaveSimetrica, fornecedor.getFullAdress());
        String codCountry = criptografar.criptografar(chaveSimetrica, fornecedor.getCodCountry());
        String codEstado = criptografar.criptografar(chaveSimetrica, fornecedor.getCodEstado());
        String codCidade = criptografar.criptografar(chaveSimetrica, fornecedor.getCodCidade());
        String codFornecedor = criptografar.criptografar(chaveSimetrica, fornecedor.getCodForncedor());

        return new FornecedorRecordOne(id, nome, cpfOrCnpj, email, telefone, fullAdress, codCountry, codEstado, codCidade, codFornecedor);
    }

    // FornecedorRecordTwo
    public FornecedorRecordTwo plainToFornecedorRecordTwo(Fornecedor fornecedor) {
        String nome = criptografar.criptografar(chaveSimetrica, fornecedor.getNome());
        String cpfOrCnpj = criptografar.criptografar(chaveSimetrica, fornecedor.getCpfOrCnpj());
        String email = criptografar.criptografar(chaveSimetrica, fornecedor.getEmail());
        String telefone = criptografar.criptografar(chaveSimetrica, fornecedor.getTelefone());
        String fullAdress = criptografar.criptografar(chaveSimetrica, fornecedor.getFullAdress());
        String codFornecedor = criptografar.criptografar(chaveSimetrica, fornecedor.getCodForncedor());

        return new FornecedorRecordTwo(nome, cpfOrCnpj, email, telefone, fullAdress, codFornecedor);
    }

    // FornecedorRecordThree
    public FornecedorRecordThree plainToFornecedorRecordThree(Fornecedor fornecedor) {
        String nome = criptografar.criptografar(chaveSimetrica, fornecedor.getNome());
        String email = criptografar.criptografar(chaveSimetrica, fornecedor.getEmail());
        String telefone = criptografar.criptografar(chaveSimetrica, fornecedor.getTelefone());
        String codFornecedor = criptografar.criptografar(chaveSimetrica, fornecedor.getCodForncedor());

        return new FornecedorRecordThree(nome, email, telefone, codFornecedor);
    }

    // Encripted → Plain
    public Fornecedor encriptedToPlainFornecedor(FornecedorRecordOne fornecedorRecordOne) {
        int id = (fornecedorRecordOne.id() != null) ? Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.id())) : null;
        String nome = descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.nome());
        String cpfOrCnpj = descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.cpfOrCnpj());
        String email = descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.email());
        String telefone = (fornecedorRecordOne.telefone() != null) ? descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.telefone()) : null;
        String fullAdress = (fornecedorRecordOne.fullAdress() != null) ? descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.fullAdress()) : null;
        String codCountry = descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.codCountry());
        String codEstado = descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.codEstado());
        String codCidade = descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.codCidade());
        String codFornecedor = (fornecedorRecordOne.codFornecedor() != null) ? descriptografar.descriptografar(chaveSimetrica, fornecedorRecordOne.codFornecedor()) : null;

        return new Fornecedor(id, nome, cpfOrCnpj, email, telefone, fullAdress, codCountry, codEstado, codCidade, codFornecedor);
    }
}
