package com.siakad.modul_penilaian.repository;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sia.main.domain.TglSmt;

@Repository
public class TglSmtRepositoryImpl implements TglSmtRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public TglSmt getAktif() {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery("FROM TglSmt WHERE aTglSmtAktif = TRUE");
		return (TglSmt) query.list().get(0);
	}

}
