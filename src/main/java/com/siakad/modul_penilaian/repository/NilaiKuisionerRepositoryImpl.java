package com.siakad.modul_penilaian.repository;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sia.main.domain.NilaiKuisioner;

@Repository
public class NilaiKuisionerRepositoryImpl implements NilaiKuisionerRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public UUID insert(NilaiKuisioner nilaiKuisioner) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UUID insertId = (UUID) session.save(nilaiKuisioner);
		tx.commit();
		session.flush();
		session.close();
		return insertId;
	}

}
