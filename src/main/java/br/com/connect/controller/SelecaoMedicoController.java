package br.com.connect.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.connect.model.Material;
import br.com.connect.model.Medico;
import br.com.connect.service.MedicoService;
	

@ViewScoped
@ManagedBean(name = "selecaoMedico")
public class SelecaoMedicoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MedicoService}")
	private MedicoService medicoService;
	
	private String nome;
	
	private List<Medico> medicosFiltrados = new ArrayList<Medico>();
	private Medico selectedMedico;
	public SelecaoMedicoController(){
		
	}
	
	public void pesquisar() {
		medicosFiltrados = (List<Medico>) getMedicoService().getMedicoByNome(nome);
	}
	
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 300);
		opcoes.put("appendToBody", true);
		System.out.println("abrindo dialogo postos");
		RequestContext.getCurrentInstance().openDialog("selecaoMedico", opcoes, null);
	}
	
	
	public void selecionar(Medico posto) {
		
		RequestContext.getCurrentInstance().closeDialog(posto);
		System.out.println("fechando dialogo postos: " + posto);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public MedicoService getMedicoService() {
		return medicoService;
	}

	public void setMedicoService(MedicoService medicoService) {
		this.medicoService = medicoService;
	}

	public Medico getSelectedMedico() {
		System.out.println("getSelectedMedico; " + selectedMedico);
		return selectedMedico;
	}

	public void setSelectedMedico(Medico selectedMedico) {
		System.out.println("setSelectedMedico; " + selectedMedico);
		this.selectedMedico = selectedMedico;
	}

	public List<Medico> getMedicosFiltrados() {
		return medicosFiltrados;
	}

	public void setMedicosFiltrados(List<Medico> postosFiltrados) {
		this.medicosFiltrados = postosFiltrados;
	}

}