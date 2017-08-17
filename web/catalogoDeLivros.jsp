<%@page import="java.sql.SQLException"%>
<%@page import="br.com.siscultbook.model.dao.LivroDAO"%>
<%@page import="br.com.siscultbook.conexao.Pool"%>
<%@page import="br.com.siscultbook.conexao.InterfacePool"%>
<%@page import="br.com.siscultbook.bean.Autor"%>
<%@page import="java.util.Set"%>
<%@page import="br.com.siscultbook.bean.Livro"%>
<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.List, br.com.siscultbook.bean.Cliente" %>
<link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="css/catalogoLivro.css" />
<%----%>        
<div id="catalogoLivro">
    <!-- path -->

    <div id="path">
        <ul>
            <li class="base">Você está aqui:</li>
            <li class="first">${titulo}: <font color="#FF0000">${totalDeLivros} produtos cadastrados</font></li>

        </ul>
    </div>
    <!-- end path -->
    <div id="erro">
        ${mensagem}
    </div>
    <%--
        Página é visão que lista todos os clientes cadastrados no banco de dados
    --%>

    <%
                //String formURL = response.encodeURL("/WebExemplos/pedido.jsp");
                //String classe = "linha1";
                InterfacePool pool = Pool.getInstacia();
                pool = (InterfacePool) getServletContext().getAttribute("pool");
                LivroDAO livroDAO = new LivroDAO(pool);
                List<Livro> livros = (List<Livro>) request.getAttribute("livros");
                //System.out.println("quantos livros" + livros.size());                
                String classe = "product1";
                for (Livro livro : livros) {


    %>
    <div class="<%=classe%>">
        <a href="SisCultbookController?cmd=detalhesProduto&cod=<%=livro.getCodigoProduto()%>" title="<%=livro.getTitulo()%>">
            <img src="imgensLivros/<%=livro.getFigura()%>" width="110" height="175" border="0" />
        </a>
<br>
        <div class="contentProduct">
            <a href="SisCultbookController?cmd=detalhesProduto&cod=<%=livro.getCodigoProduto()%>" class="mainSale"><%=livro.getTitulo()%></a> <br>
            <%
                                List<Autor> autores;
                                //recebo via request afirmando que e um Set
                                try {
                                    autores = livroDAO.lerAutores(livro.getCodigoProduto());
                                    //Iterator iterator = autor.iterator();
                                    if (autores != null) {
                                        //pecorro a lista de todos os autores cadastrado no banco que recebi via request
                                        for (Autor autor : autores) {
            %>
            <a href="SisCultbookController?cmd=consultarLivrosPorAutor&amp;codigo=<%=autor.getCodigoAutor()%>" class="autorSale"><span>+</span><%=autor.getSobrenome()%></a>,
            <%
                                        }
                                    }

                                } catch (SQLException e) {
                                    out.println("Problemas na base de dados:" + e.getMessage());
                                }
            %>
            <div class="sale"><hr class="sale"></div>
            <span class="for">por <%=Utilitario.formatarParaMonetario(livro.getPreco()) %></span>
        </div>

    </div>
    <%    classe = classe.equals("product1") ? "product2" : "product1";
                }
                try {
                        Integer anterior;
                        Integer pagina = (Integer) request.getAttribute("pagina");
                        Integer limite = (Integer) request.getAttribute("limite");
                        Integer numOfPages = (Integer) request.getAttribute("numOfPages");
                        Integer totalDeLinhas = (Integer) request.getAttribute("totalDeLinhas");
                        
    %>
    <br /><br />
    <div id="paginacao">
        <%
                                if (pagina != 1) {
                                    anterior = pagina - 1;
                                    out.println("<a href=SisCultbookController?cmd=consultarCatalogo&pagina=" + anterior + " class='pequeno'>" + limite + " Anteriores</a>");
                                } else {
                                    out.println(limite + " Anteriores ");
                                }


                                int i;

                                for (i = 1; i <= numOfPages; i++) {
                                    if (i == pagina) {
                                        out.println("<b>" + i + "</b> ");
                                    } else {
                                        out.println("<a href=SisCultbookController?cmd=consultarCatalogo&pagina=" + i + " class='pequeno' >" + i + "</a> ");
                                    }
                                }

                                if ((totalDeLinhas % limite) != 0) {
                                    if (i == pagina) {
                                        out.println(i + " ");
                                    } else {
                                        out.println("<a href=SisCultbookController?cmd=consultarCatalogo&pagina=" + i + " class='pequeno'>" + i + "</a> ");
                                    }
                                }

                                int proxima;
                                if ((totalDeLinhas - (limite * pagina)) > 0) {
                                    proxima = pagina + 1;

                                    out.println("<a href=SisCultbookController?cmd=consultarCatalogo&pagina=" + proxima + " class='pequeno' >Próximos " + limite + "</a>");
                                } else {
                                    out.println("Próximos " + limite);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                out.println("erro: " + e.getMessage());
                            }


                %>
    </div>
</div>