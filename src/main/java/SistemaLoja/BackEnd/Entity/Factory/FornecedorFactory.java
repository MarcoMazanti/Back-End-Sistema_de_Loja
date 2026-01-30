package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Fornecedor.Fornecedor;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorFactory {
    @Setter
    private String chaveSimetrica;
    @Autowired
    private Criptografar criptografar;

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
}
