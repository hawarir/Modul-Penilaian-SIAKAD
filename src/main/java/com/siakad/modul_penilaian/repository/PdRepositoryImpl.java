package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sia.main.domain.Pd;

@Repository
public class PdRepositoryImpl implements PdRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Pd> get(String where, String order, int limit, int offset) {
		// TODO Auto-generated method stub
		String dbWhere = "";
		String dbOrder = "";
		if(where != "")
			dbWhere += " WHERE " + where;
		if(order != "")
			dbOrder += " ORDER BY " + order;
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Pd" + dbWhere + dbOrder);
		return query.list();
	}

	@Override
	@Transactional
	public Pd getById(UUID idPd) {
		// TODO Auto-generated method stub
		return (Pd) sessionFactory.getCurrentSession().get(Pd.class, idPd);
	}

	@Override
	public UUID insert(Pd pd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Pd pd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(UUID idPd) {
		// TODO Auto-generated method stub

	}

	@Override
	public long count(String where) {
		// TODO Auto-generated method stub
		return 0;
	}

}
