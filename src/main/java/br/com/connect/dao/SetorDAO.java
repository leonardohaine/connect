/*
	 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.connect.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.connect.model.Setor;


/**
 *
 * @author Leonardo
 */

@Repository
@Transactional
public class SetorDAO extends GenericDAO<Setor>{

	@Autowired
	private SessionFactory sessionFactory;

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveSetor(Setor setor) {
		System.out.println("Save: " + setor.toString());
		getSessionFactory().getCurrentSession().merge(setor);
	}

	public void deleteSetor(Setor setor) {
		getSessionFactory().getCurrentSession().delete(setor);
	}

	public void updateSetor(Setor setor) {
		getSessionFactory().getCurrentSession().update(setor);
	}

	public Setor getSetorById(Long id) {
		Setor setor = (Setor) getSessionFactory().getCurrentSession().get(Setor.class, id);

		return setor;

	}

	public List<Setor> getSetores() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Setor").list();
		return list;
	}

}
