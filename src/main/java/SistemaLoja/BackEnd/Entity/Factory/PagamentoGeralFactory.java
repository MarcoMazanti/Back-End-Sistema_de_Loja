package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Pagamento.*;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.ItemPagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.Pagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.PagamentoPayload;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import SistemaLoja.BackEnd.Security.Decript.Descriptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PagamentoGeralFactory {
    @Setter
    private String chaveSimetrica;
    @Autowired
    private Criptografar criptografar;
    @Autowired
    private Descriptografar descriptografar;

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

    // Pagamento
    public Pagamento encriptedToPlainPagamento(PagamentoRecordOne pagamentoRecordOne) {
        int id = (pagamentoRecordOne.id() != null) ? Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, pagamentoRecordOne.id())) : null;
        int idCliente = Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, pagamentoRecordOne.idCliente()));
        int idFilial = Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, pagamentoRecordOne.idFilial()));
        BigDecimal precoTotal = new BigDecimal(descriptografar.descriptografar(chaveSimetrica, pagamentoRecordOne.precoTotal()));
        BigDecimal precoPago = (pagamentoRecordOne.precoPago() != null) ? new BigDecimal(descriptografar.descriptografar(chaveSimetrica, pagamentoRecordOne.precoPago())) : null;

        Date dataCompra = null;
        if (pagamentoRecordOne.dataCompra() != null) {
            String dataCompraString = descriptografar.descriptografar(chaveSimetrica, pagamentoRecordOne.dataCompra());
            dataCompra = new Date(Long.parseLong(dataCompraString));
        }

        String codPagamento = (pagamentoRecordOne.codPagamento() != null) ? descriptografar.descriptografar(chaveSimetrica, pagamentoRecordOne.codPagamento()) : null;

        return new Pagamento(id, idCliente, idFilial, precoTotal, precoPago, dataCompra, codPagamento);
    }

    // Item Pagamento
    public ItemPagamento encriptedToPlainItemPagamento(ItemPagamentoRecordOne itemPagamentoRecordOne) {
        int id = (itemPagamentoRecordOne.id() != null) ? Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, itemPagamentoRecordOne.id())) : null;
        int idPagamento = (itemPagamentoRecordOne.idPagamento() != null) ? Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, itemPagamentoRecordOne.idPagamento())) : null;
        int idItem = Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, itemPagamentoRecordOne.idItem()));
        String nome = descriptografar.descriptografar(chaveSimetrica, itemPagamentoRecordOne.nome());
        int quantidade = Integer.parseInt(descriptografar.descriptografar(chaveSimetrica, itemPagamentoRecordOne.quantidade()));
        BigDecimal precoUnit = new BigDecimal(descriptografar.descriptografar(chaveSimetrica, itemPagamentoRecordOne.precoUnit()));

        return new ItemPagamento(id, idPagamento, idItem, nome, quantidade, precoUnit);
    }

    // Pagamento Payload
    public PagamentoPayload encriptedToPlainPagamentoPayload(PagamentoPayloadRecord<PagamentoRecordOne, ItemPagamentoRecordOne> pagamentoPayloadRecord) {
        Pagamento pagamento = encriptedToPlainPagamento((PagamentoRecordOne) pagamentoPayloadRecord.pagamento());
        List<ItemPagamento> itemPagamentoList = new ArrayList<>();

        for (ItemPagamentoRecordOne item : pagamentoPayloadRecord.itemPagamentoList()) {
            itemPagamentoList.add(encriptedToPlainItemPagamento(item));
        }

        return new PagamentoPayload(pagamento, itemPagamentoList);
    }
}
