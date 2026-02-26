package SistemaLoja.BackEnd.Entity.Plain.Pagamento;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "idPagamento", "idItem", "nome", "quantidade", "precoUnit"})
@Entity(name = "item_pagamento")
@Schema(description = "Responsável por armazenar todos os dados de um item do pagamento.")
public class ItemPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Schema(description = "Número de identificação", example = "1")
    private Integer id;
    @Schema(description = "Campo relacional apontando para um registro de Pagamento", example = "1")
    private Integer idPagamento;
    @NotNull
    @Schema(description = "Campo relacional apontando para um registro de Estoque", example = "1")
    private Integer idItem;
    @NotBlank
    @Schema(description = "Nome do item", example = "Nome Completo do item", maxLength = 255)
    private String nome;
    @NotNull
    @Schema(description = "Quantidade deste item comprado")
    private Integer quantidade;
    @NotNull
    @Digits(integer = 6, fraction = 2)
    @Schema(description = "Preço unitário do item comprado")
    private BigDecimal precoUnit;

    // Responsável quando for envio de dados completos para cadastro
    public ItemPagamento(Integer idPagamento, Integer idItem, String nome, Integer quantidade, BigDecimal precoUnit) {
        this.idPagamento = idPagamento;
        this.idItem = idItem;
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnit = precoUnit;
    }

    public ItemPagamento(Integer idItem, String nome, Integer quantidade, BigDecimal precoUnit) {
        this.idItem = idItem;
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnit = precoUnit;
    }
}
