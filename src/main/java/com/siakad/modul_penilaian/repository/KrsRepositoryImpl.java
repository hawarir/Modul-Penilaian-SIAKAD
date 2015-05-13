package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	public List<Krs> getByPemb(UUID idPemb) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT krs FROM Krs as krs LEFT JOIN krs.pd LEFT JOIN krs.konversiNilai WHERE id_pemb='" + idPemb + "' ORDER BY nim_pd ASC");
		return query.list();
	}

	@Override
	@Transactional
	public Krs getById(UUID idKrs) {
		// TODO Auto-generated method stub
		return (Krs) sessionFactory.getCurrentSession().get(Krs.class, idKrs);
	}

	@Override
	public void update(Krs krs) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(krs);
		tx.commit();
		session.flush();
		session.close();
	}
	
}
