/*
 * classe que vai fazer o controle das regras de negocio e o destino das requisições http
 */
package br.com.siscultbook.model.helper;

import br.com.siscultbook.conexao.InterfacePool;
import br.com.siscultbook.model.command.AcessarUsuario;
import br.com.siscultbook.model.command.AdicionarItemNoCarrinho;
import br.com.siscultbook.model.command.AreaRestrita;
import br.com.siscultbook.model.command.AtualizarAcesso;
import br.com.siscultbook.model.command.AtualizarAutor;
import br.com.siscultbook.model.command.AtualizarCliente;
import br.com.siscultbook.model.command.AtualizarClientePainel;
import br.com.siscultbook.model.command.AtualizarEditora;
import br.com.siscultbook.model.command.AtualizarFuncionario;
import br.com.siscultbook.model.command.AtualizarFuncionarioPainel;
import br.com.siscultbook.model.command.AtualizarLivroImg;
import br.com.siscultbook.model.command.AtualizarNivelAcesso;
import br.com.siscultbook.model.command.AtualizarPedido;
import br.com.siscultbook.model.command.CadastrarAutor;
import br.com.siscultbook.model.command.CadastrarCliente;
import br.com.siscultbook.model.command.CadastrarClientePainel;
import br.com.siscultbook.model.command.CadastrarEditora;
import br.com.siscultbook.model.command.CadastrarFuncionario;
import br.com.siscultbook.model.command.CadastrarLivroImg;
import br.com.siscultbook.model.command.CadastrarNivelAcesso;
import br.com.siscultbook.model.command.CalcularCep;
import br.com.siscultbook.model.command.ConsultarAutor;
import br.com.siscultbook.model.command.ConsultarCatalogo;
import br.com.siscultbook.model.command.ConsultarCliente;
import br.com.siscultbook.model.command.ConsultarEditora;
import br.com.siscultbook.model.command.ConsultarFuncionario;
import br.com.siscultbook.model.command.ConsultarItensDoCarrinho;
import br.com.siscultbook.model.command.ConsultarItensDoPedido;
import br.com.siscultbook.model.command.ConsultarItensDoPedidoDoCliente;
import br.com.siscultbook.model.command.ConsultarLivro;
import br.com.siscultbook.model.command.ConsultarLivrosPorAutor;
import br.com.siscultbook.model.command.ConsultarLvirosPorAssunto;
import br.com.siscultbook.model.command.ConsultarLvirosPorEditora;
import br.com.siscultbook.model.command.ConsultarLvirosPorISBN;
import br.com.siscultbook.model.command.ConsultarMeusPedidos;
import br.com.siscultbook.model.command.ConsultarNivelAcesso;
import br.com.siscultbook.model.command.ConsultarTodosPedidos;
import br.com.siscultbook.model.command.DetalhesProduto;
import br.com.siscultbook.model.command.EditarAcesso;
import br.com.siscultbook.model.command.EditarAutor;
import br.com.siscultbook.model.command.EditarCliente;
import br.com.siscultbook.model.command.EditarEditora;
import br.com.siscultbook.model.command.EditarFuncionario;
import br.com.siscultbook.model.command.EditarLivro;
import br.com.siscultbook.model.command.EditarNivelAcesso;
import br.com.siscultbook.model.command.EditarPedido;
import br.com.siscultbook.model.command.ExcluirArquivoImg;
import br.com.siscultbook.model.command.ExcluirAutor;
import br.com.siscultbook.model.command.ExcluirCliente;
import br.com.siscultbook.model.command.ExcluirEditora;
import br.com.siscultbook.model.command.ExcluirFuncionario;
import br.com.siscultbook.model.command.ExcluirLivro;
import br.com.siscultbook.model.command.ExcluirNivelAcesso;
import br.com.siscultbook.model.command.ExibirLivrosPedidos;
import br.com.siscultbook.model.command.ExibirPosicaoPedidos;
import br.com.siscultbook.model.command.ExibirRelatorioLivrosPorAssunto;
import br.com.siscultbook.model.command.ExibrRelatorios;
import br.com.siscultbook.model.command.ExibrRelatoriosEntreDatas;
import br.com.siscultbook.model.command.FinalizarPedido;
import br.com.siscultbook.model.command.GravarPedido;
import br.com.siscultbook.model.command.IdentificarUsuarioNoCarrinho;
import br.com.siscultbook.model.command.IniciarSisCultook;
import br.com.siscultbook.model.command.InterfaceCommand;
import br.com.siscultbook.model.command.LivrosPaginaPrincipal;
import br.com.siscultbook.model.command.PainelCliente;
import br.com.siscultbook.model.command.RemoverSessao;
import br.com.siscultbook.model.command.VisualizarArquivos;
import br.com.siscultbook.model.dao.AutorDAO;
import br.com.siscultbook.model.dao.ClienteDAO;
import br.com.siscultbook.model.dao.EditoraDAO;
import br.com.siscultbook.model.dao.EntregaDAO;
import br.com.siscultbook.model.dao.FuncionarioDAO;
import br.com.siscultbook.model.dao.LivroDAO;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import br.com.siscultbook.model.dao.PagamentoDAO;
import br.com.siscultbook.model.dao.PedidoDAO;
import br.com.siscultbook.model.dao.ProdutoDAO;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;


/*
 * A função do objeto que for instaciado a partir dessa classe é o seguinte:
 * ele recebe a requisição e a partir da requisição ele vai retornar um
 * comando a ser executado.
 * Quem chama essa classe vai ser o ServletController da Aplicação.
 */
public class SisCultbookHelper {

    private HashMap<String, InterfaceCommand> mapaComandos;
    private HttpServletRequest request;
    private InterfacePool pool;

    public SisCultbookHelper(InterfacePool pool) {
        this.pool = pool;
        this.request = request;

        mapaComandos = new HashMap<String, InterfaceCommand>();
        mapaComandos.put("cadastrarCliente", new CadastrarCliente(new ClienteDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("consultarCliente", new ConsultarCliente(new ClienteDAO(pool)));
        mapaComandos.put("atualizarCliente", new AtualizarCliente(new ClienteDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("editarCliente", new EditarCliente(new ClienteDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("excluirCliente", new ExcluirCliente(new ClienteDAO(pool)));
        //comando para o funcionario
        mapaComandos.put("cadastrarFuncionario", new CadastrarFuncionario(new FuncionarioDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("consultarFuncionario", new ConsultarFuncionario(new FuncionarioDAO(pool)));
        mapaComandos.put("atualizarFuncionario", new AtualizarFuncionario(new FuncionarioDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("editarFuncionario", new EditarFuncionario(new FuncionarioDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("excluirFuncionario", new ExcluirFuncionario(new FuncionarioDAO(pool)));
        //comando para a Editora
        mapaComandos.put("cadastrarEditora", new CadastrarEditora(new EditoraDAO(pool)));
        mapaComandos.put("consultarEditora", new ConsultarEditora(new EditoraDAO(pool)));
        mapaComandos.put("atualizarEditora", new AtualizarEditora(new EditoraDAO(pool)));
        mapaComandos.put("editarEditora", new EditarEditora(new EditoraDAO(pool)));
        mapaComandos.put("excluirEditora", new ExcluirEditora(new EditoraDAO(pool)));
        //comando para a Editora
        mapaComandos.put("cadastrarAutor", new CadastrarAutor(new AutorDAO(pool)));
        mapaComandos.put("consultarAutor", new ConsultarAutor(new AutorDAO(pool)));
        mapaComandos.put("atualizarAutor", new AtualizarAutor(new AutorDAO(pool)));
        mapaComandos.put("editarAutor", new EditarAutor(new AutorDAO(pool)));
        mapaComandos.put("excluirAutor", new ExcluirAutor(new AutorDAO(pool)));
        //comadno para o Livro
        mapaComandos.put("editarLivro", new EditarLivro(new AutorDAO(pool), new EditoraDAO(pool), new LivroDAO(pool)));
        mapaComandos.put("excluirArquivoImg", new ExcluirArquivoImg(new AutorDAO(pool), new EditoraDAO(pool), new LivroDAO(pool)));
        mapaComandos.put("cadastrarLivro", new CadastrarLivroImg(new LivroDAO(pool), new ProdutoDAO(pool), new AutorDAO(pool), new EditoraDAO(pool)));
        mapaComandos.put("atualizarLivro", new AtualizarLivroImg(new LivroDAO(pool), new ProdutoDAO(pool), new AutorDAO(pool), new EditoraDAO(pool)));
        mapaComandos.put("consultarLivro", new ConsultarLivro(new LivroDAO(pool)));
        mapaComandos.put("excluirLivro", new ExcluirLivro(new LivroDAO(pool), new ProdutoDAO(pool)));
        mapaComandos.put("detalhesProduto", new DetalhesProduto(new LivroDAO(pool)));
        mapaComandos.put("consultarLivrosPorAutor", new ConsultarLivrosPorAutor(new LivroDAO(pool), new AutorDAO(pool)));
        mapaComandos.put("consultarLivrosPorEditora", new ConsultarLvirosPorEditora(new LivroDAO(pool), new EditoraDAO(pool)));
        mapaComandos.put("consultarLivrosPorAssunto", new ConsultarLvirosPorAssunto(new LivroDAO(pool)));
        mapaComandos.put("consultarLivrosTipo", new ConsultarLvirosPorISBN(new LivroDAO(pool)));

        //comando para o carrinho de compras
        mapaComandos.put("consultarCatalogo", new ConsultarCatalogo(new LivroDAO(pool)));
        mapaComandos.put("adicionarCarrinho", new AdicionarItemNoCarrinho(new LivroDAO(pool)));
        mapaComandos.put("atualizarCarrinho", new AdicionarItemNoCarrinho(new LivroDAO(pool)));
        mapaComandos.put("removerItemCarrinho", new AdicionarItemNoCarrinho(new LivroDAO(pool)));
        mapaComandos.put("consultarItensCarrinho", new ConsultarItensDoCarrinho());
        mapaComandos.put("calcularCep", new CalcularCep());
        //comando para usuario
        mapaComandos.put("acessarUsuario", new AcessarUsuario(new FuncionarioDAO(pool), new ClienteDAO(pool)));
        //comando para acessos
        mapaComandos.put("autorizarAcesso", new EditarAcesso(new FuncionarioDAO(pool), new ClienteDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("atualizarAcesso", new AtualizarAcesso(new NivelDeAcessoDAO(pool)));
        //comando para o niveldeacesso
        mapaComandos.put("editarNivelAcesso", new EditarNivelAcesso(new NivelDeAcessoDAO(pool)));
        mapaComandos.put("cadastrarNivelAcesso", new CadastrarNivelAcesso(new NivelDeAcessoDAO(pool)));
        mapaComandos.put("atualizarNivelAcesso", new AtualizarNivelAcesso(new NivelDeAcessoDAO(pool)));
        mapaComandos.put("consultarNivelAcesso", new ConsultarNivelAcesso(new NivelDeAcessoDAO(pool)));
        mapaComandos.put("excluirNivelAcesso", new ExcluirNivelAcesso(new NivelDeAcessoDAO(pool)));
        //diversos
        mapaComandos.put("iniciarSiscultbook", new IniciarSisCultook(new LivroDAO(pool)));
        mapaComandos.put("paginaPrincipal", new LivrosPaginaPrincipal(new LivroDAO(pool)));
        mapaComandos.put("areaRestrita", new AreaRestrita());
        mapaComandos.put("finalizarCompra", new FinalizarPedido());
        mapaComandos.put("sairSistema", new RemoverSessao(new ClienteDAO(pool), new FuncionarioDAO(pool)));
        mapaComandos.put("arquivosEstaticos", new VisualizarArquivos(new AutorDAO(pool), new EditoraDAO(pool)));
        mapaComandos.put("identificarUsuario", new IdentificarUsuarioNoCarrinho(new ClienteDAO(pool)));
        //comandos ref. ao pedido
        mapaComandos.put("gravarPedido", new GravarPedido(new PedidoDAO(pool), new EntregaDAO(pool), new PagamentoDAO(pool)));
        mapaComandos.put("atualizarPedido", new AtualizarPedido(new PedidoDAO(pool), new EntregaDAO(pool), new PagamentoDAO(pool)));
        mapaComandos.put("consultarMeusPedidos", new ConsultarMeusPedidos(new PedidoDAO(pool)));
        mapaComandos.put("consultarTodosPedidos", new ConsultarTodosPedidos(new PedidoDAO(pool)));
        mapaComandos.put("exibirItensPedido", new ConsultarItensDoPedido(new PedidoDAO(pool), new PagamentoDAO(pool), new EntregaDAO(pool)));
        mapaComandos.put("exibirItensPCliente", new ConsultarItensDoPedidoDoCliente(new PedidoDAO(pool), new PagamentoDAO(pool), new EntregaDAO(pool), new ClienteDAO(pool)));
        mapaComandos.put("exibirPedido", new EditarPedido(new PedidoDAO(pool), new PagamentoDAO(pool), new EntregaDAO(pool)));
        //comandos ref. ao cliente
        mapaComandos.put("dadosCliente", new PainelCliente(new ClienteDAO(pool), new FuncionarioDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("cadastrarClientePainel", new CadastrarClientePainel(new ClienteDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("atualizarClientePainel", new AtualizarClientePainel(new ClienteDAO(pool), new NivelDeAcessoDAO(pool)));
        mapaComandos.put("atualizarFuncionarioPainel", new AtualizarFuncionarioPainel(new FuncionarioDAO(pool)));
        //comandos ref. aos relatórios do sistema
        mapaComandos.put("consultarRelatorios", new ExibrRelatorios());
        mapaComandos.put("exibirPedidosEntreAsDatas", new ExibrRelatoriosEntreDatas(new PedidoDAO(pool)));
        mapaComandos.put("exibirPosicaoPedidos", new ExibirPosicaoPedidos(pool));
        mapaComandos.put("exibirPosicaoLivros", new ExibirRelatorioLivrosPorAssunto(pool));
        mapaComandos.put("exibirLivrosDosPedidos", new ExibirLivrosPedidos(pool));

    }

    public InterfaceCommand getCommand() {

        String cmd = request.getParameter("cmd");
        if (cmd == null) {
        return mapaComandos.get("iniciarSiscultbook");
        }
        return mapaComandos.get(cmd);
        /*
        String cmd = request.getParameter("cmd");
        if (cmd == null || request.getSession().getAttribute("usuario") == null) {
            return mapaComandos.get("acessarUsuario");
        }
        return mapaComandos.get(cmd);*/
        /*
        String cmd = request.getParameter("cmd");
        return mapaComandos.get(cmd);
         */
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
