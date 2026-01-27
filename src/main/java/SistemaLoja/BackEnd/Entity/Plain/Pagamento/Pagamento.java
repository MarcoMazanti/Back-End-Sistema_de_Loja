package SistemaLoja.BackEnd.Entity.Plain.Pagamento;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "idCliente", "idFilial", "precoTotal", "precoPago", "dataCompra", "codPagamento"})
@Entity(name = "pagamento")
@Schema(description = "Responsável por armazenar todos os dados de um pagamento.")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Schema(description = "Número de identificação", example = "1")
    private Integer id;
    @NotNull
    @Schema(description = "Campo relacional apontando para um registro de Cliente", example = "2")
    private int idCliente;
    @NotNull
    @Schema(description = "Campo relacional apontando para um registro de Filial", example = "3")
    private int idFilial;
    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Schema(description = "Preço total da compra realizada pelo cliente")
    private BigDecimal precoTotal;
    @Digits(integer = 8, fraction = 2)
    @Schema(description = "Preço já pago pelo cliente")
    private BigDecimal precoPago;
    @Schema(description = "Campo gerado automaticamente", defaultValue = "CURRENT_DATE")
    private Date dataCompra;
    @Column(name = "cod_pagamento", length = 24, insertable = false, updatable = false)
    @Schema(description = "Série única do pagamento gerado automaticamente", example = "FIL-3-VND-1")
    private String codPagamento;

    @PrePersist
    public void prePersist() {
        if (precoPago == null) {
            precoPago = BigDecimal.ZERO;
        }
        if (dataCompra == null) {
            dataCompra = new Date();
        }
    }

    // Responsável pela volta do PagamentoRecordOne quando descriptografar
    public Pagamento(int idCliente, BigDecimal precoTotal, BigDecimal precoPago, Date dataCompra, String codPagamento) {
        String[] listaDados = codPagamento.split("-");

        this.id = Integer.parseInt(listaDados[3]);
        this.idCliente = idCliente;
        this.idFilial = Integer.parseInt(listaDados[1]);
        this.precoTotal = precoTotal;
        this.precoPago = precoPago;
        this.dataCompra = dataCompra;
        this.codPagamento = codPagamento;
    }

    // Responsável apenas pelos campos NOT NULL no banco
    public Pagamento(int idCliente, int idFilial, BigDecimal precoTotal) {
        this.idCliente = idCliente;
        this.idFilial = idFilial;
        this.precoTotal = precoTotal;
    }

    public Pagamento(int idCliente, int idFilial, BigDecimal precoTotal, BigDecimal precoPago) {
        this.idCliente = idCliente;
        this.idFilial = idFilial;
        this.precoTotal = precoTotal;
        this.precoPago = precoPago;
    }

    public Pagamento(int idCliente, int idFilial) {
        this.idCliente = idCliente;
        this.idFilial = idFilial;
    }

    // Responsável quando for envio de dados completos para cadastro
    public Pagamento(int idCliente, int idFilial, BigDecimal precoTotal, BigDecimal precoPago, Date dataCompra, String codPagamento) {
        this.idCliente = idCliente;
        this.idFilial = idFilial;
        this.precoTotal = precoTotal;
        this.precoPago = precoPago;
        this.dataCompra = dataCompra;
        this.codPagamento = codPagamento;
    }
}
