package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sia.main.domain.Nilai;

@Repository
public class NilaiRepositoryImpl implements NilaiRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertBulkNilai(List<Nilai> listNilai) {
		// TODO Auto-generated method stub
		for (Nilai nilai : listNilai) {
			if(isExist(nilai.getKrs().getIdKrs(), nilai.getKomponenNilai().getIdKomponen())) {
				updateNilai(nilai);
			}
			else {
				insertNilai(nilai);
			}
		}
	}

	@Override
	public UUID insertNilai(Nilai nilai) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UUID insertId = (UUID) session.save(nilai);
		tx.commit();
		session.flush();
		session.close();
		return insertId;
	}

	@Override
	public void updateNilai(Nilai nilai) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(nilai);
		tx.commit();
		session.flush();
		session.close();
	}

	@Override
	public boolean isExist(UUID idKrs, UUID idKomponen) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Nilai WHERE id_krs = :idKrs AND id_komponen = :idKomponen");
		query.setParameter("idKrs", idKrs);
		query.setParameter("idKomponen", idKomponen);
		if(query.list().isEmpty())
			return false;
		else
			return true;
	}

}
