package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Pagamento.ItemPagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.Pagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.PagamentoPayload;
import SistemaLoja.BackEnd.Exception.RegistroInexistenteException;
import SistemaLoja.BackEnd.Exception.TabelaVaziaException;
import SistemaLoja.BackEnd.Repository.ItemPagamentoRepository;
import SistemaLoja.BackEnd.Repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPagamentoRepository itemPagamentoRepository;

    public List<Pagamento> listarPagamento() {
        List<Pagamento> pagamentoList = pagamentoRepository.findAll();

        if (pagamentoList.isEmpty()) throw new TabelaVaziaException("Tabela de Pagamento está vazia!");
        return pagamentoList;
    }

    public List<ItemPagamento> listarItem() {
        List<ItemPagamento> itemPagamentoList = itemPagamentoRepository.findAll();

        if (itemPagamentoList.isEmpty()) throw new TabelaVaziaException("Tabela de Item Pagamento está vazia!");
        return itemPagamentoList;
    }

    public Pagamento buscarPagamentoPorId(Integer id) {
        Optional<Pagamento> optionalPagamento = pagamentoRepository.findById(id);

        if (optionalPagamento.isEmpty()) throw new RegistroInexistenteException("Não foi encontrado pelo ID o Pagamento desejado!");
        return optionalPagamento.get();
    }
    public ItemPagamento buscarItemPorId(Integer id) {
        Optional<ItemPagamento> optionalItemPagamento = itemPagamentoRepository.findById(id);

        if (optionalItemPagamento.isEmpty()) throw new RegistroInexistenteException("Não foi encontrado pelo ID o Item Pagamento desejado!");
        return optionalItemPagamento.get();
    }

    public List<Pagamento> listarPagamentoPorIdCliente(Integer idCliente) {
        List<Pagamento> pagamentoList = pagamentoRepository.findAllByIdCliente(idCliente);

        if (pagamentoList.isEmpty()) throw new RegistroInexistenteException("Não foi encontrado nenhum registro com este idCliente!");
        return pagamentoList;
    }

    public List<Pagamento> listarPagamentoPorIdFilial(Integer idFilial) {
        List<Pagamento> pagamentoList = pagamentoRepository.findAllByIdFilial(idFilial);

        if (pagamentoList.isEmpty()) throw new RegistroInexistenteException("Não foi encontrado nenhum registro com este idFilial!");
        return pagamentoList;
    }

    public List<ItemPagamento> listarItemPorIdPagamento(Integer idPagamento) {
        List<ItemPagamento> itemPagamentoList = itemPagamentoRepository.findAllByIdPagamento(idPagamento);

        if (itemPagamentoList.isEmpty()) throw new RegistroInexistenteException("Não foi encontrado nenhum registro com este idPagamento!");
        return itemPagamentoList;
    }

    public List<ItemPagamento> listarItemPorIdItem(Integer idItem) {
        List<ItemPagamento> itemPagamentoList = itemPagamentoRepository.findAllByIdItem(idItem);

        if (itemPagamentoList.isEmpty()) throw new RegistroInexistenteException("Não foi encontrado nenhum registro com este idItem!");
        return itemPagamentoList;
    }

    //  Vai receber um objeto onde possua o objeto pagamento e uma lista com os itens.
    //  Será feito o salvamento na tabela Pagamento, assim irei obter o ‘id’ do registro feito, com isto eu completo com os
    // dados que estarão presentes na lista.
    @Transactional
    public PagamentoPayload salvar(PagamentoPayload pagamentoPayload) {
        pagamentoPayload.setPagamento(salvarPagamento(pagamentoPayload.getPagamento()));

        pagamentoPayload.getItemPagamentoList().forEach(itemPagamento -> itemPagamento.setIdPagamento(pagamentoPayload.getPagamento().getId()));

        pagamentoPayload.setItemPagamentoList(salvarListaItem(pagamentoPayload.getItemPagamentoList()));
        return pagamentoPayload;
    }

    private Pagamento salvarPagamento(Pagamento item) {
        return pagamentoRepository.save(item);
    }

    private List<ItemPagamento> salvarListaItem(List<ItemPagamento> itemPagamentoList) {
        return itemPagamentoRepository.saveAll(itemPagamentoList);
    }
}
