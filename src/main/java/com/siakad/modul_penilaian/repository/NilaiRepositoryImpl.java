package com.siakad.modul_penilaian.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sia.main.domain.Nilai;

@Repository
public class NilaiRepositoryImpl implements NilaiRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void insertBulkNilai(List<Nilai> listNilai) {
		// TODO Auto-generated method stub
		for (Nilai nilai : listNilai) {
			nilai = getId(nilai);
			if(nilai.getIdNilai() != null) {
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
	@Transactional
	public Nilai getId(Nilai nilai) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Nilai WHERE id_krs = '" + nilai.getKrs().getIdKrs() 
				+ "' AND id_komponen = '" + nilai.getKomponenNilai().getIdKomponen() + "'");
		if(query.list().isEmpty())
			return nilai;
		else {
			Nilai existingNilai = (Nilai) query.list().get(0);
			existingNilai.setNilai(nilai.getNilai());
			return existingNilai;
		}
	}

	@Override
	@Transactional
	public List<Nilai> getByKrs(List<UUID> listIdKrs) {
		// TODO Auto-generated method stub
		List<Nilai> listNilai = new ArrayList<Nilai>();
		for (UUID idKrs : listIdKrs) {
			Query query = sessionFactory.getCurrentSession().createQuery("FROM Nilai WHERE id_krs = '" + idKrs + "'");
			listNilai.addAll(query.list());
		}
		
		return listNilai;
	}

}
