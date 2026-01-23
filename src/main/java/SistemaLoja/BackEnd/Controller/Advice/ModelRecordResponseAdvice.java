package SistemaLoja.BackEnd.Controller.Advice;

import SistemaLoja.BackEnd.Entity.Factory.*;
import SistemaLoja.BackEnd.Entity.Plain.Cliente.Cliente;
import SistemaLoja.BackEnd.Entity.Plain.Empregado.Empregado;
import SistemaLoja.BackEnd.Entity.Plain.Estoque.Estoque;
import SistemaLoja.BackEnd.Entity.Plain.Filial.Filial;
import SistemaLoja.BackEnd.Entity.Plain.Fornecedor.Fornecedor;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.ItemPagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.Pagamento;
import SistemaLoja.BackEnd.Entity.Plain.Pagamento.PagamentoPayload;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Order(1)
@ControllerAdvice
public class ModelRecordResponseAdvice implements ResponseBodyAdvice<Object> {
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

    private int modelRecord = 1;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body,
                                            MethodParameter returnType,
                                            MediaType selectedContentType,
                                            Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                            ServerHttpRequest request,
                                            ServerHttpResponse response) {
        if (body == null) return null;
        String modelRecordStr = request.getHeaders().getFirst("ModelRecord");

        if (modelRecordStr != null && !modelRecordStr.isEmpty()) {
            modelRecord = Integer.parseInt(modelRecordStr);
        } else {
            if (request instanceof ServletServerHttpRequest servletRequest) {
                HttpServletRequest httpRequest = servletRequest.getServletRequest();

                Object attr = httpRequest.getAttribute("ModelRecord");

                this.modelRecord = (attr != null) ? (int) attr : 1;
            }
        }

        if (body instanceof List<?> lista) {
            if (!lista.isEmpty()) {
                List<Object> listaEntrega = new ArrayList<>();

                for (Object item : lista) {
                    if (item instanceof Filial filial) {
                        listaEntrega.add(filialPlainToRecord(filial));
                    } else if (item instanceof Empregado empregado) {
                        listaEntrega.add(empregadoPlainToRecord(empregado));
                    } else if (item instanceof Cliente cliente) {
                        listaEntrega.add(clientePlainToFactory(cliente));
                    } else if (item instanceof Fornecedor fornecedor) {
                        listaEntrega.add(fornecedorPlainToRecord(fornecedor));
                    } else if (item instanceof Estoque estoque) {
                        listaEntrega.add(estoquePlainToRecord(estoque));
                    } else if (item instanceof Pagamento pagamento) {
                        listaEntrega.add(pagamentoPlainToRecord(pagamento));
                    } else if (item instanceof ItemPagamento itemPagamento) {
                        listaEntrega.add(itemPagamentoPlainToRecord(itemPagamento));
                    } else if (item instanceof PagamentoPayload pagamentoPayload) {
                        listaEntrega.add(pagamentoPayloadPlainToRecord(pagamentoPayload));
                    }
                }

                return listaEntrega;
            } else {
                return List.of();
            }
        } else {
            Object objEntrega;
            if (body instanceof Filial filial) {
                objEntrega = filialPlainToRecord(filial);
            } else if (body instanceof Empregado empregado) {
                objEntrega = empregadoPlainToRecord(empregado);
            } else if (body instanceof Cliente cliente) {
                objEntrega = clientePlainToFactory(cliente);
            } else if (body instanceof Fornecedor fornecedor) {
                objEntrega = fornecedorPlainToRecord(fornecedor);
            } else if (body instanceof Estoque estoque) {
                objEntrega = estoquePlainToRecord(estoque);
            } else if (body instanceof Pagamento pagamento) {
                objEntrega = pagamentoPlainToRecord(pagamento);
            } else if (body instanceof ItemPagamento itemPagamento) {
                objEntrega = itemPagamentoPlainToRecord(itemPagamento);
            } else if (body instanceof PagamentoPayload pagamentoPayload) {
                objEntrega = pagamentoPayloadPlainToRecord(pagamentoPayload);
            } else {
                objEntrega = body;
            }

            return  objEntrega;
        }
    }

    private Object filialPlainToRecord(Filial filial) {
        return switch (modelRecord) {
            case 1 -> filialFactory.plainToFilialRecordOne(filial);
            case 2 -> filialFactory.plainToFilialRecordTwo(filial);
            case 3 -> filialFactory.plainToFilialRecordThree(filial);
            default -> null;
        };
    }

    private Object empregadoPlainToRecord(Empregado empregado) {
        return switch (modelRecord) {
            case 1 -> empregadoFactory.plainToEmpregadoRecordOne(empregado);
            case 2 -> empregadoFactory.plainToEmpregadoRecordTwo(empregado);
            case 3 -> empregadoFactory.plainToEmpregadoThree(empregado);
            default -> null;
        };
    }

    private Object clientePlainToFactory(Cliente cliente) {
        return switch (modelRecord) {
            case 1 -> clienteFactory.plainToClienteRecordOne(cliente);
            case 2 -> clienteFactory.plainToClienteRecordTwo(cliente);
            case 3 -> clienteFactory.plainToClienteRecordThree(cliente);
            default -> null;
        };
    }

    public Object fornecedorPlainToRecord(Fornecedor fornecedor) {
        return switch (modelRecord) {
            case 1 -> fornecedorFactory.plainToFornecedorRecordOne(fornecedor);
            case 2 -> fornecedorFactory.plainToFornecedorRecordTwo(fornecedor);
            case 3 -> fornecedorFactory.plainToFornecedorRecordThree(fornecedor);
            default -> null;
        };
    }

    public Object estoquePlainToRecord(Estoque estoque) {
        return switch (modelRecord) {
            case 1 -> estoqueFactory.plainToEstoqueRecordOne(estoque);
            case 2 -> estoqueFactory.plainToEstoqueRecordTwo(estoque);
            case 3 -> estoqueFactory.plainToEstoqueRecordThree(estoque);
            default -> null;
        };
    }

    public Object pagamentoPlainToRecord(Pagamento pagamento) {
        return switch (modelRecord) {
            case 1 -> pagamentoGeralFactory.plainToPagamentoRecordOne(pagamento);
            case 2 -> pagamentoGeralFactory.plainToPagamentoRecordTwo(pagamento);
            default -> null;
        };
    }

    public Object itemPagamentoPlainToRecord(ItemPagamento itemPagamento) {
        return switch (modelRecord) {
            case 1 -> pagamentoGeralFactory.plainToItemPagamentoRecordOne(itemPagamento);
            case 2 -> pagamentoGeralFactory.plainToItemPagamentoRecordTwo(itemPagamento);
            default -> null;
        };
    }

    public Object pagamentoPayloadPlainToRecord(PagamentoPayload pagamentoPayload) {
        return switch (modelRecord) {
            case 1 -> pagamentoGeralFactory.plainToPagamentoPayloadRecordOne(pagamentoPayload);
            case 2 -> pagamentoGeralFactory.plainToPagamentoPayloadRecordTwo(pagamentoPayload);
            default -> null;
        };
    }
}
