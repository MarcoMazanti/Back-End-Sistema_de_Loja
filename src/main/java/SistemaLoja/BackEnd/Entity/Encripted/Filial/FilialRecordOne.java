package SistemaLoja.BackEnd.Entity.Encripted.Filial;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record FilialRecordOne(String telefone, String quantEmpregados, String codFilial) {
    @JsonCreator
    public FilialRecordOne(@JsonProperty("telefone") String telefone,
                           @JsonProperty("quantEmpregados") String quantEmpregados,
                           @JsonProperty("codFilial") String codFilial) {
        this.telefone = telefone;
        this.quantEmpregados = quantEmpregados;
        this.codFilial = codFilial;
    }
}
