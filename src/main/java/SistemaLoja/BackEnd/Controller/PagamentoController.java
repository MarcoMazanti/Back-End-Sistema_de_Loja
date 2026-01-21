package SistemaLoja.BackEnd.Controller;

import SistemaLoja.BackEnd.Entity.Plain.Pagamento.ItemPagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.Pagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.PagamentoPayload;
import SistemaLoja.BackEnd.Service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    // GET - Pagamento
    @GetMapping("/generic")
    public ResponseEntity<List<Pagamento>> getAllPagamento() {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarPagamento());
    }

    @GetMapping("/generic/id/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.buscarPagamentoPorId(id));
    }

    @GetMapping("/generic/id_cliente/{idCliente}")
    public ResponseEntity<List<Pagamento>> getAllPagamentoByIdCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarPagamentoPorIdCliente(idCliente));
    }

    @GetMapping("/generic/id_filial/{idFilial}")
    public ResponseEntity<List<Pagamento>> getAllPagamentoByIdFilial(@PathVariable Integer idFilial) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarPagamentoPorIdFilial(idFilial));
    }

    // GET - Item Pagamento
    @GetMapping("/specs")
    public ResponseEntity<List<ItemPagamento>> getAllItemPagamento() {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarItem());
    }

    @GetMapping("/specs/id/{id}")
    public ResponseEntity<ItemPagamento> getItemPagamentoById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.buscarItemPorId(id));
    }

    @GetMapping("/specs/id_pagamento/{idPagamento}")
    public ResponseEntity<List<ItemPagamento>> getAllItemPagamentoByIdPagamento(@PathVariable Integer idPagamento) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarItemPorIdPagamento(idPagamento));
    }

    @GetMapping("/specs/id_item/{idItem}")
    public ResponseEntity<List<ItemPagamento>> getAllItemPagamentoByIdItem(@PathVariable Integer idItem) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarItemPorIdItem(idItem));
    }

    // POST
    @PostMapping
    public ResponseEntity<PagamentoPayload> efetuarVenda(@RequestBody PagamentoPayload pagamentoPayload) {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.salvar(pagamentoPayload));
    }
}
