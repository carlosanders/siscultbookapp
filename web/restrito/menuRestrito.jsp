<%@page import="br.com.siscultbook.bean.Cliente"%>
<%@page import="br.com.siscultbook.bean.Funcionario"%>
<%@page import="br.com.siscultbook.conexao.Pool"%>
<%@page import="br.com.siscultbook.conexao.InterfacePool"%>
<%@page import="br.com.siscultbook.model.dao.NivelDeAcessoDAO"%>
<%@page import="java.util.Map"%>
<%@page import="br.com.siscultbook.bean.Acesso"%>
<%@page import="java.util.List"%>

<link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />

<!--	 coluna esquerda -->

<div class="leftCol"> 		

    <!-- categorias -->
    <div class="blocoNav">
        <div class="bot">
            <div class="left">
                <div class="right">
                    <div class="left_top">
                        <div class="right_top">
                            <div class="left_bot">
                                <div class="right_bot">
                                    <div class="titulo">Menu Restrito</div>
                                    <ul id="lista">

                                        <%
                                                    InterfacePool pool = Pool.getInstacia();
                                                    pool = (InterfacePool) getServletContext().getAttribute("pool");
                                                    NivelDeAcessoDAO acessoDAO = new NivelDeAcessoDAO(pool);

                                                    if (request.getSession().getAttribute("usuario") instanceof Funcionario) {
                                                        Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
                                                        List<Acesso> acessos = acessoDAO.retornarListaAcessosPorNivel((usuario.getLoginFuncionario().getNivelDeAcesso().getCodigoNivelDeAcesso()));
                                                        //List<Acesso> acessos = (List<Acesso>) request.getAttribute("menuUsuarioFuncionario");
                                                        for (Acesso acesso : acessos) {
                                        %>

                                        <li>
                                            <span class="lista">&#8250;</span>
                                            <a href="SisCultbookController?cmd=<%=acesso.getComando()%>" class="lista" title="<%=acesso.getDescricao()%>">
                                                <%=acesso.getDescricao()%>
                                            </a>
                                        </li>
                                        <%
                                                        }
                                                    }
                                        %>

                                        <%    //vai retornar a lista que o usuario cliente poder acessar
                                                    if (request.getSession().getAttribute("usuario") instanceof Cliente) {
                                                        //List<Acesso> acessos = (List<Acesso>) request.getAttribute("menuUsuarioCliente");
                                                        Cliente usuario = (Cliente) request.getSession().getAttribute("usuario");
                                                        List<Acesso> acessos = acessoDAO.retornarListaAcessosPorNivel((usuario.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso()));
                                                        for (Acesso acesso : acessos) {
                                        %>

                                        <li>
                                            <span class="lista">&#8250;</span>
                                            <a href="SisCultbookController?cmd=<%=acesso.getComando()%>" class="lista" title="<%=acesso.getDescricao()%>">
                                                <%=acesso.getDescricao()%>
                                            </a>
                                        </li>

                                        <%
                                                        }
                                                    }
                                        %>

                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="push10"></div>


    <!-- promoção --><!-- newsletter --></div>
<!-- end leftCol -->