package SistemaLoja.BackEnd.Entity.Factory;

import SistemaLoja.BackEnd.Entity.Encripted.Estoque.EstoqueRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Estoque.EstoqueRecordThree;
import SistemaLoja.BackEnd.Entity.Encripted.Estoque.EstoqueRecordTwo;
import SistemaLoja.BackEnd.Entity.Plain.Estoque.Estoque;
import SistemaLoja.BackEnd.Security.Cript.Criptografar;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueFactory {
    @Setter
    private String chaveSimetrica;
    @Autowired
    private Criptografar criptografar;

    // Plain → Encripted
    // EstoqueRecordOne
    public EstoqueRecordOne plainToEstoqueRecordOne(Estoque estoque) {
        String id = criptografar.criptografar(chaveSimetrica, String.valueOf(estoque.getId()));
        String nome = criptografar.criptografar(chaveSimetrica, estoque.getNome());
        String idFilial = criptografar.criptografar(chaveSimetrica, String.valueOf(estoque.getIdFilial()));
        String idFornecedor = criptografar.criptografar(chaveSimetrica, String.valueOf(estoque.getIdFornecedor()));
        String preco = criptografar.criptografar(chaveSimetrica, String.valueOf(estoque.getPreco()));
        String quantidade = criptografar.criptografar(chaveSimetrica, String.valueOf(estoque.getQuantidade()));
        String descricao = criptografar.criptografar(chaveSimetrica, estoque.getDescricao());
        String codItem = criptografar.criptografar(chaveSimetrica, estoque.getCodItem());

        return new EstoqueRecordOne(id, nome, idFilial, idFornecedor, preco, quantidade, descricao, codItem);
    }

    // EstoqueRecordTwo
    public EstoqueRecordTwo plainToEstoqueRecordTwo(Estoque estoque) {
        String nome = criptografar.criptografar(chaveSimetrica, estoque.getNome());
        String idFornecedor = criptografar.criptografar(chaveSimetrica, String.valueOf(estoque.getIdFornecedor()));
        String preco = criptografar.criptografar(chaveSimetrica, String.valueOf(estoque.getPreco()));
        String quantidade = criptografar.criptografar(chaveSimetrica, String.valueOf(estoque.getQuantidade()));
        String descricao = criptografar.criptografar(chaveSimetrica, estoque.getDescricao());
        String codItem = criptografar.criptografar(chaveSimetrica, estoque.getCodItem());

        return new EstoqueRecordTwo(nome, idFornecedor, preco, quantidade, descricao, codItem);
    }

    // EstoqueRecordThree
    public EstoqueRecordThree plainToEstoqueRecordThree(Estoque estoque) {
        String nome = criptografar.criptografar(chaveSimetrica, estoque.getNome());
        String preco = criptografar.criptografar(chaveSimetrica, String.valueOf(estoque.getPreco()));
        String descricao = criptografar.criptografar(chaveSimetrica, estoque.getDescricao());
        String codItem = criptografar.criptografar(chaveSimetrica, estoque.getCodItem());

        return new EstoqueRecordThree(nome, preco, descricao, codItem);
    }

    // Encripted → Plain
}
