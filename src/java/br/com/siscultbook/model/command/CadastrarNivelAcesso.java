/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Acesso;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class CadastrarNivelAcesso implements InterfaceCommand {

    private NivelDeAcessoDAO acessoDAO;

    public CadastrarNivelAcesso(NivelDeAcessoDAO acessoDAO) {
        super();
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Acesso nivel = new Acesso();

        /* adciono um título para a página */
        request.setAttribute("titulo", "Cadastro - Acesso");

        boolean retorno = false;
        String mensagem = "";

        try {

            String nomeNivelAcesso = request.getParameter("nomeNivelAcesso");
            if (nomeNivelAcesso.equals("") || nomeNivelAcesso == null || nomeNivelAcesso.length() < 3) {
                mensagem = mensagem + "O campo deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            }

            nivel.getNivelDeAcesso().setNivelDeAcesso(nomeNivelAcesso);

            if (retorno) {

                request.setAttribute("nivel", nivel);
          
                request.setAttribute("mensagem", mensagem);
                /* caso passe por aqui retorno a página por usuario corrigir e enviar novamente */
                return "restrito/cadastroNivelAcesso.jsp";

            } else {
                /* caso tudo esteja correto semanticamente então vou gravar no banco */
                acessoDAO.salvarNivelDeAcesso(nivel);
                request.setAttribute("mensagem", "Acesso (" + nivel.getNivelDeAcesso().getNivelDeAcesso() + ") cadastrado com sucesso!");
                request.setAttribute("nivel", nivel);
            }



        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com a gravação: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
            e.printStackTrace();
        }

        return "SisCultbookController?cmd=consultarNivelAcesso";
    }
}
