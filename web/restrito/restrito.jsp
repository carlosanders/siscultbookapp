<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/screen.css" />
<title>Documento sem título</title>

</head>

<body>
<div id="ladob">
<div id="ola">
  				Ol&aacute; <span class="style1"><em>Usuario</em></span>, 
  				seja Bem-Vindo !
  </div>
<div class="faixa_amarela" id="faixa_amarela-lins_cadastro" >
                &nbsp;<img src="../images/icn_rec_servico_off.gif" alt="Mostrar informação" border="0" />&nbsp;Gerenciar Usuários
          </div>
<br />
<div id="lins_cadastro" style="display:; ">
            <ul id="painel">
            		<li>
						<div class="balao2" >
                            <a title="Consultar lista de Clientes" href="SisCultbookController?cmd=consultarCliente" accesskey="13" tabindex="13">
                                <img title="Consultar lista de Clientes" alt="Consultar lista de Clientes" src="../images/IconeEditarClientes.jpg" width="64" height="64" border="0" />
                                <br>Consultar Cliente
                            </a>
                        </div>
                    </li>
                    
                    <li>
						<div class="balao2" >
                            <a title="Cadastrar um novo cliente" href="SisCultbookController?cmd=editarCliente" accesskey="13" tabindex="13">
                                <img title="Cadastrar um novo cliente" alt="Cadastrar um novo cliente" src="../images/IconeClientes.jpg" width="64" height="64" border="0" />
                            <br>
                                Adicionar Cliente
                            </a>
                        </div>
                    </li>
                    
                    <li>
						<div class="balao2" >
                            <a title="Consultar lista de Funcionários" href="SisCultbookController?cmd=consultarFuncionario" accesskey="13" tabindex="13">
                                <img title="Consultar lista de Funcionários" alt="Consultar lista de Funcionários" src="../images/business_user.png" width="64" height="64" border="0" />
                            <br>
                                Consultar Funcionario
                            </a>
                        </div>
                    </li>
                    <li>
						<div class="balao2" >
                            <a title="Cadastrar um novo Funcionário" href="SisCultbookController?cmd=editarFuncionario" accesskey="13" tabindex="13">
                                <img title="Cadastrar um novo Funcionário" alt="Cadastrar um novo Funcionário" src="../images/business_user_add.png" width="64" height="64" border="0" />
                                <br>
                                Adicionar Funcionario
                            </a>
                        </div>
                    </li>

            </ul>
</div>

<div class="faixa_amarela" id="faixa_amarela-lins_cadastro1" >
                &nbsp;<img src="../images/icn_rec_servico_off.gif" alt="Mostrar informação" border="0" />&nbsp;Gerenciar Obra
          </div>
<br />
<div id="lins_cadastro" style="height:250px;">
            <ul id="painel">
            		<li>
						<div class="balao2" >
                            <a title="Consultar lista de Livros" href="SisCultbookController?cmd=consultarLivro" accesskey="13" tabindex="13">
                                <img title="Consultar lista de Livros" alt="Consultar lista de Livros" src="../images/IconeLivros.jpg" width="64" height="64" border="0" />
                                <br>
                                Consultar Livro
                          </a>
                        </div>
                    </li>
                    
                    <li>
						<div class="balao2" >
                            <a title="Cadastrar um novo Livro" href="SisCultbookController?cmd=editarLivro" accesskey="13" tabindex="13">
                                <img title="Cadastrar um novo Livro" alt="Cadastrar um novo Livro" src="../images/IconeLivro.jpg" width="64" height="64" border="0" />
                                <br>
                                Adicionar Livro
                          </a>
                        </div>
                    </li>
                    
                    <li>
						<div class="balao2" >
                            <a title="Consultar lista de Editoras" href="SisCultbookController?cmd=consultarEditora" accesskey="13" tabindex="13">
                                <img title="Consultar lista de Editoras" alt="Consultar lista de Editoras" src="../images/ListarEditora.jpg" width="64" height="64" border="0" />
                                <br>Consultar Editora
                            </a>
                        </div>
                    </li>
                    
                    <li>
						<div class="balao2" >
                            <a title="Cadastrar uma Editora" href="SisCultbookController?cmd=editarEditora" accesskey="13" tabindex="13">
                                <img title="Cadastrar uma Editora" alt="Cadastrar uma Editora" src="../images/IconeEditora.jpg" width="64" height="64" border="0" />
                            <br>
                                Adcionar Editora
                            </a>
                        </div>
                    </li>
                    
                    <li>
						<div class="balao2" >
                            <a title="Consultar lista de Autores" href="SisCultbookController?cmd=consultarAutor" accesskey="13" tabindex="13">
                                <img title="Consultar lista de Autores" alt="Consultar lista de Autores" src="../images/images.png" width="64" height="64" border="0" />
                            <br>
                                Consultar Autor
                            </a>
                        </div>
                    </li>
                    <li>
						<div class="balao2" >
                            <a title="Cadastrar Autor" href="SisCultbookController?cmd=editarAutor" accesskey="13" tabindex="13">
                                <img title="Cadastrar Autor" alt="Cadastrar Autor" src="../images/image_add.png" width="64" height="64" border="0" />
                            <br>
                                Adicionar Autor
                            </a>
                        </div>
                    </li>

            </ul>
</div>  

<div class="faixa_amarela" id="faixa_amarela-lins_cadastro" >
                &nbsp;<img src="../images/icn_rec_servico_off.gif" alt="Mostrar informação" border="0" />&nbsp;Administração do Sistema
          </div>
<br />
<div id="lins_cadastro" style="display:; ">
            <ul id="painel">
            		<li>
						<div class="balao2" >
                            <a title="Consultar Níveis de Acesso" href="SisCultbookController?cmd=consultarNivelAcesso" accesskey="13" tabindex="13">
                                <img title="Consultar Níveis de Acesso" alt="Consultar Níveis de Acesso" src="../images/unlock.png" width="64" height="64" border="0" />
                            <br>
                                Consultar Níveis
                            </a>
                      </div>
                    </li>
                    
                    <li>
						<div class="balao2" >
                            <a title="Cadastrar nível de Acesso" href="SisCultbookController?cmd=editarNivelAcesso" accesskey="13" tabindex="13">
                                <img title="Cadastrar nível de Acesso" alt="Cadastrar nível de Acesso" src="../images/add2.png" width="64" height="64" border="0" />
                            <br>
                                Adicionar Nível
                            </a>
                      </div>
                    </li>
                    
                    <li>
						<div class="balao2" >
                            <a title="Consultar lista de Pedidos" href="SisCultbookController?cmd=consultarTodosPedidos" accesskey="13" tabindex="13">
                                <img title="Consultar lista de Pedidos" alt="Consultar lista de Pedidos" src="../images/kviewshell.png" width="64" height="64" border="0" />
                            <br>
                                Consultar Pedidos
                            </a>
                      </div>
                    </li>
                    <li>
						<div class="balao2" >
                            <a title="Consultar Relatórios" href="SisCultbookController?cmd=consultarRelatorios" accesskey="13" tabindex="13">
                                <img title="Consultar Relatórios" alt="Consultar Relatórios" src="../images/chart2.png" width="64" height="64" border="0" />
                            <br>
                                Consultar Relatórios
                            </a>
                      </div>
                    </li>

            </ul>
</div>


        
</div>

</body>
</html>