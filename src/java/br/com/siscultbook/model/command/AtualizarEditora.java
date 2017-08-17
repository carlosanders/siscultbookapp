/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Editora;
import br.com.siscultbook.bean.Status;
import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import br.com.siscultbook.util.ValidarCpfCnpj;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class AtualizarEditora implements InterfaceCommand {

    private InterfaceEditoraDAO editoraDAO;

    public AtualizarEditora(InterfaceEditoraDAO editoraDAO) {
        super();
        this.editoraDAO = editoraDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Editora editora = new Editora();
        request.setAttribute("titulo", "Atualização - Editora");

        boolean retorno = false;
        String mensagem = "";

        try {

            String nomeEditora = request.getParameter("nomeEditora");
            if (nomeEditora.equals("") || nomeEditora == null || nomeEditora.length() < 5) {
                mensagem = mensagem + "Digite seu nome completo, deve conter no mínino 5 caracteres.<br />";
                retorno = true;
            }

            String cnpj = request.getParameter("cnpj");
            cnpj = cnpj.replace(".", "");
            cnpj = cnpj.replace("/", "");
            cnpj = cnpj.replace("-", "");
            //System.out.println("DADO EDITORA CNPJ- " + cnpj);
            if (cnpj.equals("") || cnpj == null || cnpj.length() != 14
                    || ValidarCpfCnpj.valida_CpfCnpj(cnpj) == false) {
                mensagem = mensagem + "Digite um CNPJ, ele deve conter 14 caracteres.<br />";
                retorno = true;
            }

            String statusEditora = request.getParameter("statusEditora");
            if (statusEditora.equals("") || statusEditora == null) {
                mensagem = mensagem + "Selecione uma opção do status.<br />";
                retorno = true;
            }else{
                editora.setStatus(Status.valueOf(Status.class, statusEditora));
            }

            //seto os valores do form no bean Cliente
            editora.setCodigoEditora(Integer.valueOf(request.getParameter("codigo")));
            editora.setNomeEditora(nomeEditora);
            editora.setCnpj(cnpj);
            editora.setObs(request.getParameter("obs"));

            /* envio as expression laguage do funcionario para página através dos request abaixo */
            request.setAttribute("editora", editora);

            if (retorno) {
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("editora", editora);
                request.setAttribute("editoraStatus", editora.getStatus());

                /* caso passe por aqui retorno a página por usuario corrigir e enviar novamente */
                return "restrito/atualizaEditora.jsp";

            } else {

                /* caso tudo esteja correto semanticamente então vou gravar no banco */
                editoraDAO.atualizar(editora);
                /* envio mensagem de sucesso do cadastro do funcionario para página através dos request abaixo */
                request.setAttribute("mensagem", "Editora (" + editora.getNomeEditora() + ") atualizado com sucesso!");

            }


        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com a gravação:</font> " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
            e.printStackTrace();
        }

        /* Tudo ocorrendo bem e após ser gravado no banco de dados envio para o SisCultbookController
         * a página abaixo com o funcionario gravado com sucesso
         */
        request.setAttribute("editora", editora);
        return "SisCultbookController?cmd=consultarEditora";
    }
}
