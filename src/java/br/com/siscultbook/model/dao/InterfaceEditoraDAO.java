package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Editora;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface InterfaceEditoraDAO {

    public void excluir(Integer codigo) throws SQLException;

    public void salvar(Editora editora) throws SQLException;

    public void atualizar(Editora editora) throws SQLException;

    public Editora getEditora(Integer codigo) throws SQLException;

    public Editora buscarEditoraPorCNPJ(String cnpj) throws SQLException;

    public List<Editora> getEditoras() throws SQLException;
    public List<Editora> getEditorasAtivas() throws SQLException;
}
