package br.com.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.connect.dao.EquipamentoDAO;
import br.com.connect.model.Equipamento;

@Service("EquipamentoService")
@Transactional(readOnly = true)
public class EquipamentoService {

	@Autowired
	EquipamentoDAO equipamentoDAO;

	@Transactional(readOnly = false)
	public void saveEquipamento(Equipamento equipamento) {
		getEquipamentoDAO().saveEquipamento(equipamento);
	}

	@Transactional(readOnly = false)
	public void deleteEquipamento(Equipamento equipamento) {
		getEquipamentoDAO().deleteEquipamento(equipamento);
	}

	@Transactional(readOnly = false)
	public void updateEquipamento(Equipamento equipamento) {
		getEquipamentoDAO().updateEquipamento(equipamento);
	}

	public Equipamento getEquipamentoById(Long id) {
		return getEquipamentoDAO().getEquipamentoById(id);
	}

	public EquipamentoDAO getEquipamentoDAO() {
		return equipamentoDAO;
	}

	
	public void setEquipamentoDAO(EquipamentoDAO equipamentoDAO) {
		this.equipamentoDAO = equipamentoDAO;
	}

	public List<Equipamento> getEquipamentos() {
		return getEquipamentoDAO().getEquipamentos();
	}

}
