package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Autor;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.Sexo;
import br.com.siscultbook.bean.Status;
import br.com.siscultbook.model.dao.InterfaceAutorDAO;
import br.com.siscultbook.util.Utilitario;
import br.com.siscultbook.util.ValidarCpfCnpj;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlosanders
 */
public class AtualizarAutor implements InterfaceCommand {

    private InterfaceAutorDAO autorDAO;

    public AtualizarAutor(InterfaceAutorDAO autorDAO) {
        super();
        this.autorDAO = autorDAO;

    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Autor autor = new Autor();
        /*Expressão regular para validar data*/
        Pattern pData = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
        /* adciono um título para a página */
        request.setAttribute("titulo", "Atualizar - Autor(a)");

        boolean retorno = false;
        String mensagem = "";

        try {

            String nomeCompleto = request.getParameter("nomeCompleto");
            if (nomeCompleto.equals("") || nomeCompleto == null || nomeCompleto.length() < 3) {
                mensagem = mensagem + "O campo Nome completo, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            }else{
                autor.setNomeCompleto(nomeCompleto);
            }

            String cep = request.getParameter("cep");
            cep = cep.replace("-", "");
            if (cep.equals("") || cep == null || cep.length() != 8) {
                mensagem = mensagem + "Digite um CEP, ele deve conter 8 caracteres.<br />";
                retorno = true;
            }else{
                autor.getEndereco().setCep(cep);
            }

            String dataNascimento = request.getParameter("dataNascimento");
            Matcher mDataNascimento = pData.matcher(dataNascimento);
            if (dataNascimento.equals("") || dataNascimento == null || !mDataNascimento.find()
                    || Utilitario.verificaData(dataNascimento) == null) {
                mensagem = mensagem + "Digite uma data válida, para o campo nascimento.<br />";
                retorno = true;
            }else{
                autor.setDataNascimento(Utilitario.verificaData(dataNascimento));
            }

            String sexo = request.getParameter("sexo");
            if (sexo == null || sexo.equals("")) {
                mensagem = mensagem + "O campo sexo não foi selecionado.<br />";
                retorno = true;
            }else{
                autor.setSexo(Sexo.valueOf(Sexo.class, sexo));
            }

            String cpf = request.getParameter("cpf");
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
            if (cpf.equals("") || cpf == null || cpf.length() != 11
                    || ValidarCpfCnpj.valida_CpfCnpj(cpf) == false) {
                mensagem = mensagem + "Digite um CPF válido, ele deve conter 11 caracteres.<br />";
                retorno = true;
            }else{
                autor.setCpf(cpf);
            }

            String rua = request.getParameter("rua");
            if (rua.equals("") || rua == null || rua.length() < 3) {
                mensagem = mensagem + "O campo rua deve ser maior que 3 caracteres.<br />";
                retorno = true;
            }else{
                autor.getEndereco().setRua(rua);
            }

            String bairro = request.getParameter("bairro");
            if (bairro.equals("") || bairro == null || bairro.length() < 3) {
                mensagem = mensagem + "O campo bairro deve ser maior que 3 caracteres.<br />";
                retorno = true;
            }else{
                autor.getEndereco().setBairro(bairro);
            }
            String cidade = request.getParameter("cidade");
            if (cidade.equals("") || cidade == null || cidade.length() < 3) {
                mensagem = mensagem + "O campo cidade deve ser maior que 3 caracteres.<br />";
                retorno = true;
            }else{
                autor.getEndereco().setCidade(cidade);
            }

            String valorEstado = request.getParameter("estado");
            if (valorEstado == null || valorEstado.equals("")) {
                mensagem = mensagem + "Selecione o Estado.<br />";
                retorno = true;
            }else{
                autor.getEndereco().setEstado(Estados.valueOf(Estados.class, valorEstado));
            }

            String autorSobrenome = request.getParameter("autorSobrenome");
            if (autorSobrenome.equals("") || autorSobrenome == null) {
                mensagem = mensagem + "O campo sobrenome está em branco ou vazio.<br />";
                retorno = true;
            }else{
                autor.setSobrenome(autorSobrenome);
            }

            String statusAutor = request.getParameter("statusAutor");
            if (statusAutor.equals("") || statusAutor == null) {
                mensagem = mensagem + "Selecione uma opção do status.<br />";
                retorno = true;
            }else{
                autor.setStatus(Status.valueOf(Status.class, statusAutor));
            }


            /* Após passar por cada if acimo vamos fazer uma verificação se todos os dados vindo do form estão de
             * acordo. Se a variavel retorno for true então algo está errado e precisa ser corrigido
             * senão tudo está ok então daremos prosseguimento a gravação no bando de dados
             */
            //seto os valores do form no bean Autor
            autor.setCodigoAutor(Integer.parseInt(request.getParameter("codigo")));
            /* envio as expression laguage do funcionario para página através dos request abaixo */
            request.setAttribute("autorDataNascimento", Utilitario.DataFormatada(autor.getDataNascimento()));
            request.setAttribute("autorSexo", autor.getSexo());
            request.setAttribute("autorEstado", autor.getEndereco().getEstado());
            request.setAttribute("autorStatus", autor.getStatus());

            if (retorno) {

                request.setAttribute("mensagem", mensagem);
                request.setAttribute("autor", autor);
                request.setAttribute("erro", "Dados Incorretos");
                /* caso passe por aqui retorno a página por usuario corrigir e enviar novamente */
                return "restrito/atualizaAutor.jsp";

            } else {
                /* caso tudo esteja correto semanticamente então vou gravar no banco */
                autorDAO.atualizar(autor);
                /* envio mensagem de sucesso */
                request.setAttribute("mensagem", "Autor(a) (" + autor.getNomeCompleto() + ") atualizado com sucesso!");

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
        request.setAttribute("autor", autor);
        return "SisCultbookController?cmd=consultarAutor";
    }
}
