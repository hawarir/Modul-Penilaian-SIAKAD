package com.siakad.modul_penilaian.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sia.main.domain.Kuisioner;

@Repository
public class KuisionerRepositoryImpl implements KuisionerRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Kuisioner> get(String where, String order, int limit, int offset) {
		// TODO Auto-generated method stub
		String dbWhere = "";
		String dbOrder = "";
		if(where != "")
			dbWhere += " WHERE " + where;
		if(order != "")
			dbOrder += " ORDER BY " + order;
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Kuisioner" + dbWhere + dbOrder);
		return query.list();
	}

}
