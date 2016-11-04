package br.com.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.connect.dao.MaterialDAO;
import br.com.connect.dao.UsuarioDAO;
import br.com.connect.model.Material;
import br.com.connect.model.Usuario;

@Service("MaterialService")
@Transactional(readOnly = true)
public class MaterialService {

	@Autowired
	MaterialDAO materialDAO;

	@Transactional(readOnly = false)
	public void saveMaterial(Material material) {
		getMaterialDAO().saveMaterial(material);
	}

	@Transactional(readOnly = false)
	public void deleteMaterial(Material material) {
		getMaterialDAO().deleteMaterial(material);
	}

	@Transactional(readOnly = false)
	public void updateMaterial(Material material) {
		getMaterialDAO().updateMaterial(material);
	}

	public Material geMaterialById(Long id) {
		return getMaterialDAO().getMaterialById(id);
	}

	/**
	 * @return the materialDAO
	 */
	public MaterialDAO getMaterialDAO() {
		return materialDAO;
	}

	/**
	 * @param materialDAO
	 *            the materialDAO to set
	 */
	public void setMaterialDAO(MaterialDAO MaterialDAO) {
		this.materialDAO = MaterialDAO;
	}

	public List<Material> getMaterial() {
		return getMaterialDAO().getMaterial();
	}

}
