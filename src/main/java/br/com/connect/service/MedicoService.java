package br.com.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.connect.dao.MedicoDAO;
import br.com.connect.model.Medico;

@Service("MedicoService")
@Transactional(readOnly = true)
public class MedicoService {

	@Autowired
	MedicoDAO medicoDAO;

	@Transactional(readOnly = false)
	public void saveMedico(Medico medicosol) {
		getMedicoDAO().saveMedico(medicosol);
	}

	@Transactional(readOnly = false)
	public void deleteMedico(Medico medicosol) {
		getMedicoDAO().deleteMedico(medicosol);
	}

	@Transactional(readOnly = false)
	public void updateMedico(Medico medicosol) {
		getMedicoDAO().updateMedico(medicosol);
	}

	public Medico getMedicoById(Long id) {
		return getMedicoDAO().getMedicoById(id);
	}
	
	public Medico getMedicoByNome(String nome) {
		return getMedicoDAO().getMedicoByNome(nome);
	}
	
	/**
	 * @return the medicosolDAO
	 */
	public MedicoDAO getMedicoDAO() {
		return medicoDAO;
	}

	/**
	 * @param medicosolDAO
	 *            the medicosolDAO to set
	 */
	public void setMedicoDAO(MedicoDAO medicoDAO) {
		this.medicoDAO = medicoDAO;
	}

	public List<Medico> getMedico() {
		return getMedicoDAO().getMedico();
	}

}
