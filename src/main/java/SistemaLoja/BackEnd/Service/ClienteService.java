package SistemaLoja.BackEnd.Service;

import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import SistemaLoja.BackEnd.Exception.AtualizacaoNaoPermitidaException;
import SistemaLoja.BackEnd.Exception.RegistroInexistenteException;
import SistemaLoja.BackEnd.Exception.RegistroJaExistenteException;
import SistemaLoja.BackEnd.Exception.TabelaVaziaException;
import SistemaLoja.BackEnd.Repository.ClienteRepository;
import SistemaLoja.BackEnd.Service.Interface.RequestRequerinteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService extends ServiceAbstract<Cliente> implements RequestRequerinteInterface<Cliente> {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listar() {
        List<Cliente> clienteList = clienteRepository.findAll();

        if (clienteList.isEmpty()) throw new TabelaVaziaException("Tabela de Cliente está vazia!");
        return clienteList;
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isEmpty()) throw new RegistroInexistenteException("Cliente não encontrado!");
        return optionalCliente.get();
    }

    @Override
    public Cliente salvar(Integer idRequerinte, Cliente item) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Cliente> optionalCliente = clienteRepository.findByCpfOrCnpj(item.getCpfOrCnpj());
        if (optionalCliente.isPresent()) throw new RegistroJaExistenteException("Já possui um Cliente com esse CPF/CNPJ!");

        return clienteRepository.save(item);
    }

    @Override
    public Cliente atualizar(Integer idRequerinte, Cliente item) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Cliente> optionalCliente = clienteRepository.findById(item.getId());
        if (optionalCliente.isEmpty()) throw new RegistroInexistenteException("Registro de cliente não existente!");
        Cliente clienteAntigo = optionalCliente.get();

        if (!clienteAntigo.equals(item)) throw new AtualizacaoNaoPermitidaException("Não é permitido alterar o CPF/CNPJ e o ID do cliente!");

        return clienteRepository.save(item);
    }

    @Override
    public void remover(Integer idRequerinte, Integer id) {
        verificarPermissaoRequerinte(idRequerinte);

        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isEmpty()) throw new RegistroInexistenteException("Cliente não cadastrado!");

        clienteRepository.deleteById(id);
    }
}
