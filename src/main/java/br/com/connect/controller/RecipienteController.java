package br.com.connect.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.connect.model.Recipiente;
import br.com.connect.service.RecipienteService;

@ManagedBean(name = "recipiente")
@ViewScoped
public class RecipienteController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2457514004956272802L;
	
	private Recipiente recipiente = new Recipiente();
	private List<Recipiente> recipientes = new ArrayList<Recipiente>();
	private Recipiente selectedRecipiente;
	
	@ManagedProperty(value = "#{RecipienteService}")
	private RecipienteService recipienteService;
	
	public RecipienteController(){
		
	}
	
	
	
	public String save(){
		try{
			getRecipienteService().saveRecipiente(recipiente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Sucesso!", "Recipiente cadastrado"));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro!", "Erro ao tentar cadastrar recipiente: " +e));
		}
		
		return "listaRecipiente";
	}
	
	public String delete(){
		try{
			getRecipienteService().deleteRecipiente(selectedRecipiente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Sucesso!", "Recipiente deletado"));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro!", "Erro ao tentar deletar recipiente: " +e));
		}
		
		return "listaRecipiente";
	}
	
	@PostConstruct
	public void editar(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("recipienteId");
		if(id != null){
			System.out.println("ID editar: " + id);
			recipiente = getRecipienteService().getRecipienteById(Long.valueOf(id));
		}
	}
	
	public RecipienteService getRecipienteService() {
		return recipienteService;
	}

	public void setRecipienteService(RecipienteService recipienteService) {
		this.recipienteService = recipienteService;
	}



	public Recipiente getRecipiente() {
		return recipiente;
	}



	public void setRecipiente(Recipiente recipiente) {
		this.recipiente = recipiente;
	}



	public void setRecipientes(List<Recipiente> recipientes) {
		this.recipientes = recipientes;
	}



	public List<Recipiente> getRecipientes() {
		recipientes = getRecipienteService().getRecipientes();
		return recipientes;
	}



	public Recipiente getSelectedRecipiente() {
		return selectedRecipiente;
	}



	public void setSelectedRecipiente(Recipiente selectedRecipiente) {
		this.selectedRecipiente = selectedRecipiente;
	}
	
}
