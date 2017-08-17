/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cargo;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.Funcionario;
import br.com.siscultbook.bean.Sexo;
import br.com.siscultbook.bean.Status;
import br.com.siscultbook.model.dao.InterfaceFuncionarioDAO;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import br.com.siscultbook.util.Utilitario;
import br.com.siscultbook.util.ValidarCpfCnpj;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class AtualizarFuncionarioPainel implements InterfaceCommand {

    private InterfaceFuncionarioDAO funcionarioDAO;

    public AtualizarFuncionarioPainel(InterfaceFuncionarioDAO funcionarioDAO) {
        super();
        this.funcionarioDAO = funcionarioDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Funcionario funcionario = new Funcionario();
        /*Expressão regular para validar data*/
        Pattern pData = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
        /* adciono um título para a página */
        request.setAttribute("titulo", "Atualização - Funcionário");

        boolean retorno = false;
        String mensagem = "";

        try {

            funcionario = funcionarioDAO.getFuncionario(Integer.parseInt(request.getParameter("codigo")));
            
            String nomeCompleto = request.getParameter("nomeCompleto");
            if (nomeCompleto.equals("") || nomeCompleto == null || nomeCompleto.length() < 3) {
                mensagem = mensagem + "O campo Nome completo, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            } else {
                funcionario.setNomeCompleto(nomeCompleto);
            }

            String cep = request.getParameter("cep");
            cep = cep.replace("-", "");
            if (cep.equals("") || cep == null || cep.length() != 8) {
                mensagem = mensagem + "Digite um CEP, ele deve conter 9 caracteres.<br />";
                retorno = true;
            } else {
                funcionario.getEndereco().setCep(cep);
            }

            String dataNascimento = request.getParameter("dataNascimento");
            Matcher mDataNascimento = pData.matcher(dataNascimento);
            if (dataNascimento.equals("") || dataNascimento == null || !mDataNascimento.find()
                    || Utilitario.verificaData(dataNascimento) == null) {
                mensagem = mensagem + "Digite uma data válida, para o campo nascimento.<br />";
                retorno = true;
            } else {
                funcionario.setDataNascimento(Utilitario.verificaData(dataNascimento));
            }

            String sexo = request.getParameter("sexo");
            if (sexo == null || sexo.equals("")) {
                mensagem = mensagem + "O campo sexo não foi selecionado.<br />";
                retorno = true;
            } else {
                funcionario.setSexo(Sexo.valueOf(Sexo.class, sexo));
            }
 
            String rua = request.getParameter("rua");
            if (rua.equals("") || rua == null || rua.length() < 3) {
                mensagem = mensagem + "O campo rua está em branco ou vazio.<br />";
                retorno = true;
            } else {
                funcionario.getEndereco().setRua(rua);
            }

            String bairro = request.getParameter("bairro");
            if (bairro.equals("") || bairro == null || bairro.length() < 3) {
                mensagem = mensagem + "O campo bairro está em branco ou vazio.<br />";
                retorno = true;
            } else {
                funcionario.getEndereco().setBairro(bairro);
            }

            String cidade = request.getParameter("cidade");
            if (cidade.equals("") || cidade == null || cidade.length() < 3) {
                mensagem = mensagem + "O campo cidade está em branco ou vazio.<br />";
                retorno = true;
            } else {
                funcionario.getEndereco().setCidade(cidade);
            }

            String valorEstado = request.getParameter("estado");
            if (valorEstado == null || valorEstado.equals("")) {
                mensagem = mensagem + "Selecione o Estado civel do cliente.<br />";
                retorno = true;
            } else {
                funcionario.getEndereco().setEstado(Estados.valueOf(Estados.class, valorEstado));
            }

            String senha = request.getParameter("senha");
            if (senha.equals("") || senha == null || senha.length() < 6) {
                mensagem = mensagem + "O campo senha, deve conter no mínino 6 caracteres.<br />";
                retorno = true;
            } else {
                funcionario.getLoginFuncionario().setSenha(senha);
            }

            /* envio as expression laguage do funcionario para página através dos request abaixo */
            request.setAttribute("funcionarioDataNascimento", Utilitario.DataFormatada(funcionario.getDataNascimento()));
            request.setAttribute("funcionarioSexo", funcionario.getSexo());
            request.setAttribute("funcionarioEstado", funcionario.getEndereco().getEstado());
            request.setAttribute("funcionario", funcionario);

            if (retorno) {

                request.setAttribute("mensagem", mensagem);
                request.setAttribute("erro", "Dados Incorretos:");
                /* caso passe por aqui retorno a página por usuario corrigir e enviar novamente */
                request.setAttribute("titulo", "Meus Dados");
                request.setAttribute("arquivo", "formAtualizaFuncionario.jsp");
                return "dadosCliente.jsp";

            } else {
                /* caso tudo esteja correto semanticamente então vou gravar no banco */
                funcionarioDAO.atualizar(funcionario);
                /* envio mensagem de sucesso */
                request.setAttribute("mensagem", "Funcionario (" + funcionario.getNomeCompleto() + ") atualizado com sucesso!");

            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com a gravação: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
            e.printStackTrace();
        }

        /* Tudo ocorrendo bem e após ser gravado no banco de dados envio para o SisCultbookController
         * a página abaixo com o funcionario gravado com sucesso
         */
        request.setAttribute("funcionario", funcionario);
        return "SisCultbookController?cmd=paginaPrincipal";
    }
}
