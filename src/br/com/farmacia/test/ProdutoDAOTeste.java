package br.com.farmacia.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;


public class ProdutoDAOTeste {

	    //TEST Junit INSERT PRODUTOS
		@Test
		@Ignore
		public void salvar() throws SQLException {	
	        Produtos p1 = new Produtos();
			p1.setDescricao("Dipirona");
			p1.setQuantidade(12L);  //TYPER LONG
			p1.setPreco(2.99);
			Fornecedores f = new Fornecedores();  //ACESSO AO FORNECEDOR
			f.setCodigo(8L);
			p1.setFornecedores(f);                   
			
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.salvar(p1);
		}
		
		//TEST Junit EDITAR PRODUTOS
		@Test
		@Ignore
		public void editar() throws SQLException{
			Produtos p = new Produtos();
			p.setCodigo(5L);
			p.setDescricao("CataFlan");
			p.setQuantidade(2L);
			p.setPreco(15.75);
			
			Fornecedores f = new Fornecedores();
			f.setCodigo(13L);   //EDITANDO ASSOCIAÇÃO
			p.setFornecedores(f);
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.editar(p);
		}
		
		//TEST Junit DELETE PRODUTOS
		@Test
		@Ignore
		public void excluir() throws SQLException{
			Produtos p = new Produtos();
			p.setCodigo(2L);
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(p);
		}
		
		
		//TEST Junit LISTAGEM DE PRODUTOS E ASSOAÇÂO COM FORNECEDORES
		@Test      
        public void listar() throws SQLException {
			ProdutoDAO fdao = new ProdutoDAO();
			ArrayList<Produtos> lista = fdao.listar();
			
			for(Produtos p :lista) {
				System.out.println("Código do Produto: "+ p.getCodigo());			
				System.out.println("Descrição do Produto: "+ p.getDescricao());	
				System.out.println("Quantidade: "+ p.getQuantidade());
				System.out.println("Valor do Produto: "+ p.getPreco());
				
				System.out.println("Código do Fornecedor: "+ p.getFornecedores().getCodigo());
				System.out.println("Descrição do Fornecedor: "+ p.getFornecedores().getDescricao());
				System.out.println("");
			}
		}
}
