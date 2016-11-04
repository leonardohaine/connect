/*
	 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.connect.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.connect.model.Equipamento;


/**
 *
 * @author Leonardo
 */

@Repository
@Transactional
public class EquipamentoDAO extends GenericDAO<Equipamento>{

	@Autowired
	private SessionFactory sessionFactory;

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveEquipamento(Equipamento equipamento) {
		System.out.println("Save: " + equipamento.toString());
		getSessionFactory().getCurrentSession().merge(equipamento);
	}

	public void deleteEquipamento(Equipamento equipamento) {
		getSessionFactory().getCurrentSession().delete(equipamento);
	}

	public void updateEquipamento(Equipamento equipamento) {
		getSessionFactory().getCurrentSession().update(equipamento);
	}

	public Equipamento getEquipamentoById(Long id) {
		Equipamento equipamento = (Equipamento) getSessionFactory().getCurrentSession().get(Equipamento.class, id);

		return equipamento;

	}

	public List<Equipamento> getEquipamentos() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Equipamento").list();
		return list;
	}

}
