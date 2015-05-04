package com.siakad.modul_penilaian.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sia.main.domain.Krs;

@Repository
public class KrsRepositoryImpl implements KrsRepository{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Krs> get(String where, String order, int limit, int offset) {
		// TODO Auto-generated method stub
		String dbWhere = "";
		String dbOrder = "";
		if(where != "")
			dbWhere += " WHERE " + where;
		if(order != "")
			dbOrder += " ORDER BY " + order;
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Krs" + dbWhere + dbOrder);
		return query.list();
	}

	@Override
	@Transactional
	public List<Krs> leftJoinPd(String where, String order) {
		// TODO Auto-generated method stub
		String dbWhere = "";
		String dbOrder = "";
		if(where != "")
			dbWhere += " WHERE " + where;
		if(order != "")
			dbOrder += " ORDER BY " + order;
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT krs FROM Krs as krs LEFT JOIN krs.pd" + dbWhere + dbOrder);
		//System.out.println(query.toString());
		return query.list();
	}
	
}
