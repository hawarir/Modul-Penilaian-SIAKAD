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

import com.sia.main.domain.KomponenNilai;

@Repository
public class KomponenNilaiRepositoryImpl implements KomponenNilaiRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<KomponenNilai> get(String where, String order, int limit,
			int offset) {
		// TODO Auto-generated method stub
		String dbWhere = "";
		String dbOrder = "";
		if(where != "")
			dbWhere += " WHERE " + where;
		if(order != "")
			dbOrder += " ORDER BY " + order;
		Query query = sessionFactory.getCurrentSession().createQuery("FROM komponen_nilai " + dbWhere + dbOrder);
		return query.list();
	}

	@Override
	public UUID insert(KomponenNilai komp) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UUID insertId = (UUID)session.save(komp);
		tx.commit();
		session.flush();
		session.close();
		return insertId;
	}

	@Override
	public void update(KomponenNilai komp) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(komp);
		tx.commit();
		session.flush();
		session.close();
	}

	@Override
	public void delete(UUID idKomp) {
		// TODO Auto-generated method stub

	}

}
