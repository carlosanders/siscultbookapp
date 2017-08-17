package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.EstadoCivil;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.NivelDeAcesso;
import br.com.siscultbook.bean.Sexo;
import br.com.siscultbook.bean.Status;
import br.com.siscultbook.model.dao.InterfaceClienteDAO;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import br.com.siscultbook.util.Utilitario;
import br.com.siscultbook.util.ValidarCpfCnpj;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author com1dn-711
 */
public class CadastrarClientePainel implements InterfaceCommand {

    private InterfaceClienteDAO clienteDAO;
    private NivelDeAcessoDAO acessoDAO;

    public CadastrarClientePainel(InterfaceClienteDAO clienteDAO, NivelDeAcessoDAO acessoDAO) {
        super();
        this.clienteDAO = clienteDAO;
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cliente cliente = new Cliente();
        /*Expressão regular para validar data*/
        Pattern pData = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
        Pattern pTelefone = Pattern.compile("^[0-9]{2}[0-9]{3,4}[0-9]{4}$");
        Pattern pEmail = Pattern.compile("^[0-9a-z_-]+(.[0-9a-z_-]+)*@[a-z0-9-]+(.[a-z0-9-]+)+$");
        /* adciono um título para a página */
        request.setAttribute("titulo", "Cadastro - Cliente");

        boolean retorno = false;
        String mensagem = "";

        try {

            String nomeCompleto = request.getParameter("nomeCompleto");
            if (nomeCompleto.equals("") || nomeCompleto == null || nomeCompleto.length() < 3) {
                mensagem = mensagem + "Digite seu nome completo, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            } else {
                cliente.setNomeCompleto(nomeCompleto);
            }

            String cep = request.getParameter("cep");
            cep = cep.replace("-", "");
            if (cep.equals("") || cep == null || cep.length() != 8) {
                mensagem = mensagem + "Digite um CEP, ele deve conter 8 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setCep(cep);
            }

            String dataNascimento = request.getParameter("dataNascimento");
            Matcher mDataNascimento = pData.matcher(dataNascimento);
            if (dataNascimento.equals("") || dataNascimento == null || !mDataNascimento.find()
                    || Utilitario.verificaData(dataNascimento) == null) {
                mensagem = mensagem + "Digite uma data válida, para o campo nascimento.<br />";
                retorno = true;
            } else {
                cliente.setDataNascimento(Utilitario.verificaData(dataNascimento));
            }

            String sexo = request.getParameter("sexo");
            if (sexo == null || sexo.equals("")) {
                mensagem = mensagem + "O campo sexo não foi selecionado.<br />";
                retorno = true;
            } else {
                cliente.setSexo(Sexo.valueOf(Sexo.class, sexo));
            }

            String telefoneContato = request.getParameter("telefoneContato");
            telefoneContato = telefoneContato.replace("(", "");
            telefoneContato = telefoneContato.replace(")", "");
            telefoneContato = telefoneContato.replace(" ", "");
            telefoneContato = telefoneContato.replace("-", "");
            System.out.println("debuge Telefone replace - " + telefoneContato);
            Matcher m = pTelefone.matcher(telefoneContato);
            if (telefoneContato.equals("") || telefoneContato == null || !m.find()) {
                mensagem = mensagem + "O telefone de contato, deve conter apenas números.<br />";
                retorno = true;
            } else {
                cliente.setTelefone(telefoneContato);
            }

            String emailContato = request.getParameter("emailContato");
            Matcher mEmail = pEmail.matcher(emailContato);
            if (emailContato.equals("") || emailContato == null
                    || !mEmail.find()) {
                mensagem = mensagem + "Email, inválido.<br />";
                retorno = true;
            } else {
                cliente.setEmail(emailContato);
            }

            String rua = request.getParameter("rua");
            if (rua.equals("") || rua == null || rua.length() < 3) {
                mensagem = mensagem + "O campo rua deve ser maior que 3 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setRua(rua);
            }

            String bairro = request.getParameter("bairro");
            if (bairro.equals("") || bairro == null || bairro.length() < 3) {
                mensagem = mensagem + "O campo bairro deve ser maior que 3 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setBairro(bairro);
            }

            String cidade = request.getParameter("cidade");
            if (cidade.equals("") || cidade == null || cidade.length() < 3) {
                mensagem = mensagem + "O campo cidade deve ser maior que 3 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setCidade(cidade);
            }

            String valorEstado = request.getParameter("estado");
            if (valorEstado == null || valorEstado.equals("")) {
                mensagem = mensagem + "Selecione o Estado civel do cliente.<br />";
                retorno = true;
            } else {
                cliente.getEndereco().setEstado(Estados.valueOf(Estados.class, valorEstado));
            }

            String valorEstadoCivil = request.getParameter("estadoCivil");
            if (valorEstadoCivil == null || valorEstadoCivil.equals("")) {
                mensagem = mensagem + "Selecione o Estado civel do cliente.<br />";
                retorno = true;
            } else {
                cliente.setEstadoCivil(EstadoCivil.valueOf(EstadoCivil.class, valorEstadoCivil));
            }

            String cpf = request.getParameter("cpf");
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
            if (cpf.equals("") || cpf == null || cpf.length() != 11
                    || ValidarCpfCnpj.valida_CpfCnpj(cpf) == false || cpf.equals("01234567890")) {
                mensagem = mensagem + "Digite um CPF, ele deve conter 11 caracteres.<br />";
                retorno = true;
            } else {
                cliente.setCpf(cpf);
            }

            String senha = request.getParameter("senha");
            if (senha.equals("") || senha == null || senha.length() < 6) {
                mensagem = mensagem + "O campo senha, deve conter no mínino 6 caracteres.<br />";
                retorno = true;
            } else {
                cliente.getLoginCliente().setSenha(senha);
            }

            List<NivelDeAcesso> acessos = (List<NivelDeAcesso>) acessoDAO.getNiveisDeAcesso();
            for (NivelDeAcesso niveis : acessos) {
                if (niveis.getNivelDeAcesso().equals("Cliente")) {
                    cliente.getLoginCliente().getNivelDeAcesso().setCodigoNivelDeAcesso(niveis.getCodigoNivelDeAcesso());
                }
            }


            cliente.getLoginCliente().setStatus(Status.Ativo);
            GregorianCalendar data = new GregorianCalendar();
            data.setTime(new Date());
            cliente.setDesde(data);
            /* Após passar por cada if acimo vamos fazer uma verificação se todos os dados vindo do form estão de
             * acordo. Se a variavel retorno for true então algo está errado e precisa ser corrigido
             * senão tudo está ok então daremos prosseguimento a gravação no bando de dados
             */
            //System.out.println("CadastrarClientePainel - " + retorno);
            if (retorno) {

                request.setAttribute("mensagem", mensagem);
                request.setAttribute("clienteDataNascimento", Utilitario.DataFormatada(cliente.getDataNascimento()));
                request.setAttribute("clienteDataCadastro", Utilitario.DataFormatada(cliente.getDesde()));
                request.setAttribute("clienteEstadoCivil", cliente.getEstadoCivil());
                request.setAttribute("clienteNivelAcesso", cliente.getLoginCliente().getNivelDeAcesso());
                request.setAttribute("clienteStatusUsuario", cliente.getLoginCliente().getStatus());
                request.setAttribute("clienteSexo", cliente.getSexo());
                request.setAttribute("clienteEstado", cliente.getEndereco().getEstado());
                request.setAttribute("erro", "Dados Incorretos");
                request.setAttribute("cliente", cliente);

                /* caso passe por aqui retorno a página por usuario corrigir e enviar novamente */
                request.setAttribute("titulo", "Cadastro - Cliente");
                request.setAttribute("arquivo", "formCadastroCliente.jsp");
                return "dadosCliente.jsp";

            } else {

                if (clienteDAO.buscarClientePorCPF(cpf) == null) {
                    /* caso tudo esteja correto semanticamente então vou gravar no banco */
                    clienteDAO.salvar(cliente);
                    //request.setAttribute("NiveisDeAcesso", acessoDAO.getNiveisDeAcesso());

                    /* envio mensagem de sucesso do cadastro do funcionario para página através dos request abaixo */
                    request.setAttribute("mensagem", "Cliente (" + cliente.getNomeCompleto() + ") gravado com sucesso!");
                    request.setAttribute("clienteDataNascimento", Utilitario.DataFormatada(cliente.getDataNascimento()));
                    request.setAttribute("clienteDataCadastro", Utilitario.DataFormatada(cliente.getDesde()));
                    request.setAttribute("clienteEstadoCivil", cliente.getEstadoCivil());
                    request.setAttribute("clienteNivelAcesso", cliente.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso());
                    request.setAttribute("clienteStatusUsuario", cliente.getLoginCliente().getStatus());
                    request.setAttribute("clienteSexo", cliente.getSexo());
                    request.setAttribute("clienteEstado", cliente.getEndereco().getEstado());
                    request.setAttribute("cliente", cliente);

                } else {

                    //request.setAttribute("NiveisDeAcesso", acessoDAO.getNiveisDeAcesso());

                    request.setAttribute("mensagem", "Cliente (" + cliente.getNomeCompleto() + ").");
                    request.setAttribute("clienteDataNascimento", Utilitario.DataFormatada(cliente.getDataNascimento()));
                    request.setAttribute("clienteDataCadastro", Utilitario.DataFormatada(cliente.getDesde()));
                    request.setAttribute("clienteEstadoCivil", cliente.getEstadoCivil());
                    request.setAttribute("clienteNivelAcesso", cliente.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso());
                    request.setAttribute("clienteStatusUsuario", cliente.getLoginCliente().getStatus());
                    request.setAttribute("clienteSexo", cliente.getSexo());
                    request.setAttribute("clienteEstado", cliente.getEndereco().getEstado());
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("erro", "Erro - CPF já cadastrado no sistema");

                    request.setAttribute("titulo", "Cadastro - Cliente");
                    request.setAttribute("arquivo", "formCadastroCliente.jsp");
                    return "dadosCliente.jsp";
                }

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

        return "SisCultbookController?cmd=paginaPrincipal";
    }
}
