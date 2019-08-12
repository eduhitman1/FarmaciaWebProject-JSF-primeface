package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private Produtos produtos;
	private ArrayList<Produtos>itens;
	private ArrayList<Produtos>itensFiltrados;
	private ArrayList<Fornecedores>comboFornecedores;
	
	
    public Produtos getProdutos() {
		return produtos;
	}
    
    public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	public ArrayList<Produtos> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	//GET E SET DE COMBOBOX
   	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}
	
	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}
	
  
	@PostConstruct   
	public void prepararPesquisa() {
		try {
		ProdutoDAO fdao = new ProdutoDAO();
	        itens = fdao.listar();
	
		} catch (SQLException e) {
	        JSFUtil.adicionarMensagemErro("Erro ao pesquisa: "+e.getMessage());
		
                }
	}
	
	// FUN��O PARA PREPARAR O BUTTON NEW 
	public void prepararNovo() {		 	
		 	try {
		 		FornecedoresDAO dao = new FornecedoresDAO();
		 		produtos = new Produtos();
				comboFornecedores = dao.listar();
			} catch (SQLException e) {
				JSFUtil.adicionarMensagemErro("Erro no Dialogo button new: \n"+e.getMessage());
				e.printStackTrace();
			}
		}
	 public void novo() {
			try {
				ProdutoDAO fdao = new ProdutoDAO();
				fdao.salvar(produtos);
	           
				itens = fdao.listar(); //ATUALIZA��O DE TABLE
				
				JSFUtil.adicionarMensagemSucesso("Produtos Salvo com sucesso");		
			  } catch (SQLException e) {
				JSFUtil.adicionarMensagemErro("Erro ao salva produto: "+e.getMessage());
				e.printStackTrace();
			 }
	 }
	
	 // FUN��O PARA EXCLUIR
	 public void excluir() {
			try {
				ProdutoDAO fdao = new ProdutoDAO();
				fdao.excluir(produtos);
	           
				itens = fdao.listar(); //ATUALIZA��O DE TABLE
				JSFUtil.adicionarMensagemSucesso("Produto excluido");		
			  } catch (SQLException e) {
				JSFUtil.adicionarMensagemErro("N�o � poss�vel excluir o produto" + e.getMessage());
				e.printStackTrace();
			 }
		}
	 
	// FUN��O PARA PREPARAR O BUTTON EDITAR
	   public void editar() {
			try {
				ProdutoDAO fdao = new ProdutoDAO();
				fdao.editar(produtos);
	            itens = fdao.listar(); //ATUALIZA��O DE TABLE
				JSFUtil.adicionarMensagemSucesso("Produto editatdo com sucesso");		
			  } catch (SQLException e) {
				JSFUtil.adicionarMensagemErro("Erro ao editar:\n "+e.getMessage());
				e.printStackTrace();
			 }
		}
	   public void prepararEditar() { // AO EDITAR O COMBOBOX PUXA A LISTA
		 	try {
		 		FornecedoresDAO dao = new FornecedoresDAO();
		 		produtos = new Produtos();
				comboFornecedores = dao.listar();
			} catch (SQLException e) {
				JSFUtil.adicionarMensagemErro("Erro no Dialogo button new: \n"+e.getMessage());
				e.printStackTrace();
			}
		}
	 
	 
	 
	 
}
