package SistemaLoja.BackEnd.Entity.Plain.Pagamento;

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
@Entity(name = "item_pagamento")
public class ItemPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    private int idPagamento;
    @NotNull
    private int idItem;
    @NotBlank
    private String nome;
    @NotNull
    private int quantidade;
    @NotNull
    @Digits(integer = 6, fraction = 2)
    private BigDecimal preco;

    // Responsável quando for envio de dados completos para cadastro
    public ItemPagamento(int idPagamento, int idItem, String nome, int quantidade, BigDecimal preco) {
        this.idPagamento = idPagamento;
        this.idItem = idItem;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemPagamento(int idItem, String nome, int quantidade, BigDecimal preco) {
        this.idItem = idItem;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
