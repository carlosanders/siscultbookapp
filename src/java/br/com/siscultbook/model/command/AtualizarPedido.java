/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.Entrega;
import br.com.siscultbook.bean.EstadoDoPedido;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.Pagamento;
import br.com.siscultbook.bean.PagamentoBoleto;
import br.com.siscultbook.bean.PagamentoCartao;
import br.com.siscultbook.bean.Pedido;
import br.com.siscultbook.bean.TipoCartao;
import br.com.siscultbook.model.dao.EntregaDAO;
import br.com.siscultbook.model.dao.PagamentoDAO;
import br.com.siscultbook.model.dao.PedidoDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class AtualizarPedido implements InterfaceCommand {

    private PedidoDAO pedidoDAO;
    private EntregaDAO entregaDAO;
    private PagamentoDAO pagamentoDAO;

    public AtualizarPedido(PedidoDAO pedidoDAO, EntregaDAO entregaDAO, PagamentoDAO pagamentoDAO) {
        super();
        this.pedidoDAO = pedidoDAO;
        this.entregaDAO = entregaDAO;
        this.pagamentoDAO = pagamentoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Pedido Finalizado");
        boolean retorno = false;
        String mensagem = "";
        Cliente cliente = null;
        Entrega entrega = new Entrega();
        Pedido pedido = new Pedido();
        Pattern pTelefone = Pattern.compile("^[0-9]{2}[0-9]{3,4}[0-9]{4}$");
        PagamentoCartao pagamentoCartao = null;
        PagamentoBoleto pagamentoBoleto = null;

        try {
            Integer codigoPedido = Integer.parseInt(request.getParameter("codigoPedido"));
            Integer codigoPagamento = Integer.parseInt(request.getParameter("codPagamento"));
            Integer codigoEntrega = Integer.parseInt(request.getParameter("codigoEntrega"));
            Integer codigoCliente = Integer.parseInt(request.getParameter("codigoCliente"));


            /* Tratando dados do pedido vindo do form */
            pedido.setCodigoPedido(codigoPedido);
            pedido.getCliente().setCodigoCliente(codigoCliente);
            String descricao = request.getParameter("descricao");
            if (descricao.equals("") || descricao == null || descricao.length() < 3) {
                mensagem = mensagem + "O campo descrição, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            } else {
                pedido.setDescricao(descricao);
            }
            String statusPedido = request.getParameter("statusPedido");
            if (statusPedido.equals("") || statusPedido == null) {
                mensagem = mensagem + "Selecione uma opção.<br />";
                retorno = true;
            } else {
                pedido.setStatusPedido(EstadoDoPedido.valueOf(EstadoDoPedido.class, statusPedido));
            }
            String dataPedido = request.getParameter("dataPedido");
            if (dataPedido.equals("") || dataPedido == null) {
                mensagem = mensagem + "O campo Data do Pedido, está vazio.<br />";
                retorno = true;
            } else {
                pedido.setDataPedido(Utilitario.verificaData(dataPedido));
            }

            /* tratando dados do pagamento */
            String fPagamento = request.getParameter("fPagamento");
            if (fPagamento.equals("") || fPagamento == null) {
                mensagem = mensagem + "Forma de Pagamento não encontrado.<br />";
                retorno = true;
            } else {
                if (fPagamento.equals("boleto")) {
                    pagamentoBoleto = new PagamentoBoleto();
                    pagamentoBoleto.setCodigoPagamento(codigoPagamento);
                    pagamentoBoleto.getPedido().setCodigoPedido(codigoPedido);

                    String valorTotal = request.getParameter("valorTotal");
                    valorTotal = valorTotal.replace(",", ".");
                    if (valorTotal.equals("") || valorTotal == null) {
                        mensagem = mensagem + "O campo valor total, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoBoleto.setValorTotal(Double.parseDouble(valorTotal));
                    }
                    String nrBoleto = request.getParameter("nrBoleto");
                    if (nrBoleto.equals("") || nrBoleto == null) {
                        mensagem = mensagem + "O campo número boleto.<br />";
                        retorno = true;
                    } else {
                        pagamentoBoleto.setNumeroDocumento(nrBoleto);
                    }
                    String vencimento = request.getParameter("vencimento");
                    if (vencimento.equals("") || vencimento == null) {
                        mensagem = mensagem + "O campo vencimento, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoBoleto.setVencimento(Utilitario.verificaData(vencimento));
                    }
                    String cedente = request.getParameter("cedente");
                    if (cedente.equals("") || cedente == null) {
                        mensagem = mensagem + "O campo cedente, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoBoleto.setCedente(cedente);
                    }
                    request.setAttribute("pagamento", pagamentoBoleto);

                } else {

                    pagamentoCartao = new PagamentoCartao();
                    pagamentoCartao.setCodigoPagamento(codigoPagamento);
                    pagamentoCartao.getPedido().setCodigoPedido(codigoPedido);

                    String valorTotal = request.getParameter("valorTotal");
                    valorTotal = valorTotal.replace(",", ".");
                    if (valorTotal.equals("") || valorTotal == null) {
                        mensagem = mensagem + "O campo valor total, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoCartao.setValorTotal(Double.parseDouble(valorTotal));
                    }

                    String validadeCartao = request.getParameter("validadeCartao");
                    if (validadeCartao.equals("") || validadeCartao == null) {
                        mensagem = mensagem + "O campo validade cartão, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoCartao.setValidadeCartao(validadeCartao);
                    }

                    String tipoCartaoBandeira = request.getParameter("tipoCartaoBandeira");
                    if (tipoCartaoBandeira.equals("") || tipoCartaoBandeira == null) {
                        mensagem = mensagem + "Selecione uma opção.<br />";
                        retorno = true;
                    } else {
                        pagamentoCartao.setBandeira(TipoCartao.valueOf(TipoCartao.class, tipoCartaoBandeira));
                    }

                    String codSeguranca = request.getParameter("codSeguranca");
                    if (codSeguranca.equals("") || codSeguranca == null) {
                        mensagem = mensagem + "O campo cód. de segurança, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoCartao.setCodigoSeguranca(codSeguranca);
                    }

                    String titularCartao = request.getParameter("titularCartao");
                    if (titularCartao.equals("") || titularCartao == null) {
                        mensagem = mensagem + "O campo titular, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoCartao.setNomeTitular(titularCartao);
                    }

                    String qtdParcelasCartao = request.getParameter("qtdParcelasCartao");
                    if (qtdParcelasCartao.equals("") || qtdParcelasCartao == null) {
                        mensagem = mensagem + "O campo quantidade de parcelas, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoCartao.setNumeroParcelas(Integer.parseInt(qtdParcelasCartao));
                    }

                    String valorParcelaCartao = request.getParameter("valorParcelaCartao");
                    valorParcelaCartao = valorParcelaCartao.replace(",", ".");
                    if (valorParcelaCartao.equals("") || valorParcelaCartao == null) {
                        mensagem = mensagem + "O campo Valor da Parcela, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoCartao.setValorPacela(Double.parseDouble(valorParcelaCartao));
                    }

                    String nrCartao = request.getParameter("nrCartao");
                    if (nrCartao.equals("") || nrCartao == null) {
                        mensagem = mensagem + "O campo Número Cartão, está vazio.<br />";
                        retorno = true;
                    } else {
                        pagamentoCartao.setNumeroCartao(nrCartao);
                    }

                    request.setAttribute("pagamento", pagamentoCartao);
                }
            }
            /*tratamento dos dados da entrega */
            entrega.setCodigoEntrega(codigoEntrega);
            entrega.getPedido().setCodigoPedido(codigoPedido);
            String telContato = request.getParameter("telContato");
            telContato = telContato.replace("(", "");
            telContato = telContato.replace(")", "");
            telContato = telContato.replace(" ", "");
            telContato = telContato.replace("-", "");
            if (telContato.equals("") || telContato == null) {
                mensagem = mensagem + "O campo telefone, está vazio.<br />";
                retorno = true;
            } else {
                entrega.setTelefone(telContato);
            }
            String frete = request.getParameter("frete");
            frete = frete.replace(",", ".");
            if (frete.equals("") || frete == null) {
                mensagem = mensagem + "O campo frete, está vazio.<br />";
                retorno = true;
            } else {
                entrega.setFrete(Double.parseDouble(frete));
            }

            String nomeCompleto = request.getParameter("nomeCompleto");
            if (nomeCompleto.equals("") || nomeCompleto == null) {
                mensagem = mensagem + "O campo telefone, está vazio.<br />";
                retorno = true;
            } else {
                entrega.setNomeCompleto(nomeCompleto);
            }

            String rua = request.getParameter("rua");
            if (rua.equals("") || rua == null) {
                mensagem = mensagem + "O campo rua está em branco ou vazio.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setRua(rua);
            }

            String bairro = request.getParameter("bairro");
            if (bairro.equals("") || bairro == null) {
                mensagem = mensagem + "O campo bairro está em branco ou vazio.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setBairro(bairro);
            }

            String cidade = request.getParameter("cidade");
            if (cidade.equals("") || cidade == null) {
                mensagem = mensagem + "O campo cidade está em branco ou vazio.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setCidade(cidade);
            }
            String valorEstado = request.getParameter("estado");
            if (valorEstado == null || valorEstado.equals("")) {
                //System.out.println("debug carlos - Passou aqui, dentro do if valorEstado");
                mensagem = mensagem + "Selecione o Estado do cliente.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setEstado(Estados.valueOf(Estados.class, valorEstado));
            }
            String cep = request.getParameter("cep");
            cep = cep.replace("-", "");
            if (cep.equals("") || cep == null || cep.length() != 8) {
                mensagem = mensagem + "Digite um CEP, ele deve conter 8 caracteres.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setCep(cep);
            }

            if (retorno) {

                request.setAttribute("entregaEstado", entrega.getEndereco().getEstado());
                request.setAttribute("erro", "Dados Incorretos:");
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("entrega", entrega);
                request.setAttribute("pedido", pedido);
                return "restrito/atualizaPedido.jsp";
            } else {
                pedidoDAO.atualizar(pedido);
                entregaDAO.atualizar(entrega);
                if (pagamentoBoleto != null) {
                    pagamentoDAO.atualizarPagamento(pagamentoBoleto);
                    pagamentoDAO.atualizarPagamentoBoleto(pagamentoBoleto);
                } else {
                    pagamentoDAO.atualizarPagamento(pagamentoCartao);
                    pagamentoDAO.atualizarPagamentoCartao(pagamentoCartao);
                }
                request.setAttribute("mensagem", "Pedido nº: " + pedido.getCodigoPedido() + " atualizado com sucesso");

            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com a gravação: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "SisCultbookController?cmd=consultarTodosPedidos";
    }
}
