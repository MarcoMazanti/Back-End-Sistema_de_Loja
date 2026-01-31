package SistemaLoja.BackEnd.Controller.Advice;

import SistemaLoja.BackEnd.Entity.Encripted.Cliente.ClienteRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Empregado.EmpregadoRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Estoque.EstoqueRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Filial.FilialRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Fornecedor.FornecedorRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Pagamento.ItemPagamentoRecordOne;
import SistemaLoja.BackEnd.Entity.Encripted.Pagamento.PagamentoPayloadRecord;
import SistemaLoja.BackEnd.Entity.Encripted.Pagamento.PagamentoRecordOne;
import SistemaLoja.BackEnd.Entity.Factory.*;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Login;
import SistemaLoja.BackEnd.Security.GerarSecretKey;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@ControllerAdvice
public class ModelRecordRequestAdvice extends RequestBodyAdviceAdapter {
    @Autowired
    private FilialFactory filialFactory;
    @Autowired
    private EmpregadoFactory empregadoFactory;
    @Autowired
    private ClienteFactory clienteFactory;
    @Autowired
    private FornecedorFactory fornecedorFactory;
    @Autowired
    private EstoqueFactory estoqueFactory;
    @Autowired
    private PagamentoGeralFactory pagamentoGeralFactory;
    @Autowired
    private GerarSecretKey gerarSecretKey;

    private SecretKey chaveSimetrica;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage,
                                           MethodParameter parameter,
                                           Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        String bodyOriginal = new String(inputMessage.getBody().readAllBytes(), StandardCharsets.UTF_8);
        String chaveSimetricaSuja = Optional.ofNullable(inputMessage.getHeaders().getFirst("secretKey")).orElse("");

        chaveSimetrica = gerarSecretKey.descriptSecretKey(chaveSimetricaSuja);

        Object objDescript = descriptografarBody(bodyOriginal);
        ObjectMapper mapper = new ObjectMapper();

        return new HttpInputMessage() {
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }

            @Override
            public InputStream getBody() throws IOException {
                try {
                    if (objDescript != null) {
                        String jsonFinal = mapper.writeValueAsString(objDescript);
                        return new ByteArrayInputStream(jsonFinal.getBytes(StandardCharsets.UTF_8));
                    }

                    return new ByteArrayInputStream("".getBytes());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return new ByteArrayInputStream("".getBytes());
                }
            }
        };
    }

    private Object descriptografarBody(String body) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(body);

            if (jsonNode.has("codCliente")) {
                clienteFactory.setChaveSimetrica(chaveSimetrica);
                return clienteFactory.encriptedToPlainCliente(mapper.treeToValue(jsonNode, ClienteRecordOne.class));
            } else if (jsonNode.has("codEmpregado")) {
                empregadoFactory.setChaveSimetrica(chaveSimetrica);
                return empregadoFactory.encriptedToPlainEmpregado(mapper.treeToValue(jsonNode, EmpregadoRecordOne.class));
            } else if (jsonNode.has("cpf") && jsonNode.has("senha")) {
                empregadoFactory.setChaveSimetrica(chaveSimetrica);
                return empregadoFactory.encriptedToPlainLogin(mapper.treeToValue(jsonNode, Login.class));
            } else if (jsonNode.has("codItem")) {
                estoqueFactory.setChaveSimetrica(chaveSimetrica);
                return estoqueFactory.encriptedToPlainEstoque(mapper.treeToValue(jsonNode, EstoqueRecordOne.class));
            } else if (jsonNode.has("codFilial")) {
                filialFactory.setChaveSimetrica(chaveSimetrica);
                return filialFactory.encriptedToPlainFilial(mapper.treeToValue(jsonNode, FilialRecordOne.class));
            } else if (jsonNode.has("codForncedor")) {
                fornecedorFactory.setChaveSimetrica(chaveSimetrica);
                return fornecedorFactory.encriptedToPlainFornecedor(mapper.treeToValue(jsonNode, FornecedorRecordOne.class));
            } else if (jsonNode.has("pagamento") && !jsonNode.has("precoUnit")) {
                pagamentoGeralFactory.setChaveSimetrica(chaveSimetrica);
                return pagamentoGeralFactory.encriptedToPlainPagamento(mapper.treeToValue(jsonNode, PagamentoRecordOne.class));
            } else if (!jsonNode.has("pagamento") && jsonNode.has("precoUnit")) {
                pagamentoGeralFactory.setChaveSimetrica(chaveSimetrica);
                return pagamentoGeralFactory.encriptedToPlainItemPagamento(mapper.treeToValue(jsonNode, ItemPagamentoRecordOne.class));
            } else if (jsonNode.has("pagamento") && jsonNode.has("itemPagamentoList")) {
                pagamentoGeralFactory.setChaveSimetrica(chaveSimetrica);
                return pagamentoGeralFactory.encriptedToPlainPagamentoPayload(mapper.treeToValue(jsonNode,
                        new TypeReference<PagamentoPayloadRecord<PagamentoRecordOne, ItemPagamentoRecordOne>>() {}));
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao descriptografar body do Request!");
            return null;
        }
    }
}
