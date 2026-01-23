package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Fornecedor.Fornecedor;
import org.springframework.stereotype.Service;

@Service
public class FornecedorFactory {
    // Plain → Encripted

    // FornecedorRecordOne
    public FornecedorRecordOne plainToFornecedorRecordOne(Fornecedor fornecedor) {
        String id = String.valueOf(fornecedor.getId());
        String nome = fornecedor.getNome();
        String cpfOrCnpj = fornecedor.getCpfOrCnpj();
        String email = fornecedor.getEmail();
        String telefone = fornecedor.getTelefone();
        String fullAdress = fornecedor.getFullAdress();
        String codCountry = fornecedor.getCodCountry();
        String codEstado = fornecedor.getCodEstado();
        String codCidade = fornecedor.getCodCidade();
        String codFornecedor = fornecedor.getCodForncedor();

        return new FornecedorRecordOne(id, nome, cpfOrCnpj, email, telefone, fullAdress, codCountry, codEstado, codCidade, codFornecedor);
    }

    // FornecedorRecordTwo
    public FornecedorRecordTwo plainToFornecedorRecordTwo(Fornecedor fornecedor) {
        String nome = fornecedor.getNome();
        String cpfOrCnpj = fornecedor.getCpfOrCnpj();
        String email = fornecedor.getEmail();
        String telefone = fornecedor.getTelefone();
        String fullAdress = fornecedor.getFullAdress();
        String codFornecedor = fornecedor.getCodForncedor();

        return new FornecedorRecordTwo(nome, cpfOrCnpj, email, telefone, fullAdress, codFornecedor);
    }

    // FornecedorRecordThree
    public FornecedorRecordThree plainToFornecedorRecordThree(Fornecedor fornecedor) {
        String nome = fornecedor.getNome();
        String email = fornecedor.getEmail();
        String telefone = fornecedor.getTelefone();
        String codFornecedor = fornecedor.getCodForncedor();

        return new FornecedorRecordThree(nome, email, telefone, codFornecedor);
    }

    // Encripted → Plain
}
