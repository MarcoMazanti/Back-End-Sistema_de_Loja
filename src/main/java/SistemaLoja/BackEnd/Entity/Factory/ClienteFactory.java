package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteFactory {
    @Setter
    private String chaveSimetrica;
    @Autowired
    private Criptografar criptografar;

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
}
