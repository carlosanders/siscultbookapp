package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Carrinho;
import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.Entrega;
import br.com.siscultbook.bean.EstadoDoPedido;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.PagamentoBoleto;
import br.com.siscultbook.bean.PagamentoCartao;
import br.com.siscultbook.bean.Pedido;
import br.com.siscultbook.bean.TipoCartao;
import br.com.siscultbook.model.dao.EntregaDAO;
import br.com.siscultbook.model.dao.PagamentoDAO;
import br.com.siscultbook.model.dao.PedidoDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
public class GravarPedido implements InterfaceCommand {

    private PedidoDAO pedidoDAO;
    private EntregaDAO entregaDAO;
    private PagamentoDAO pagamentoDAO;

    public GravarPedido(PedidoDAO pedidoDAO, EntregaDAO entregaDAO, PagamentoDAO pagamentoDAO) {
        super();
        this.pedidoDAO = pedidoDAO;
        this.entregaDAO = entregaDAO;
        this.pagamentoDAO = pagamentoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("titulo", "Pedido Finalizado");
        boolean retorno = false;
        String mensagem = "";
        String sucesso = "";
        Cliente cliente;
        HttpSession session = request.getSession(true);
        Carrinho carrinho;
        Entrega entrega = new Entrega();
        Pattern pTelefone = Pattern.compile("^[0-9]{2}[0-9]{3,4}[0-9]{4}$");

        PagamentoCartao pagamentoCartao = null;
        PagamentoBoleto pagamentoBoleto = null;

        try {

            String nome_ent = request.getParameter("nome_ent");
            if (nome_ent.equals("") || nome_ent == null || nome_ent.length() < 3) {
                mensagem = mensagem + "O campo Nome, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            } else {
                entrega.setNomeCompleto(nome_ent);

            }

            String endereco_ent = request.getParameter("endereco_ent");
            if (endereco_ent.equals("") || endereco_ent == null || endereco_ent.length() < 3) {
                mensagem = mensagem + "O campo Endereço, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setRua(endereco_ent);
            }

            String bairro_ent = request.getParameter("bairro_ent");
            if (bairro_ent.equals("") || bairro_ent == null || bairro_ent.length() < 3) {
                mensagem = mensagem + "O campo Bairro, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setBairro(bairro_ent);
            }

            String cidade_ent = request.getParameter("cidade_ent");
            if (cidade_ent.equals("") || cidade_ent == null || cidade_ent.length() < 3) {
                mensagem = mensagem + "O campo Cidade, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setCidade(cidade_ent);
            }

            String valorEstado = request.getParameter("uf_ent");
            if (valorEstado == null || valorEstado.equals("")) {
                mensagem = mensagem + "Selecione o Estado.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setEstado(Estados.valueOf(Estados.class, valorEstado));
            }

            String cep = request.getParameter("cep_ent");
            cep = cep.replace("-", "");
            if (cep.equals("") || cep == null || cep.length() != 8) {
                mensagem = mensagem + "Digite um CEP, ele deve conter 8 caracteres.<br />";
                retorno = true;
            } else {
                entrega.getEndereco().setCep(cep);
            }

            String telefoneContato = request.getParameter("telefone_ent");
            telefoneContato = telefoneContato.replace("(", "");
            telefoneContato = telefoneContato.replace(")", "");
            telefoneContato = telefoneContato.replace(" ", "");
            telefoneContato = telefoneContato.replace("-", "");
            Matcher m = pTelefone.matcher(telefoneContato);
            if (telefoneContato.equals("") || telefoneContato == null || !m.find()) {
                mensagem = mensagem + "O telefone de contato, deve conter apenas números.<br />";
                retorno = true;
            } else {
                entrega.setTelefone(telefoneContato);
            }

            String ffrete = request.getParameter("ffrete");
            if (ffrete == null) {
                mensagem = mensagem + "Selecione uma forma de entrega.<br />";
                retorno = true;
            } else {
                if (ffrete.equals("n")) {
                    entrega.setFrete(0.00);
                } else if (ffrete.equals("s")) {
                    //valor do carrinho
                    entrega.setFrete((Double) session.getAttribute("valorCep"));
                }
            }

            String fpagamento = request.getParameter("fpagamento");
            if (fpagamento == null) {
                mensagem = mensagem + "Selecione uma forma de pagamento.<br />";
                retorno = true;
            } else if (fpagamento.equals("cartao")) {

                pagamentoCartao = new PagamentoCartao();
                String tipoCartao = request.getParameter("tipo_cartao");
                //System.out.println("form - " + tipoCartao);
                if (tipoCartao == null || tipoCartao.equals("")) {
                    mensagem = mensagem + "Selecione seu cartão: <br />";
                    retorno = true;
                } else {
                    pagamentoCartao.setBandeira(TipoCartao.valueOf(TipoCartao.class, tipoCartao));
                    //System.out.println("dentro else - " + tipoCartao);
                }

                String nrParcelas = request.getParameter("parcelas");
                if (nrParcelas.equals("") || nrParcelas == null) {
                    mensagem = mensagem + "Selecione o parcelamento: <br />";
                    retorno = true;
                } else {

                    pagamentoCartao.setNumeroParcelas(Integer.parseInt(nrParcelas));
                }

                String nomeCartao = request.getParameter("nome_cartao");
                if (nomeCartao.equals("") || nomeCartao == null) {
                    mensagem = mensagem + "O campo Nome do Titular deve ser preenchido: <br />";
                    retorno = true;
                } else {
                    pagamentoCartao.setNomeTitular(nomeCartao);
                }

                String nrCartao = request.getParameter("num_cartao");
                if (nrCartao.equals("") || nrCartao == null) {
                    mensagem = mensagem + "O campo Número do cartão deve ser preenchido: <br />";
                    retorno = true;
                } else {
                    pagamentoCartao.setNumeroCartao(nrCartao);
                }

                String codSeguranca = request.getParameter("cod_verificador");
                if (codSeguranca.equals("") || codSeguranca == null || codSeguranca.length() != 3) {
                    mensagem = mensagem + "O campo código verificador deve ser preenchido, com 3 caracteres: <br />";
                    retorno = true;
                } else {
                    pagamentoCartao.setCodigoSeguranca(codSeguranca);
                }

                String mesValidade = request.getParameter("mes_validade");
                String anoValidade = request.getParameter("ano_validade");
                if (mesValidade.equals("") || mesValidade == null) {
                    mensagem = mensagem + "Selecione o mês de validade do cartão: <br />";
                    retorno = true;
                } else if (anoValidade.equals("") || anoValidade == null) {
                    mensagem = mensagem + "Selecione o ano de validade do cartão: <br />";
                    retorno = true;
                } else {
                    pagamentoCartao.setValidadeCartao(mesValidade + anoValidade);
                }

            } else if (fpagamento.equals("boleto")) {
                pagamentoBoleto = new PagamentoBoleto();
                this.camposPagamentoBoleto(pagamentoBoleto);

            }

            if (retorno) {
                request.setAttribute("entregaEstado", entrega.getEndereco().getEstado());
                request.setAttribute("erro", "Dados Incorretos:");
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("entrega", entrega);

                return "SisCultbookController?cmd=finalizarCompra";
            } else {
                if (session.getAttribute("carrinho") != null) {
                    synchronized (session) {
                        carrinho = (Carrinho) session.getAttribute("carrinho");
                        Pedido pedido = new Pedido();

                        GregorianCalendar datahj = new GregorianCalendar();
                        datahj.setTime(new Date());
                        pedido.setDataPedido(datahj);

                        if (session.getAttribute("usuario") instanceof Cliente) {
                            cliente = (Cliente) session.getAttribute("usuario");
                            pedido.setDescricao("Item vendido");

                            pedido.setStatusPedido(EstadoDoPedido.AguardandoConfirmacaoPagamento);
                            pedido.setCliente(cliente);
                            pedido.setItens(carrinho.buscarItens());

                            pedidoDAO.salvar(pedido);
                            pedido.setCodigoPedido(pedidoDAO.recuperaCodigoPedido());
                            entrega.getPedido().setCodigoPedido(pedidoDAO.recuperaCodigoPedido());

                            if (pagamentoCartao != null) {
                                pagamentoCartao.setPedido(pedido);
                                pagamentoCartao.setValorTotal(carrinho.calcularTotalCarrinho());
                                double calculoTotal = ((Double) session.getAttribute("valorCep") + carrinho.calcularTotalCarrinho()) / pagamentoCartao.getNumeroParcelas();
                                pagamentoCartao.setValorPacela(calculoTotal);
                                pagamentoDAO.salvarPagamento(pagamentoCartao);
                                pagamentoCartao.setCodigoPagamento(pagamentoDAO.recuperaCodigoPagamento());
                                pagamentoDAO.salvarPagamentoCartao(pagamentoCartao);
                                request.setAttribute("pagamento", pagamentoCartao);
                                sucesso += "<br />em breve você receberá um email de confirmação de pagamento!";


                            } else {
                                pagamentoBoleto.setPedido(pedido);
                                pagamentoBoleto.setValorTotal(carrinho.calcularTotalCarrinho());
                                pagamentoDAO.salvarPagamento(pagamentoBoleto);
                                pagamentoBoleto.setCodigoPagamento(pagamentoDAO.recuperaCodigoPagamento());
                                pagamentoDAO.salvarPagamentoBoleto(pagamentoBoleto);
                                request.setAttribute("pagamento", pagamentoBoleto);
                                sucesso += "<br />em breve você receberá um email com o link do boleto para pagamento!";

                            }

                            pedidoDAO.salvarListaPedidoProduto(pedido);
                            entregaDAO.salvarPagamento(entrega);

                            request.setAttribute("entrega", entrega);
                            request.setAttribute("pedido", pedido);
                            request.setAttribute("cliente", cliente);
                            request.setAttribute("itens", pedido.getItens());
                            //System.out.println("itens - " + pedido.getItens());
                            request.setAttribute("valorTotalCarrinho", Utilitario.formatarParaMonetario(carrinho.calcularTotalCarrinho()));
                            request.setAttribute("valorCep", Utilitario.formatarParaMonetario((Double) session.getAttribute("valorCep")));
                            double valorTotPedido = (Double) session.getAttribute("valorCep") + carrinho.calcularTotalCarrinho();
                            //System.out.println("valor 1 - " + pedido.calcularTotal());
                            //System.out.println("valor 2 - " + valorTotPedido);
                            request.setAttribute("valorTotalPedido", Utilitario.formatarParaMonetario(valorTotPedido));
                            sucesso += "<br />Obrigado por comprar com a CULTBOOK LIVRARIA!<br /><br />";
                            request.setAttribute("mensagem", "PEDIDO CÓD. (" + pedido.getCodigoPedido() + ") enviado com sucesso!" + sucesso);


                        }
                    }
                }
            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("arquivo", "sucessoCompra.jsp");
        //return "arquivosEstaticos.jsp";
        return "arquivosEstaticos.jsp";
    }

    private void camposPagamentoBoleto(PagamentoBoleto pagamentoBoleto) {

        pagamentoBoleto.setCedente("Livraria CULTBOOK");
        pagamentoBoleto.setNumeroDocumento("0123456789");
        //simulacao de um vencimento para o boleto baseado na data do sistema
        GregorianCalendar data = new GregorianCalendar();
        data.setTime(new Date());
        data.add(Calendar.DATE, 15);
        pagamentoBoleto.setVencimento(data);

    }
}
