package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteFactory {
    // Plain → Encripted

    // ClienteRecordOne
    public ClienteRecordOne plainToClienteRecordOne(Cliente cliente) {
        String id = String.valueOf(cliente.getId());
        String nome = cliente.getNome();
        String cpfOrCnpj = cliente.getCpfOrCnpj();
        String email = cliente.getEmail();
        String telefone = cliente.getTelefone();
        String fullAdress = cliente.getFullAdress();
        String codCountry = cliente.getCodCountry();
        String codEstado = cliente.getCodEstado();
        String codCidade = cliente.getCodCidade();
        String codCliente = cliente.getCodCliente();

        return new ClienteRecordOne(id, nome, cpfOrCnpj,email, telefone, fullAdress, codCountry, codEstado, codCidade, codCliente);
    }

    // ClienteRecordTwo
    public ClienteRecordTwo plainToClienteRecordTwo(Cliente cliente) {
        String nome = cliente.getNome();
        String cpfOrCnpj = cliente.getCpfOrCnpj();
        String email = cliente.getEmail();
        String telefone = cliente.getTelefone();
        String fullAdress = cliente.getFullAdress();
        String codCliente = cliente.getCodCliente();

        return new ClienteRecordTwo(nome, cpfOrCnpj, email, telefone, fullAdress, codCliente);
    }

    // ClienteRecordThree
    public ClienteRecordThree plainToClienteRecordThree(Cliente cliente) {
        String nome = cliente.getNome();
        String email = cliente.getEmail();
        String telefone = cliente.getTelefone();
        String codCliente = cliente.getCodCliente();

        return new ClienteRecordThree(nome, email, telefone, codCliente);
    }

    // Encripted → Plain
}
