package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Estoque.EstoqueRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Estoque.EstoqueRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Estoque.EstoqueRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Estoque.Estoque;
import org.springframework.stereotype.Service;

@Service
public class EstoqueFactory {
    // Plain → Encripted
    // EstoqueRecordOne
    public EstoqueRecordOne plainToEstoqueRecordOne(Estoque estoque) {
        String id = String.valueOf(estoque.getId());
        String nome = estoque.getNome();
        String idFilial = String.valueOf(estoque.getIdFilial());
        String idFornecedor = String.valueOf(estoque.getIdFornecedor());
        String preco = String.valueOf(estoque.getPreco());
        String quantidade = String.valueOf(estoque.getQuantidade());
        String descricao = estoque.getDescricao();
        String codItem = estoque.getCodItem();

        return new EstoqueRecordOne(id, nome, idFilial, idFornecedor, preco, quantidade, descricao, codItem);
    }

    // EstoqueRecordTwo
    public EstoqueRecordTwo plainToEstoqueRecordTwo(Estoque estoque) {
        String nome = estoque.getNome();
        String idFornecedor = String.valueOf(estoque.getIdFornecedor());
        String preco = String.valueOf(estoque.getPreco());
        String quantidade = String.valueOf(estoque.getQuantidade());
        String descricao = estoque.getDescricao();
        String codItem = estoque.getCodItem();

        return new EstoqueRecordTwo(nome, idFornecedor, preco, quantidade, descricao, codItem);
    }

    // EstoqueRecordThree
    public EstoqueRecordThree plainToEstoqueRecordThree(Estoque estoque) {
        String nome = estoque.getNome();
        String preco = String.valueOf(estoque.getPreco());
        String descricao = estoque.getDescricao();
        String codItem = estoque.getCodItem();

        return new EstoqueRecordThree(nome, preco, descricao, codItem);
    }

    // Encripted → Plain
}
