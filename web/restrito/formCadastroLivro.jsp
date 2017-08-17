<%@page import="br.com.siscultbook.bean.Assunto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="br.com.siscultbook.bean.Autor"%>
<%@page import="java.util.List"%>
<%@page import="br.com.siscultbook.bean.Editora"%>
<%----%>  
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" type="text/css" href="../css/screen.css" />
      
<div id="cadastros">
            <!-- path -->

        <div id="path">
            <ul>
                <li class="base">Você está aqui:</li>
                <li class="first">${titulo}</li>

            </ul>
        </div>
        <!-- end path -->
        <div id="erro">
        	<font color="#FF0000" style="font-weight:bold">${erro}</font>
            <br />
        	${mensagem}
        </div>
    <%--
        esse form a action dele vai enviar para o SisCultbookController, ele vai receber e redirecionar para
        SisCultbookHelper e o helper vai capturar o valor do paramentro "cmd" input hidden
        de acordo com o parametro ele ira tratar essa requisição e devolver para o SisCultbookController
        com uma página de destino
    --%>
    <form name="form" id="form" action="SisCultbookController" method="post" >
        <input type='hidden' name='cmd' value="cadastrarLivro" />
        <fieldset class="contato">
            <legend>Informa&ccedil;&otilde;es Pessoais</legend>
            <div>
              <label for="isbn">ISBN do Livro</label> <input id="isbn" name="isbn" value="${produto.isbn}" type="text">
            </div>
            <div>
                <label for="titulo">Titulo do Livro</label> <input id="titulo" name="titulo" value="${produto.titulo}" type="text">
            </div>
            <div>
                <label for="assunto">Assunto</label>
                <select name="assunto" id="estado" class="fabrikinput inputbox">
                    <option value="" selected="selected">Escolha o Assunto</option>
                    <% for (Assunto assunto : Assunto.values()) {%>
                    <% if (assunto.equals(request.getAttribute("livroAssunto"))) {%>
                    <option value="<%=assunto.name()%>" selected="selected" ><%=assunto.name()%></option>
                    <% } else {%>
                    <option value="<%=assunto.name()%>"><%=assunto.name()%></option>
                    <% }%>
                    <% }%>
                </select>
            </div>
            <div>
                <label for="estoque">Qtd Estoque</label> <input id="estoque" name="estoque" value="${produto.estoque}" type="text">
            </div>
            <div>
                <label for="figura">Enviar Figura</label> <input id="figura" name="figura" value="${produto.figura}" type="text">
            </div>
            <div>
                <label for="anoPublicacao">Ano da Publicação</label> <input id="anoPublicacao" name="anoPublicacao" value="${produto.anoPublicacao}" type="text">
            </div>
            <div>
                <label for="listaAutores">Autores Cadastrados</label>
                <select name="listaAutores" size="4" multiple class="fabrikinput inputbox" >                   
                    <% List<Autor> autores = (List<Autor>) request.getAttribute("autores");
                       String[] teste;
                       teste = (String[]) request.getAttribute("teste");
                       List<Integer> valores = new ArrayList<Integer>();
                       
                       if (teste != null) {
                           for(int a = 0; a < teste.length; a++){
                            valores.add(Integer.parseInt(teste[a]));
                           }
                           for (Autor autor : autores) {%>
                    <option value="<%=autor.getCodigoAutor() %>"
                            <% if(valores.contains(autor.getCodigoAutor())){ out.println("selected");} %> ><%=autor.getSobrenome() %></option>

                    <% } %>
                    <% }else{
                           System.out.println("debug - " + request.getAttribute("autores"));
                           for (Autor autor : autores) { %>
                             <option value="<%=autor.getCodigoAutor() %>" ><%=autor.getSobrenome() %></option>
                         <%} %>
                    <% }%>
                </select>
            </div>

            <div>
                <label for="descricaoCurta">Descrição Curta</label> <input id="descricaoCurta" name="descricaoCurta" value="${produto.descricaoCurta}" type="text">
            </div>
            <div>
                <label for="descricaoLonga">Descrição Longa</label> <input id="descricaoLonga" name="descricaoLonga" value="${produto.descricaoLonga}" type="text">
            </div>
            <div>
                <label for="preco">Preço</label> <input id="preco" name="preco" value="${produto.preco}" type="text">
            </div>

            <div>
                <label for="editoraCodigo">Editora Cadastradas</label>
                <select name="editoraCodigo" id="editoraCodigo" class="fabrikinput inputbox">
                    <option value="" selected="selected">Escolha a Editora</option>
                    <% List<Editora> editoras = (List<Editora>) request.getAttribute("editoras");
                       for (Editora editora : editoras) {
                           if (editora.getCodigoEditora() == request.getAttribute("produtoEditora")) {
                    %>
                    <option value="<%=editora.getCodigoEditora()%>" selected="selected" ><%=editora.getNomeEditora()%></option>
                    <% } else {%>
                    <option value="<%=editora.getCodigoEditora()%>" ><%=editora.getNomeEditora()%></option>
                    <% }%>
                    <% }%>
                </select>
            </div>
        </fieldset>

        <div>

            <button type="submit" id="submit-go"><p style="margin-top:-4px;">Salvar</p></button>
        </div>
    </form>
</div>