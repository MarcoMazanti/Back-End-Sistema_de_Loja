package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import SistemaLoja.BackEnd.Security.Decript.Descriptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class ClienteFactory {
    @Setter
    private SecretKey chaveSimetrica;
    @Autowired
    private Criptografar criptografar;
    @Autowired
    private Descriptografar descriptografar;

    // Plain → Encripted

    // ClienteRecordOne
    public ClienteRecordOne plainToClienteRecordOne(Cliente cliente) {
        String id = criptografar.criptografar(chaveSimetrica, String.valueOf(cliente.getId()));
        String nome = criptografar.criptografar(chaveSimetrica, cliente.getNome());
        String cpfOrCnpj = criptografar.criptografar(chaveSimetrica, cliente.getCpfOrCnpj());
        String email = criptografar.criptografar(chaveSimetrica, cliente.getEmail());
        String telefone = criptografar.criptografar(chaveSimetrica, cliente.getTelefone());
        String fullAdress = criptografar.criptografar(chaveSimetrica, cliente.getFullAdress());
        String codCountry = criptografar.criptografar(chaveSimetrica, cliente.getCodCountry());
        String codEstado = criptografar.criptografar(chaveSimetrica, cliente.getCodEstado());
        String codCidade = criptografar.criptografar(chaveSimetrica, cliente.getCodCidade());
        String codCliente = criptografar.criptografar(chaveSimetrica, cliente.getCodCliente());

        return new ClienteRecordOne(id, nome, cpfOrCnpj,email, telefone, fullAdress, codCountry, codEstado, codCidade, codCliente);
    }

    // ClienteRecordTwo
    public ClienteRecordTwo plainToClienteRecordTwo(Cliente cliente) {
        String nome = criptografar.criptografar(chaveSimetrica, cliente.getNome());
        String cpfOrCnpj = criptografar.criptografar(chaveSimetrica, cliente.getCpfOrCnpj());
        String email = criptografar.criptografar(chaveSimetrica, cliente.getEmail());
        String telefone = criptografar.criptografar(chaveSimetrica, cliente.getTelefone());
        String fullAdress = criptografar.criptografar(chaveSimetrica, cliente.getFullAdress());
        String codCliente = criptografar.criptografar(chaveSimetrica, cliente.getCodCliente());

        return new ClienteRecordTwo(nome, cpfOrCnpj, email, telefone, fullAdress, codCliente);
    }

    // ClienteRecordThree
    public ClienteRecordThree plainToClienteRecordThree(Cliente cliente) {
        String nome = criptografar.criptografar(chaveSimetrica, cliente.getNome());
        String email = criptografar.criptografar(chaveSimetrica, cliente.getEmail());
        String telefone = criptografar.criptografar(chaveSimetrica, cliente.getTelefone());
        String codCliente = criptografar.criptografar(chaveSimetrica, cliente.getCodCliente());

        return new ClienteRecordThree(nome, email, telefone, codCliente);
    }

    // Encripted → Plain
    public Cliente encriptedToPlainCliente(ClienteRecordOne clienteRecordOne) {
        int id = (clienteRecordOne.id() != null) ? Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.id())) : null;
        String nome = descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.nome());
        String cpfOrCnpj = descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.cpfOrCnpj());
        String email = descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.email());
        String telefone = (clienteRecordOne.telefone() != null) ? descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.telefone()) : null;
        String fullAdress = (clienteRecordOne.fullAdress() != null) ? descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.fullAdress()) : null;
        String codCountry = descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.codCountry());
        String codEstado = descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.codEstado());
        String codCidade = descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.codCidade());
        String codCliente = (clienteRecordOne.codCliente() != null) ? descriptografar.descriptografar(chaveSimetrica, clienteRecordOne.codCliente()) : null;

        return new Cliente(id, nome, cpfOrCnpj, email, telefone, fullAdress, codCountry, codEstado, codCidade, codCliente);
    }
}
