package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutoDAO {

	public void salvar(Produtos p) throws SQLException {
		Connection conexao = ConexaoFactory.conectar();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into produtos (descricao_prod,quantidade,preco,fornecedores_codigo) values (?,?,?,?)");
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setLong(2,p.getQuantidade());
		comando.setDouble(3,p.getPreco());
		comando.setLong(4,p.getFornecedores().getCodigo()); // CHAVE ESTRAGEITA
		comando.executeUpdate();
	}
	
	public void excluir(Produtos p) throws SQLException{
		Connection conexao = ConexaoFactory.conectar();
		StringBuilder sql = new StringBuilder();
		sql.append("delete from produtos where codigo_prod =?");
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, p.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar(Produtos p) throws SQLException{
		Connection conexao = ConexaoFactory.conectar();
		StringBuilder sql = new StringBuilder();
		sql.append("update produtos set descricao_prod=?, quantidade=?, preco=?, fornecedores_codigo=? where codigo_prod = ?");
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, p.getDescricao());
		comando.setLong(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.setLong(5, p.getCodigo());
		comando.executeUpdate();
	}
	
	
	
	
	// LISTAGEM PRODUTOS  // FICA ATENTO  
	public ArrayList<Produtos> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();               //FORNECEDOR(codigo, descricao)
	 	sql.append("SELECT codigo_prod, descricao_prod, quantidade, preco, codigo, descricao ");
	    sql.append("FROM produtos ");               
	    sql.append("INNER JOIN fornecedores ON codigo = fornecedores_codigo order by codigo_prod asc"); //CHAVE ESTRAGEIRA PRODUTOS
 
	    Connection conexao = ConexaoFactory.conectar();
	    PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produtos>lista = new ArrayList<Produtos>();
		
		while(resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));           //FORNECEDOR
			f.setDescricao(resultado.getString("descricao"));    //FORNECEDOR
			
			Produtos p = new Produtos();
			p.setCodigo(resultado.getLong("codigo_prod"));       //PRODUTO
			p.setDescricao(resultado.getString("descricao_prod"));  //PRODUTO
			p.setQuantidade(resultado.getLong("quantidade"));
			p.setPreco(resultado.getDouble("preco"));
			p.setFornecedores(f); // PASS FK
			lista.add(p);
		} 
		return lista;
	}
	
	
	
	
	
}
