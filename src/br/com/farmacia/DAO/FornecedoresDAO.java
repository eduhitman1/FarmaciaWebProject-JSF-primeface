package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {

	public void salva(Fornecedores f) throws SQLException {
		Connection conexao = ConexaoFactory.conectar();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into fornecedores (descricao) values (?)");
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}

	public void excluir(Fornecedores f) throws SQLException{
		Connection conexao = ConexaoFactory.conectar();
		StringBuilder sql = new StringBuilder();
		sql.append("delete from fornecedores where codigo =?");
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar(Fornecedores f) throws SQLException{
		Connection conexao = ConexaoFactory.conectar();
		StringBuilder sql = new StringBuilder();
		sql.append("update fornecedores set descricao=? where codigo = ?");
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
	}
	
	public Fornecedores buscaPorCodigo(Fornecedores f)throws SQLException {
		Connection conexao = ConexaoFactory.conectar();
		StringBuilder sql = new StringBuilder();
		sql.append("select codigo, descricao from fornecedores where codigo = ?");
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, f.getCodigo());
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		if(resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}
	
    public  ArrayList<Fornecedores>buscarPorDescricao(Fornecedores f)throws SQLException{
    	Connection conexao = ConexaoFactory.conectar();
		StringBuilder sql = new StringBuilder();
		sql.append("select codigo, descricao from fornecedores where descricao LIKE ? order by descricao asc");
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
        
		comando.setString(1, "%" + f.getDescricao() + "%" );
		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		while(resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			lista.add(item);
		}
		return lista;
    }
	
	public ArrayList<Fornecedores> listar() throws SQLException{
		Connection conexao = ConexaoFactory.conectar();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from fornecedores order by codigo asc");
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		while(resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			lista.add(f);
		}
		return lista;
	}
	
	public static void main(String[] args) {
		//INSERT
		/*Fornecedores f1 = new Fornecedores();
		f1.setDescricao("Gustavo matias");
        Fornecedores f2 = new Fornecedores();
		f2.setDescricao("Cladia rodriques");
        FornecedoresDAO fDao = new FornecedoresDAO();
		try {
			fDao.salva(f1);
		//	fDao.salva(f2);
			System.out.println("Salvo com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao salvar");
			e.printStackTrace();
		}*/ 

		//DELETE
		/*Fornecedores f1 = new Fornecedores();
		f1.setCodigo(2);
		FornecedoresDAO fDao = new FornecedoresDAO();
		try {
			fDao.excluir(f1);
		    System.out.println("excluirdo com sucesso");
		} catch (SQLException e) {
			System.out.println("erro ao excluir");
			e.printStackTrace();
		}   */
		
		//UPDATE
		/*Fornecedores f1 = new Fornecedores();
		f1.setCodigo(1);
		f1.setDescricao("Henrique");
		FornecedoresDAO fDao = new FornecedoresDAO();
		try {
			fDao.editar(f1);
		    System.out.println("Editado com sucesso");
		} catch (SQLException e) {
			System.out.println("erro ao editar");
			e.printStackTrace();
		} */
		
		
		//BUSCA POR CODIGO
		/*Fornecedores f1 = new Fornecedores();
		f1.setCodigo(1);
        Fornecedores f2 = new Fornecedores();
		f2.setCodigo(5);
        FornecedoresDAO fDao = new FornecedoresDAO();
		try {
			Fornecedores f3 = fDao.buscaPorCodigo(f1);
			Fornecedores f4 = fDao.buscaPorCodigo(f2);
			System.out.println("Resultado 1: "+f3);
			System.out.println("Resultado 2: "+f4);
		} catch (SQLException e) {
			System.out.println("Erro na busca");
			e.printStackTrace();
		} */
		
		// LISTA TODOS
		/*FornecedoresDAO fDao = new FornecedoresDAO();
		try {
		  ArrayList<Fornecedores>lista = fDao.lista();
		  for(Fornecedores f: lista) {
		  System.out.println("Resultado "+f);
		  }
		  } catch (SQLException e) {
			System.out.println("Erro na busca");
			e.printStackTrace();
		}*/  
		
		//LISTA POR DESCRICAO ESPECIFIC
		/*FornecedoresDAO fDao = new FornecedoresDAO();
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("J");
		try {
		
		  ArrayList<Fornecedores>lista = fDao.buscarPorDescricao(f1);
		  for(Fornecedores f: lista) {
		  System.out.println("Resultado "+f);
		  }
		  } catch (SQLException e) {
			System.out.println("Erro na busca");
			e.printStackTrace();
		}  */
		
	}

}
