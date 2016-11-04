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
import javax.faces.event.ActionEvent;

import br.com.connect.model.Material;
import br.com.connect.service.MaterialService;

@ManagedBean(name = "material")
@ViewScoped
public class MaterialController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2457514004956272802L;
	
	private Material material = new Material();
	private List<Material> materiais = new ArrayList<Material>();
	private Material selectedMaterial;
	
	@ManagedProperty(value = "#{MaterialService}")
	private MaterialService materialService;
	
	public MaterialController(){

	}
	
	public String save(){
		try{
			System.out.println("Save Material: " + material);
			getMaterialService().saveMaterial(material);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Sucesso!", "Material cadastrado"));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro!", "Erro ao tentar cadastrar material: " +e));
		}
		
		return "listaMaterial";
	}
	
	public String delete(){
		try{
			getMaterialService().deleteMaterial(selectedMaterial);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Sucesso!", "Material deletado: " + selectedMaterial.getDescricao()));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro!", "Erro ao deletar material: " +e));
		}
		
		return "listaMaterial";
	}
	
	@PostConstruct
	public void editar(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("materialId");
		if(id != null){
			System.out.println("ID editar: " + id);
			material = getMaterialService().geMaterialById(Long.valueOf(id));
		}
	}
	
	public MaterialService getMaterialService() {
		return materialService;
	}

	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}



	public Material getMaterial() {
		return material;
	}



	public void setMaterial(Material material) {
		this.material = material;
	}



	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}



	public List<Material> getMateriais() {
		materiais = getMaterialService().getMaterial();
		return materiais;
	}



	public Material getSelectedMaterial() {
		return selectedMaterial;
	}



	public void setSelectedMaterial(Material selectedMaterial) {
		this.selectedMaterial = selectedMaterial;
	}

}
