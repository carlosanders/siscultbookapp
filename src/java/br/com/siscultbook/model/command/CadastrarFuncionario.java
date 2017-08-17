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
public class CadastrarFuncionario implements InterfaceCommand {

    private InterfaceFuncionarioDAO funcionarioDAO;
    private NivelDeAcessoDAO acessoDAO;

    public CadastrarFuncionario(InterfaceFuncionarioDAO funcionarioDAO, NivelDeAcessoDAO acessoDAO) {
        super();
        this.funcionarioDAO = funcionarioDAO;
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Funcionario funcionario = new Funcionario();
        /*Expressão regular para validar data*/
        Pattern pData = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
        /* adciono um título para a página */
        request.setAttribute("titulo", "Cadastro - Funcionário");

        boolean retorno = false;
        String mensagem = "";

        try {

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
                mensagem = mensagem + "Digite um CEP, ele deve conter 8 caracteres.<br />";
                retorno = true;
            }else{
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

            String cargo = request.getParameter("cargo");
            if (cargo == null || cargo.equals("")) {
                mensagem = mensagem + "O campo cargo não foi selecionado.<br />";
                retorno = true;
            } else {
                funcionario.setCargo(Cargo.valueOf(Cargo.class, cargo));
            }

            String dataAdmissao = request.getParameter("dataAdmissao");
            Matcher mDataAdmissao = pData.matcher(dataAdmissao);
            if (dataAdmissao.equals("") || dataAdmissao == null || !mDataAdmissao.find()
                    || Utilitario.verificaData(dataAdmissao) == null) {
                mensagem = mensagem + "Digite uma data válida, para o campo data de admissão.<br />";
                retorno = true;
            } else {
                funcionario.setDataAdmissao(Utilitario.verificaData(dataAdmissao));
            }

            funcionario.setDataDemissao(null);
            String rua = request.getParameter("rua");
            if (rua.equals("") || rua == null || rua.length() < 3) {
                mensagem = mensagem + "O campo rua deve ser maior que 3 caracteres.<br />";
                retorno = true;
            } else {
                funcionario.getEndereco().setRua(rua);
            }

            String bairro = request.getParameter("bairro");
            if (bairro.equals("") || bairro == null || bairro.length() < 3) {
                mensagem = mensagem + "O campo bairro deve ser maior que 3 caracteres.<br />";
                retorno = true;
            } else {
                funcionario.getEndereco().setBairro(bairro);
            }

            String cidade = request.getParameter("cidade");
            if (cidade.equals("") || cidade == null || cidade.length() < 3) {
                mensagem = mensagem + "O campo cidade deve ser maior que 3 caracteres.<br />";
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

            String cpf = request.getParameter("cpf");
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
            if (cpf.equals("") || cpf == null || cpf.length() != 11
                    || ValidarCpfCnpj.valida_CpfCnpj(cpf) == false) {
                mensagem = mensagem + "Digite um CPF válido, ele deve conter 11 caracteres.<br />";
                retorno = true;
            } else {
                funcionario.setCpf(cpf);
            }

            String senha = request.getParameter("senha");
            if (senha.equals("") || senha == null || senha.length() < 6) {
                mensagem = mensagem + "O campo senha, deve conter no mínino 6 caracteres.<br />";
                retorno = true;
            } else {
                funcionario.getLoginFuncionario().setSenha(senha);
            }

            String valorNivelAcesso = request.getParameter("nivelAcesso");
            if (valorNivelAcesso == null || valorNivelAcesso.equals("")) {
                mensagem = mensagem + "Selecione o Nível de acesso para o cliente.<br />";
                retorno = true;
            } else {
                funcionario.getLoginFuncionario().getNivelDeAcesso().setCodigoNivelDeAcesso(Integer.parseInt(valorNivelAcesso));
            }

            String valorStatus = request.getParameter("statusUsuario");
            if (valorStatus == null || valorStatus.equals("")) {
                mensagem = mensagem + "Selecione o Status do funcinário.<br />";
                retorno = true;
            } else {
                funcionario.getLoginFuncionario().setStatus(Status.valueOf(Status.class, valorStatus));
            }

            /* Após passar por cada if acimo vamos fazer uma verificação se todos os dados vindo do form estão de
             * acordo. Se a variavel retorno for true então algo está errado e precisa ser corrigido
             * senão tudo está ok então daremos prosseguimento a gravação no bando de dados
             */

            /* envio as expression laguage do funcionario para página através dos request abaixo */
            request.setAttribute("funcionarioDataNascimento", Utilitario.DataFormatada(funcionario.getDataNascimento()));
            request.setAttribute("funcionarioSexo", funcionario.getSexo());
            request.setAttribute("funcionarioCargo", funcionario.getCargo());
            request.setAttribute("funcionarioDataAdmissao", Utilitario.DataFormatada(funcionario.getDataAdmissao()));
            request.setAttribute("funcionarioDataDemissao", Utilitario.DataFormatada(funcionario.getDataDemissao()));
            request.setAttribute("funcionarioEstado", funcionario.getEndereco().getEstado());
            request.setAttribute("funcionarioNivelAcesso", funcionario.getLoginFuncionario().getNivelDeAcesso().getCodigoNivelDeAcesso());
            //request.setAttribute("funcionarioDataAcesso", Utilitario.DataFormatada(funcionario.getLoginFuncionario().getUltimoAcesso()));
            request.setAttribute("funcionarioStatusUsuario", funcionario.getLoginFuncionario().getStatus());
            request.setAttribute("funcionario", funcionario);

            request.setAttribute("NiveisDeAcesso", acessoDAO.getNiveisDeAcesso());

            if (retorno) {

                request.setAttribute("mensagem", mensagem);


                /* caso passe por aqui retorno a página por usuario corrigir e enviar novamente */
                return "restrito/cadastroFuncionario.jsp";

            } else {
                if (funcionarioDAO.getFuncionario(cpf) == null) {
                    /* caso tudo esteja correto semanticamente então vou gravar no banco */
                    funcionarioDAO.salvar(funcionario);
                    /* envio mensagem de sucesso */
                    request.setAttribute("mensagem", "Funcionario (" + funcionario.getNomeCompleto() + ") gravado com sucesso!");
                    request.setAttribute("funcionario", funcionario);
                }else{
                    request.setAttribute("funcionario", funcionario);
                    request.setAttribute("erro", "Erro - CPF já cadastrado no sistema");
                    return "restrito/cadastroFuncionario.jsp";
                }
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
        return "SisCultbookController?cmd=consultarFuncionario";

    }
}
