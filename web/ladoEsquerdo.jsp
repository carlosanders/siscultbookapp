<%@page import="br.com.siscultbook.bean.Cliente"%>
<%@page import="br.com.siscultbook.bean.Funcionario"%>
<%@page import="br.com.siscultbook.conexao.Pool"%>
<%@page import="br.com.siscultbook.conexao.InterfacePool"%>
<%@page import="br.com.siscultbook.model.dao.NivelDeAcessoDAO"%>
<%@page import="java.util.Map"%>
<%@page import="br.com.siscultbook.bean.Acesso"%>
<%@page import="java.util.List"%>
<!--
<link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
-->
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
                                    <div class="titulo">Categorias</div>
                                    <ul id="lista">

                                        <li><span class="lista">&#8250;</span> <a href="SisCultbookController?cmd=consultarCatalogo" class="lista">Cat&aacute;logo de Livros</a></li>

                                        <li><span class="lista">&#8250;</span> <a href="SisCultbookController?cmd=arquivosEstaticos&pg=autores" class="lista">Autores</a></li>

                                        <li><span class="lista">&#8250;</span> <a href="SisCultbookController?cmd=arquivosEstaticos&pg=editoras" class="lista">Editoras</a></li>

                                        <li><span class="lista">&#8250;</span> <a href="SisCultbookController?cmd=arquivosEstaticos&pg=assuntos" class="lista">Assuntos</a></li>


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


    <!-- promo��o --><!-- newsletter -->
  <script language="javascript" src="scripts/validaCampos.js" type="text/javascript" ></script>
    <div class="blocoExtra">
        <div class="bot">
            <div class="left">
                <div class="right">
                    <div class="left_top">
                        <div class="right_top">
                            <div class="left_bot">
                                <div class="right_bot">
                                    <div class="titulo">Newsletter</div>

                                    <br>
                                    <label class="newsletterSmall" for="nome news">Receba em seu e-mail as novidades e lan�amentos da <b>Cultbook.com.br</b></label>
                                    <div class="push10"></div>
                                    <form method="post" action="#" id="form1" name="form1" onsubmit="return valida(document.form1);">
                                        <label class="newsletter" for="nome news">Nome</label><br>
                                        <input name="nome" style="width: 160px;" class="campo" type="text"><br>
                                        <label class="newsletter" for="email news">e-Mail</label><br>
                                        <input name="email" style="width: 160px;" class="campo" type="text"><br>
                                        <div class="push10"></div>
                                        <input name="enviar" value="Assinar" class="botao" type="submit"><br>
                                    </form>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- end leftCol -->