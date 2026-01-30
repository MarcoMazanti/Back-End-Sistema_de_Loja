package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Pagamento.*;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.ItemPagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.Pagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.PagamentoPayload;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagamentoGeralFactory {
    @Setter
    private String chaveSimetrica;
    @Autowired
    private Criptografar criptografar;

    // Plain → Encripted

    // Pagamento
    // PagamentoRecordOne
    public PagamentoRecordOne plainToPagamentoRecordOne(Pagamento pagamento) {
        String id = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getId()));
        String idCliente = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getIdCliente()));
        String idFilial = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getIdFilial()));
        String precoTotal = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getPrecoTotal()));
        String precoPago = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getPrecoPago()));
        String dataCompra = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getDataCompra()));
        String codPagamento = criptografar.criptografar(chaveSimetrica, pagamento.getCodPagamento());

        return new PagamentoRecordOne(id, idCliente, idFilial, precoTotal, precoPago, dataCompra, codPagamento);
    }

    // PagamentoRecordTwo
    public PagamentoRecordTwo plainToPagamentoRecordTwo(Pagamento pagamento) {
        String idCliente = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getIdCliente()));
        String precoTotal = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getPrecoTotal()));
        String precoPago = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getPrecoPago()));
        String dataCompra = criptografar.criptografar(chaveSimetrica, String.valueOf(pagamento.getDataCompra()));
        String codPagamento = criptografar.criptografar(chaveSimetrica, pagamento.getCodPagamento());

        return new PagamentoRecordTwo(idCliente, precoTotal, precoPago, dataCompra, codPagamento);
    }


    // Item Pagamento
    //ItemPagamentoRecordOne
    public ItemPagamentoRecordOne plainToItemPagamentoRecordOne(ItemPagamento itemPagamento) {
        String id = criptografar.criptografar(chaveSimetrica, String.valueOf(itemPagamento.getId()));
        String idPagamento = criptografar.criptografar(chaveSimetrica, String.valueOf(itemPagamento.getIdPagamento()));
        String idItem = criptografar.criptografar(chaveSimetrica, String.valueOf(itemPagamento.getIdItem()));
        String nome = criptografar.criptografar(chaveSimetrica, itemPagamento.getNome());
        String quantidade = criptografar.criptografar(chaveSimetrica, String.valueOf(itemPagamento.getQuantidade()));
        String preco = criptografar.criptografar(chaveSimetrica, String.valueOf(itemPagamento.getPrecoUnit()));

        return new ItemPagamentoRecordOne(id, idPagamento, idItem, nome, quantidade, preco);
    }

    // ItemPagamentoRecordTwo
    public ItemPagamentoRecordTwo plainToItemPagamentoRecordTwo(ItemPagamento itemPagamento) {
        String idPagamento = criptografar.criptografar(chaveSimetrica, String.valueOf(itemPagamento.getIdPagamento()));
        String idItem = criptografar.criptografar(chaveSimetrica, String.valueOf(itemPagamento.getIdItem()));
        String quantidade = criptografar.criptografar(chaveSimetrica, String.valueOf(itemPagamento.getQuantidade()));

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
