package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Pagamento.*;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.ItemPagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.Pagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.PagamentoPayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagamentoGeralFactory {
    // Plain → Encripted

    // Pagamento
    // PagamentoRecordOne
    public PagamentoRecordOne plainToPagamentoRecordOne(Pagamento pagamento) {
        String id = String.valueOf(pagamento.getId());
        String idCliente = String.valueOf(pagamento.getIdCliente());
        String idFilial = String.valueOf(pagamento.getIdFilial());
        String precoTotal = String.valueOf(pagamento.getPrecoTotal());
        String precoPago = String.valueOf(pagamento.getPrecoPago());
        String dataCompra = String.valueOf(pagamento.getDataCompra());
        String codPagamento = pagamento.getCodPagamento();

        return new PagamentoRecordOne(id, idCliente, idFilial, precoTotal, precoPago, dataCompra, codPagamento);
    }

    // PagamentoRecordTwo
    public PagamentoRecordTwo plainToPagamentoRecordTwo(Pagamento pagamento) {
        String idCliente = String.valueOf(pagamento.getIdCliente());
        String precoTotal = String.valueOf(pagamento.getPrecoTotal());
        String precoPago = String.valueOf(pagamento.getPrecoPago());
        String dataCompra = String.valueOf(pagamento.getDataCompra());
        String codPagamento = pagamento.getCodPagamento();

        return new PagamentoRecordTwo(idCliente, precoTotal, precoPago, dataCompra, codPagamento);
    }


    // Item Pagamento
    //ItemPagamentoRecordOne
    public ItemPagamentoRecordOne plainToItemPagamentoRecordOne(ItemPagamento itemPagamento) {
        String id = String.valueOf(itemPagamento.getId());
        String idPagamento = String.valueOf(itemPagamento.getIdPagamento());
        String idItem = String.valueOf(itemPagamento.getIdItem());
        String nome = itemPagamento.getNome();
        String quantidade = String.valueOf(itemPagamento.getQuantidade());
        String preco = String.valueOf(itemPagamento.getPrecoUnit());

        return new ItemPagamentoRecordOne(id, idPagamento, idItem, nome, quantidade, preco);
    }

    // ItemPagamentoRecordTwo
    public ItemPagamentoRecordTwo plainToItemPagamentoRecordTwo(ItemPagamento itemPagamento) {
        String idPagamento = String.valueOf(itemPagamento.getIdPagamento());
        String idItem = String.valueOf(itemPagamento.getIdItem());
        String quantidade = String.valueOf(itemPagamento.getQuantidade());

        return new ItemPagamentoRecordTwo(idPagamento, idItem, quantidade);
    }

    // Pagamento Payload
    // Model One
    public PagamentoPayloadRecord plainToPagamentoPayloadRecordOne(PagamentoPayload pagamentoPayload) {
        Object pagamento = plainToPagamentoRecordOne(pagamentoPayload.getPagamento());
        List<Object> list = new ArrayList<>();

        for (ItemPagamento obj : pagamentoPayload.getItemPagamentoList()) {
            list.add(plainToItemPagamentoRecordOne(obj));
        }

        return new PagamentoPayloadRecord<>(pagamento, list);
    }

    // Model Two
    public PagamentoPayloadRecord plainToPagamentoPayloadRecordTwo(PagamentoPayload pagamentoPayload) {
        Object pagamento = plainToPagamentoRecordTwo(pagamentoPayload.getPagamento());
        List<Object> list = new ArrayList<>();

        for (ItemPagamento obj : pagamentoPayload.getItemPagamentoList()) {
            list.add(plainToItemPagamentoRecordTwo(obj));
        }

        return new PagamentoPayloadRecord<>(pagamento, list);
    }

    // Encripted → Plain
}
