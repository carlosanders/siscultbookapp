package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.EstadoCivil;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.Sexo;
import br.com.siscultbook.bean.Status;
import br.com.siscultbook.model.dao.InterfaceClienteDAO;
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
public class AtualizarCliente implements InterfaceCommand {

    private InterfaceClienteDAO clienteDAO;
    private NivelDeAcessoDAO acessoDAO;

    public AtualizarCliente(InterfaceClienteDAO clienteDAO, NivelDeAcessoDAO acessoDAO) {
        super();
        this.clienteDAO = clienteDAO;
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Cliente cliente = new Cliente();
        String cpf = null;
        String nomeCompleto = null;
        String dataNascimento = null;
        String telefoneContato = null;
        String emailContato = null;
        String rua = null;
        String bairro = null;
        String cidade = null;
        String senha = null;
        String desde = null;
        String cep = null;

        boolean retorno = false;
        String mensagem = "";

        try {

            cpf = request.getParameter("cpf");
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
            if (cpf.equals("") || cpf == null || cpf.length() != 11 
                    || ValidarCpfCnpj.valida_CpfCnpj(cpf) == false || cpf.equals("01234567890")) {
                mensagem = mensagem + "Digite um CPF, ele deve conter 11 caracteres.<br />";
                retorno = true;
            } else {
                cliente.setCpf(cpf);
            }

            cep = request.getParameter("cep");
            cep = cep.replace("-", "");
            if (cep.equals("") || cep == null || cep.length() != 8) {
                mensagem = mensagem + "Digite um CEP, ele deve conter 8 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setCep(cep);
            }

            nomeCompleto = request.getParameter("nomeCompleto");
            if (nomeCompleto.equals("") || nomeCompleto == null || nomeCompleto.length() < 3) {
                mensagem = mensagem + "Digite seu nome completo, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            } else {
                cliente.setNomeCompleto(nomeCompleto);
            }

            dataNascimento = request.getParameter("dataNascimento");
            if (Utilitario.verificaData(dataNascimento) == null) {
                mensagem = mensagem + "Digite uma data correta, para o campo nascimento.<br />";
                retorno = true;
            } else {
                cliente.setDataNascimento(Utilitario.verificaData(dataNascimento));
            }

            try {

                telefoneContato = request.getParameter("telefoneContato");
                telefoneContato = telefoneContato.replace("(", "");
                telefoneContato = telefoneContato.replace(")", "");
                telefoneContato = telefoneContato.replace(" ", "");
                telefoneContato = telefoneContato.replace("-", "");
                Pattern p = Pattern.compile("^[0-9]{2}[0-9]{3,4}[0-9]{4}$");
                Matcher m = p.matcher(telefoneContato);
                //telefoneContato = Long.parseLong(request.getParameter("telefoneContato"));
                if (telefoneContato.equals("") || telefoneContato == null || !m.find()) {
                    mensagem = mensagem + "O telefone de contato, deve conter apenas números.<br />";
                    retorno = true;
                } else {
                    cliente.setTelefone(telefoneContato);
                }
            } catch (Exception e) {
                mensagem = mensagem + "Valor inválido no telefone de contato.<br />";
                retorno = true;
            }

            emailContato = request.getParameter("emailContato");
            if (emailContato.equals("") || emailContato == null || emailContato.contentEquals("@")) {
                mensagem = mensagem + "Email, inválido.<br />";
                retorno = true;
            } else {
                cliente.setEmail(emailContato);
            }

            rua = request.getParameter("rua");
            if (rua.equals("") || rua == null || rua.length() < 3) {
                mensagem = mensagem + "O campo rua deve ser maior que 3 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setRua(rua);
            }

            bairro = request.getParameter("bairro");
            if (bairro.equals("") || bairro == null || bairro.length() < 3) {
                mensagem = mensagem + "O campo bairro deve ser maior que 3 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setBairro(bairro);
            }

            cidade = request.getParameter("cidade");
            if (cidade.equals("") || cidade == null || cidade.length() < 3) {
                mensagem = mensagem + "O campo cidade deve ser maior que 3 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setCidade(cidade);
            }

            senha = request.getParameter("senha");
            if (senha.equals("") || senha == null || senha.length() < 6) {
                mensagem = mensagem + "O campo senha, deve conter no mínino 6 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getLoginCliente().setSenha(senha);
            }

            desde = request.getParameter("desde");
            if (Utilitario.verificaData(desde) == null) {
                mensagem = mensagem + "Digite uma data correta, para o campo Data de cadastro.<br />";
                retorno = true;
            } else {
                cliente.setDesde(Utilitario.verificaData(desde));
            }
            /*
            dataUltimoAcesso = request.getParameter("dataUltimoAcesso");
            if (Utilitario.verificaData(dataUltimoAcesso) == null) {
            mensagem = mensagem + "Digite uma data correta, para o campo Último acesso.<br />";
            retorno = true;
            }
             */
            String sexo = request.getParameter("sexo");
            if (sexo == null || sexo.equals("")) {
                System.out.println("debug carlos - Passou aqui, dentro do if sexo");
                mensagem = mensagem + "O campo sexo não foi selecionado.<br />";
                retorno = true;
            } else {
                cliente.setSexo(Sexo.valueOf(Sexo.class, sexo));
            }

            //situações especiais
            String valorEstadoCivil = request.getParameter("estadoCivil");
            if (valorEstadoCivil == null || valorEstadoCivil.equals("")) {
                System.out.println("debug carlos - Passou aqui, dentro do if valorEstadoCivel");
                mensagem = mensagem + "Selecione o Estado civel do cliente.<br />";
                retorno = true;
            } else {
                cliente.setEstadoCivil(EstadoCivil.valueOf(EstadoCivil.class, valorEstadoCivil));
            }

            String valorEstado = request.getParameter("estado");
            if (valorEstado == null || valorEstado.equals("")) {
                //System.out.println("debug carlos - Passou aqui, dentro do if valorEstado");
                mensagem = mensagem + "Selecione o Estado do cliente.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setEstado(Estados.valueOf(Estados.class, valorEstado));
            }

            //situações especiais
            String valorNivelAcesso = request.getParameter("nivelAcesso");
            if (valorNivelAcesso == null || valorNivelAcesso.equals("")) {
                //System.out.println("debug carlos - Passou aqui, dentro do if valorNivelAcesso");
                mensagem = mensagem + "Selecione o Nível de acesso para o cliente.<br />";
                retorno = true;
            } else {
                cliente.getLoginCliente().getNivelDeAcesso().setCodigoNivelDeAcesso(Integer.parseInt(valorNivelAcesso));
            }

            //situações especiais
            String valorStatus = request.getParameter("statusUsuario");
            if (valorStatus == null || valorStatus.equals("")) {
                //System.out.println("debug carlos - Passou aqui, dentro do if valorStatus");
                mensagem = mensagem + "Selecione o Status do cliente.<br />";
                retorno = true;
            } else {
                cliente.getLoginCliente().setStatus(Status.valueOf(Status.class, valorStatus));
            }

            //setando dados do cliente herdando da Classe Pessoa.java para a classe Cliente.java
            cliente.setCodigoCliente(Integer.parseInt(request.getParameter("codigo")));
            request.setAttribute("NiveisDeAcesso", acessoDAO.getNiveisDeAcesso());

            if (retorno) {

                request.setAttribute("mensagem", mensagem);
                System.out.println("debug carlos - Passou aqui, no if de retorno ");
                request.setAttribute("clienteDataNascimento", Utilitario.DataFormatada(cliente.getDataNascimento()));
                request.setAttribute("clienteDataCadastro", Utilitario.DataFormatada(cliente.getDesde()));
                //request.setAttribute("clienteDataAcesso", Utilitario.DataFormatada(cliente.getLoginCliente().getUltimoAcesso()));

                request.setAttribute("clienteEstadoCivil", cliente.getEstadoCivil());
                request.setAttribute("clienteNivelAcesso", cliente.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso());
                request.setAttribute("clienteStatusUsuario", cliente.getLoginCliente().getStatus());
                request.setAttribute("clienteSexo", cliente.getSexo());
                request.setAttribute("clienteEstado", cliente.getEndereco().getEstado());
                request.setAttribute("erro", "Dados Incorretos");
                request.setAttribute("cliente", cliente);

                return "restrito/atualizaCliente.jsp";

            } else {
                clienteDAO.atualizar(cliente);
                request.setAttribute("mensagem", "Cliente (" + cliente.getNomeCompleto() + ") atualizado com sucesso!");
            }



        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com a atualização: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("titulo", "Ataulização - Cliente");
        request.setAttribute("cliente", cliente);
        return "SisCultbookController?cmd=consultarCliente";
    }
}
