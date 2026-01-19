package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Fornecedor.Fornecedor;
import SistemaLoja.BackEnd.Exception.AtualizacaoNaoPermitidaException;
import SistemaLoja.BackEnd.Exception.RegistroInexistenteException;
import SistemaLoja.BackEnd.Exception.RegistroJaExistenteException;
import SistemaLoja.BackEnd.Exception.TabelaVaziaException;
import SistemaLoja.BackEnd.Repository.FornecedorRepository;
import SistemaLoja.BackEnd.Service.Interface.RequestRequerinteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService extends ServiceAbstract<Fornecedor> implements RequestRequerinteInterface<Fornecedor> {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public List<Fornecedor> listar() {
        List<Fornecedor> fornecedorList = fornecedorRepository.findAll();

        if (fornecedorList.isEmpty()) throw new TabelaVaziaException("Tabela de Fornecedor está vazia!");
        return fornecedorList;
    }

    @Override
    public Fornecedor buscarPorId(Integer id) {
        Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(id);

        if (optionalFornecedor.isEmpty()) throw new RegistroInexistenteException("Fornecedor não encontrado!");
        return optionalFornecedor.get();
    }

    @Override
    public Fornecedor salvar(Integer idRequerinte, Fornecedor item) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findByCpfOrCnpj(item.getCpfOrCnpj());
        if (optionalFornecedor.isPresent()) throw new RegistroJaExistenteException("Já possui um Fornecedor com esse CPF/CNPJ!");

        return fornecedorRepository.save(item);
    }

    @Override
    public Fornecedor atualizar(Integer idRequerinte, Fornecedor item) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(item.getId());
        if (optionalFornecedor.isEmpty()) throw new RegistroInexistenteException("Registro de fornecedor não existente!");
        Fornecedor fornecedorAntigo = optionalFornecedor.get();

        if (!fornecedorAntigo.equals(item)) throw new AtualizacaoNaoPermitidaException("Não é permitido alterar o CPF/CNPJ e o ID do fornecedor!");

        return fornecedorRepository.save(item);
    }

    @Override
    public void remover(Integer idRequerinte, Integer id) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(id);
        if (optionalFornecedor.isEmpty()) throw new RegistroInexistenteException("Fornecedor não cadastrado!");

        fornecedorRepository.deleteById(id);
    }
}
