package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Estoque.Estoque;
import SistemaLoja.BackEnd.Exception.AtualizacaoNaoPermitidaException;
import SistemaLoja.BackEnd.Exception.RegistroInexistenteException;
import SistemaLoja.BackEnd.Exception.RegistroJaExistenteException;
import SistemaLoja.BackEnd.Exception.TabelaVaziaException;
import SistemaLoja.BackEnd.Repository.EstoqueRepository;
import SistemaLoja.BackEnd.Service.Interface.RequestPadraoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService extends ServiceAbstract<Estoque> implements RequestPadraoInterface<Estoque> {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Override
    public List<Estoque> listar() {
        List<Estoque> estoqueList = estoqueRepository.findAll();

        if (estoqueList.isEmpty()) throw new TabelaVaziaException("Tabela Estoque está vazia!");
        return estoqueList;
    }

    @Override
    public Estoque buscarPorId(Integer id) {
        Optional<Estoque> optionalEstoque = estoqueRepository.findById(id);

        if (optionalEstoque.isEmpty()) throw new RegistroInexistenteException("Não foi encontrado o item no estoque!");
        return optionalEstoque.get();
    }

    public List<Estoque> listarPorIdFilial(Integer idFilial) {
        List<Estoque> estoqueList = estoqueRepository.findAllByIdFilial(idFilial);

        if (estoqueList.isEmpty()) throw new TabelaVaziaException("Não foi encontrado nenhum item em estoque desta Filial!");
        return estoqueList;
    }

    public List<Estoque> listarPorIdFornecedor(Integer idFornecedor) {
        List<Estoque> estoqueList = estoqueRepository.findAllByIdFornecedor(idFornecedor);

        if (estoqueList.isEmpty()) throw new TabelaVaziaException("Não foi encontrado nenhum item em estoque deste Fornecedor!");
        return estoqueList;
    }

    @Override
    public Estoque salvar(Estoque item) {
        Optional<Estoque> optionalEstoque = estoqueRepository.findByNomeAndIdFilialAndIdFornecedor(item.getNome(), item.getIdFilial(), item.getIdFornecedor());

        if (optionalEstoque.isPresent()) throw new RegistroJaExistenteException("Já existe este item nesta filial com o mesmo fornecedor!");

        return estoqueRepository.save(item);
    }

    @Override
    public Estoque atualizar(Estoque item) {
        Optional<Estoque> optionalEstoque = estoqueRepository.findByNomeAndIdFilialAndIdFornecedor(item.getNome(), item.getIdFilial(), item.getIdFornecedor());

        if (optionalEstoque.isEmpty()) throw new RegistroInexistenteException("Não existe este item nesta filial com o mesmo fornecedor!");
        if (!item.equals(optionalEstoque.get())) throw new AtualizacaoNaoPermitidaException("Não é permitido alterar o nome, idFilial ou idFornecedor!");

        return estoqueRepository.save(item);
    }

    @Override
    public void remover(Integer id) {
        Optional<Estoque> optionalEstoque = estoqueRepository.findById(id);

        if (optionalEstoque.isEmpty()) throw new RegistroInexistenteException("Este item não existe na tabela Estoque!");

        estoqueRepository.deleteById(id);
    }
}
