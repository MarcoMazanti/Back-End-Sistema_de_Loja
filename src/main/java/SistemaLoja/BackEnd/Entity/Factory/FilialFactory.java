package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Filial.FilialRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Filial.FilialRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Filial.FilialRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import org.springframework.stereotype.Service;

@Service
public class FilialFactory {
    // Plain → Encripted

    // FilialRecordOne
    public FilialRecordOne plainToFilialRecordOne(Filial filial) {
        String id = String.valueOf(filial.getId());
        String cnpj = filial.getCnpj();
        String telefone = filial.getTelefone();
        String quantEmpregados = String.valueOf(filial.getQuantEmpregados());
        String fullAdress = filial.getFullAdress();
        String codCountry = filial.getCodCountry();
        String codEstado = filial.getCodEstado();
        String codCidade = filial.getCodCidade();
        String codFilial = filial.getCodFilial();

        return new FilialRecordOne(id, cnpj, telefone, quantEmpregados, fullAdress, codCountry, codEstado, codCidade, codFilial);
    }

    // FilialRecordTwo
    public FilialRecordTwo plainToFilialRecordTwo(Filial filial) {
        String cnpj = filial.getCnpj();
        String telefone = filial.getTelefone();
        String quantEmpregados = String.valueOf(filial.getQuantEmpregados());
        String fullAdress = filial.getFullAdress();
        String codFilial = filial.getCodFilial();

        return new FilialRecordTwo(cnpj, telefone, quantEmpregados, fullAdress, codFilial);
    }

    // FilialRecordThree
    public FilialRecordThree plainToFilialRecordThree(Filial filial) {
        String telefone = filial.getTelefone();
        String quantEmpregados = String.valueOf(filial.getQuantEmpregados());
        String codFilial = filial.getCodFilial();

        return new FilialRecordThree(telefone, quantEmpregados, codFilial);
    }

    // Encripted → Plain
}
