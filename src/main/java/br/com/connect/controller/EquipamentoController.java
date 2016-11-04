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

import br.com.connect.model.Equipamento;
import br.com.connect.service.EquipamentoService;

@ManagedBean(name = "equipamento")
@ViewScoped
public class EquipamentoController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2457514004956272802L;
	
	private Equipamento equipamento = new Equipamento();
	private List<Equipamento> equipamentos = new ArrayList<Equipamento>();
	private Equipamento selectedEquipamento;
	
	@ManagedProperty(value = "#{EquipamentoService}")
	private EquipamentoService equipamentoService;
	
	public EquipamentoController(){
		
	}
	
	
	
	public String save(){
		try{
			getEquipamentoService().saveEquipamento(equipamento);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Sucesso!", "Equipamento cadastrado"));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro!", "Erro ao tentar cadastrar equipamento: " +e));
			return null;
		}
		return "listaEquipamento";
	}
	
	public String delete(){
		System.out.println("DELETE EQUIPAMENTO: " + selectedEquipamento);
		try{
			getEquipamentoService().deleteEquipamento(selectedEquipamento);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Sucesso!", "Equipamento deletado: " + selectedEquipamento.getDescricao().toUpperCase() ));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro!", "Erro ao tentar deletar equipamento: " +e));
			return "equipamento";
		}
		
		return "listaEquipamento";
	}
	
	@PostConstruct
	public void editar(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("equipamentoId");
		if(id != null){
			System.out.println("ID editar: " + id);
			equipamento = getEquipamentoService().getEquipamentoById(Long.valueOf(id));
		}
	}
	
	public EquipamentoService getEquipamentoService() {
		return equipamentoService;
	}

	public void setEquipamentoService(EquipamentoService equipamentoService) {
		this.equipamentoService = equipamentoService;
	}



	public Equipamento getEquipamento() {
		return equipamento;
	}



	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}



	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}



	public List<Equipamento> getEquipamentos() {
		equipamentos = getEquipamentoService().getEquipamentos();
		return equipamentos;
	}



	public Equipamento getSelectedEquipamento() {
		return selectedEquipamento;
	}



	public void setSelectedEquipamento(Equipamento selectedEquipamento) {
		this.selectedEquipamento = selectedEquipamento;
	}
	
}
