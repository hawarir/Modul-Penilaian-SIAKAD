package com.siakad.modul_penilaian.repository;

import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PendidikPengajarRepositoryImpl implements
		PendidikPengajarRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void updateNilaiIpd(UUID idPemb, double nilai) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("UPDATE PendidikPengajar SET nilaiIpd = " + nilai + " WHERE id_pemb = '" + idPemb + "'");
		query.executeUpdate();
	}

}
