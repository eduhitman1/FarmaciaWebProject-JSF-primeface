package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {
    private Fornecedores fornecedores;
	private ArrayList<Fornecedores>itens;
	private ArrayList<Fornecedores>itensFiltrados;
	
	public Fornecedores getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public ArrayList<Fornecedores> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	
	
	
  //EXECU��O PARA PUXA OS DADOS AUTOMATICAMENTE
	@PostConstruct   
	public void prepararPesquisa() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
	        itens = fdao.listar();
	
		} catch (SQLException e) {
	        JSFUtil.adicionarMensagemErro("Erro ao pesquisa: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
        
	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}
	public void novo() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salva(fornecedores);
           
			itens = fdao.listar(); //ATUALIZA��O DE TABLE
			
			JSFUtil.adicionarMensagemSucesso("Fornecedores Salvo com sucesso");		
		  } catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Erro de lista button novo: "+e.getMessage());
			e.printStackTrace();
		 }
	   }
	
	  // FUN��O PARA PREPARAR O BUTTON EXCLUIR
       public void excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);
           
			itens = fdao.listar(); //ATUALIZA��O DE TABLE
			
			JSFUtil.adicionarMensagemSucesso("Fornecedor excluido");		
		  } catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("N�o � poss�vel excluir um fornecedor que tenha um produto associado");
			e.printStackTrace();
		 }
	}
	
	// FUN��O PARA PREPARAR O BUTTON EDITAR
	   public void editar() {
			try {
				FornecedoresDAO fdao = new FornecedoresDAO();
				fdao.editar(fornecedores);
	           
				itens = fdao.listar(); //ATUALIZA��O DE TABLE
				
				JSFUtil.adicionarMensagemSucesso("Fornecedor editatdo com sucesso");		
			  } catch (SQLException e) {
				JSFUtil.adicionarMensagemErro("N�o � poss�vel editar o fornecedor quando tem um produto associado:\n "+e.getMessage());
				e.printStackTrace();
			 }
		}
	
	
}
