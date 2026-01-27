package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Pagamento.ItemPagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.Pagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.PagamentoPayload;
import SistemaLoja.BackEnd.Service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamento")
@Tag(name = "Pagamento", description = "Gerenciamento de Pagamentos da Loja")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    // GET - Pagamento
    @Operation(summary = "Obter todos Pagamentos Genéricos", description = "Obtem todos os pagamentos genéricos presente no Banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não possui nenhum registro na tabela de Pagamento Genérico.")
    })
    @GetMapping("/generic")
    public ResponseEntity<List<Pagamento>> getAllPagamento() {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarPagamento());
    }

    @Operation(summary = "Obter Pagamento Genérico por ID", description = "Obtem o pagamento genérico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado o pagamento genérico utilizando o ID fornecido.")
    })
    @GetMapping("/generic/id/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.buscarPagamentoPorId(id));
    }

    @Operation(summary = "Obter todos Pagamentos Genéricos por idCliente", description = "Obtem todos os pagamentos genéricos com base no idCliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado os pagamentos genéricos utilizando o idCliente fornecido.")
    })
    @GetMapping("/generic/id_cliente/{idCliente}")
    public ResponseEntity<List<Pagamento>> getAllPagamentoByIdCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarPagamentoPorIdCliente(idCliente));
    }

    @Operation(summary = "Obter todos Pagamentos Genéricos por idFilial", description = "Obtem todos os pagamentos genéricos com base no idFilial.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado os pagamentos genéricos utilizando o idFilial fornecido.")
    })
    @GetMapping("/generic/id_filial/{idFilial}")
    public ResponseEntity<List<Pagamento>> getAllPagamentoByIdFilial(@PathVariable Integer idFilial) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarPagamentoPorIdFilial(idFilial));
    }

    // GET - Item Pagamento
    @Operation(summary = "Obter todos Itens Pagamentos", description = "Obtem todos os itens pagamentos presente no Banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não possui nenhum registro na tabela de Item Pagamento.")
    })
    @GetMapping("/specs")
    public ResponseEntity<List<ItemPagamento>> getAllItemPagamento() {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarItem());
    }

    @Operation(summary = "Obter Item Pagamento por ID", description = "Obtem o item pagamento com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado o pagamento utilizando o ID fornecido.")
    })
    @GetMapping("/specs/id/{id}")
    public ResponseEntity<ItemPagamento> getItemPagamentoById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.buscarItemPorId(id));
    }

    @Operation(summary = "Obter todos Itens Pagamentos por idPagamento", description = "Obtem todos os itens pagamentos com base no idPagamento fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado os itens pagamentos utilizando o idPagamento fornecido.")
    })
    @GetMapping("/specs/id_pagamento/{idPagamento}")
    public ResponseEntity<List<ItemPagamento>> getAllItemPagamentoByIdPagamento(@PathVariable Integer idPagamento) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarItemPorIdPagamento(idPagamento));
    }

    @Operation(summary = "Obter todos Itens Pagamentos por idItem", description = "Obtem todos os itens pagamentos com base no idItem fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Não foi encontrado os itens pagamentos utilizando o idItem fornecido.")
    })
    @GetMapping("/specs/id_item/{idItem}")
    public ResponseEntity<List<ItemPagamento>> getAllItemPagamentoByIdItem(@PathVariable Integer idItem) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarItemPorIdItem(idItem));
    }

    // POST
    @Operation(summary = "Salvar Pagamento", description = "Salva uma uma transação no banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "406", description = "Compra reprovada devido ao excesso do preço solicitado," +
                    " não pode comprar acima do presente no estoque ou erro ao salvar o pagamento."),
            @ApiResponse(responseCode = "400", description = "Não existe o item comprado com base no ID.")
    })
    @PostMapping
    public ResponseEntity<PagamentoPayload> efetuarVenda(@RequestBody PagamentoPayload pagamentoPayload) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.salvar(pagamentoPayload));
    }
}
