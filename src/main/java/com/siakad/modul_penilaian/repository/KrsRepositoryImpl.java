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

	private final String kondisiKrsOke = "krs.aKrsBatal = FALSE AND krs.aKrsTerhapus = FALSE AND krs.aKrsDisetujui = TRUE";
	
	@Override
	@Transactional
	public List<Krs> getByPemb(UUID idPemb) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT krs FROM Krs as krs LEFT JOIN krs.pd LEFT JOIN krs.konversiNilai WHERE id_pemb='" + idPemb + "' AND " + kondisiKrsOke + " ORDER BY nim_pd ASC");
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

	@Override
	@Transactional
	public List<Krs> getAktifByPd(UUID idPd, UUID idTglSmt) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT krs FROM Krs krs WHERE krs.pd.idPd = '" + idPd + "' AND krs.pemb.tglSmt.idTglSmt = '" + idTglSmt + "' AND " + kondisiKrsOke);
		return query.list();
	}

	@Override
	@Transactional
	public List<Krs> getAllByPd(UUID idPd) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT krs FROM Krs krs WHERE krs.pd.idPd = '" + idPd + "' AND krs.aKrsDiulang = FALSE AND " + kondisiKrsOke);
		return query.list();
	}
	
}
