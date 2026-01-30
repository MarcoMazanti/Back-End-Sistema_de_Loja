package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Filial.FilialRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Filial.FilialRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Filial.FilialRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilialFactory {
    @Setter
    private String chaveSimetrica;
    @Autowired
    private Criptografar criptografar;

    // Plain → Encripted

    // FilialRecordOne
    public FilialRecordOne plainToFilialRecordOne(Filial filial) {
        String id = criptografar.criptografar(chaveSimetrica, String.valueOf(filial.getId()));
        String cnpj = criptografar.criptografar(chaveSimetrica, filial.getCnpj());
        String telefone = criptografar.criptografar(chaveSimetrica, filial.getTelefone());
        String quantEmpregados = criptografar.criptografar(chaveSimetrica, String.valueOf(filial.getQuantEmpregados()));
        String fullAdress = criptografar.criptografar(chaveSimetrica, filial.getFullAdress());
        String codCountry = criptografar.criptografar(chaveSimetrica, filial.getCodCountry());
        String codEstado = criptografar.criptografar(chaveSimetrica, filial.getCodEstado());
        String codCidade = criptografar.criptografar(chaveSimetrica, filial.getCodCidade());
        String codFilial = criptografar.criptografar(chaveSimetrica, filial.getCodFilial());

        return new FilialRecordOne(id, cnpj, telefone, quantEmpregados, fullAdress, codCountry, codEstado, codCidade, codFilial);
    }

    // FilialRecordTwo
    public FilialRecordTwo plainToFilialRecordTwo(Filial filial) {
        String cnpj = criptografar.criptografar(chaveSimetrica, filial.getCnpj());
        String telefone = criptografar.criptografar(chaveSimetrica, filial.getTelefone());
        String quantEmpregados = criptografar.criptografar(chaveSimetrica, String.valueOf(filial.getQuantEmpregados()));
        String fullAdress = criptografar.criptografar(chaveSimetrica, filial.getFullAdress());
        String codFilial = criptografar.criptografar(chaveSimetrica, filial.getCodFilial());

        return new FilialRecordTwo(cnpj, telefone, quantEmpregados, fullAdress, codFilial);
    }

    // FilialRecordThree
    public FilialRecordThree plainToFilialRecordThree(Filial filial) {
        String telefone = criptografar.criptografar(chaveSimetrica, filial.getTelefone());
        String quantEmpregados = criptografar.criptografar(chaveSimetrica, String.valueOf(filial.getQuantEmpregados()));
        String codFilial = criptografar.criptografar(chaveSimetrica, filial.getCodFilial());

        return new FilialRecordThree(telefone, quantEmpregados, codFilial);
    }

    // Encripted → Plain
}
