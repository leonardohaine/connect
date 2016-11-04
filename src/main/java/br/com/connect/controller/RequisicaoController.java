package br.com.connect.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import br.com.connect.model.Exame;
import br.com.connect.model.Paciente;
import br.com.connect.model.Posto;
import br.com.connect.model.Requisicao;
import br.com.connect.service.RequisicaoService;
import br.com.connect.util.BuscaCep;
import br.com.connect.util.Endereco;

@ManagedBean(name = "requisicao")
@ViewScoped
public class RequisicaoController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2457514004956272802L;
	
	private Paciente paciente = new Paciente();
	private Requisicao requisicao = new Requisicao();
	private List<Requisicao> requisicoes = new ArrayList<Requisicao>();
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	private Paciente selectedPaciente;
	private Posto selectedPosto;
	private String pesquisa;
	private Long idade;
	private char tipoPesquisa = 'P';
	
	private List<Exame> exames = new ArrayList<Exame>();
	
	@ManagedProperty(value = "#{unidade.hospital.cnpj}")
	private String cnpj;
	
	@ManagedProperty(value = "#{RequisicaoService}")
	private RequisicaoService requisicaoService;
	
	public RequisicaoController(){
		
	}
	
	public void calcularIdade(){
		System.out.println("Calculando idade");
		
		if(paciente.getNascimento() != null){
			int anoAtual = Calendar.getInstance().getTime().getYear();
			int anoPaciente = paciente.getNascimento().getYear();
			idade = (long) (anoAtual - anoPaciente);
			paciente.setIdade(idade.intValue());
			System.out.println("Idade: " + idade);
		}
		
	}
	
	public void pesquisarequisicao(){
		
		requisicoes = getRequisicaoService().getRequisicoes();
		if(paciente == null){
			System.out.println("Paciente n�o encontrado");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Paciente n�o encontrado", "Paciente n�o encontrado"));
			return;
		}
		
		System.out.println("Paciente encontrado: " + paciente);
	}
	
	public void postoSelecionado(SelectEvent event) {
		
		Posto posto = (Posto) event.getObject();
		setSelectedPosto(posto);
		paciente.setPosto(posto.getPosto().toString());
		System.out.println("Posto selecionado; " + posto);
	}
	
	public void endereco(){
		
		if(paciente.getCep() != null && paciente.getCep().length() == 8){
			Endereco end = BuscaCep.getEndereco(paciente.getCep());
			if(end == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Aviso!", "CEP n�o encontrado: " + paciente.getCep()));
				
				return;
			}
			paciente.setEndereco(end.getLogradouro());
			paciente.setBairro(end.getBairro());
			paciente.setCidade(end.getLocalidade());
			paciente.setUf(end.getUf());
			
			System.out.println("Cep encontrado: " + end.getCep());
		}	
	}
	
	public String save(){
		try{
			
			//getPacienteService().savePaciente(paciente, requisicao);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Sucesso!", "Paciente cadastrado"));
			return "paciente";
		}catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro!", "Erro ao tentar cadastrar paciente: " +e));
			return null;
		}
		
	}
	
	public void addExame(){
		Exame col = new Exame();
		Exame tri = new Exame();
		
		
		col.setExame(1L);
		col.setDescricao("Colesterol");
		
		tri.setExame(2L);
		tri.setDescricao("Trigricerides");
		
		exames.add(col);
		exames.add(tri);
		
		System.out.println("Adicionando exames a lista.");
	}

	public void onDateSelect(SelectEvent event) {
		System.out.println("Calculando idade - onDateSelect");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", event.getObject().toString()));
    }

	public Long getIdade() {
		return idade;
	}


	public void setIdade(Long idade) {
		
		this.idade = idade;
	}
	
	public void setTipoPesquisa(char tipoPesquisa){
		this.tipoPesquisa = tipoPesquisa;
	}
	
	public char getTipoPesquisa() {
		return tipoPesquisa;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public RequisicaoService getRequisicaoService() {
		return requisicaoService;
	}

	public void setRequisicaoService(RequisicaoService requisicaoService) {
		this.requisicaoService = requisicaoService;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public Paciente getSelectedPaciente() {
		return selectedPaciente;
	}

	public void setSelectedPaciente(Paciente selectedPaciente) {
		this.selectedPaciente = selectedPaciente;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Posto getSelectedPosto() {
		return selectedPosto;
	}

	public void setSelectedPosto(Posto selectedPosto) {
		this.selectedPosto = selectedPosto;
	}

	public List<Requisicao> getRequisicoes() {
		requisicoes = getRequisicaoService().getRequisicoes();
		return requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}
	
}
