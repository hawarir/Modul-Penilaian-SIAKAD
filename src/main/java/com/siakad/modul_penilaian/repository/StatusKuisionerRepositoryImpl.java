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

import com.sia.main.domain.StatusKuisioner;

@Repository
public class StatusKuisionerRepositoryImpl implements StatusKuisionerRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<StatusKuisioner> getByKrsKuisioner(UUID idKrs, UUID idKuisioner) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("FROM StatusKuisioner WHERE id_krs='" + idKrs + "' AND id_kuisioner='" + idKuisioner + "'");
		return query.list();
	}

	@Override
	public UUID insert(StatusKuisioner status) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UUID insertId = (UUID) session.save(status);
		tx.commit();
		session.flush();
		session.close();
		return insertId;
	}

}