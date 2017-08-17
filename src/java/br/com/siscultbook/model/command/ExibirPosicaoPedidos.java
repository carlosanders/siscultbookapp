/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.conexao.InterfacePool;
import br.com.siscultbook.model.dao.PedidoDAO;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Carlos
 */
public class ExibirPosicaoPedidos implements InterfaceCommand {

    private PedidoDAO pedidoDAO;
    private InterfacePool pool;

    public ExibirPosicaoPedidos(InterfacePool pool) {
        super();
        this.pool = pool;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("titulo", "Reltórios do Sistema");
        request.setAttribute("arquivo", "formPesquisaRelatorios.jsp");
        Connection con = pool.getConnection();
        byte[] bytes = null;

        try {

            String pathJasper = request.getAttribute("dirRelatorios") + "/totalPedidosPorStatus.jasper";
            String pathImagem = request.getAttribute("dirRelatorios") + "/logo.jpg";
            String path = request.getAttribute("dirServleContext") + "/WEB-INF/classes/br/com/siscultbook/relatorios/totalPedidosPorStatus.jasper";

            //passa os paramentros se houverem
            Map parametros = new HashMap();
            parametros.put("logo",pathImagem);

            System.out.println("logo" + pathImagem);

            JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(pathJasper);
            bytes = JasperRunManager.runReportToPdf(relatorioJasper, parametros, con);
            if (bytes != null && bytes.length > 0) {
                // envia o relatório em formato PDF para o browser
                response.setContentType("application/pdf");

                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
            }

        } catch (Exception e) {
            request.setAttribute("mensagem", "Problemas ao gerar relatorio" + e.getMessage());
            e.printStackTrace();
        }

        return "";

    }
}
