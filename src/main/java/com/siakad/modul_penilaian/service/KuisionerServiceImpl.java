package com.siakad.modul_penilaian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sia.main.domain.Kuisioner;
import com.siakad.modul_penilaian.repository.KuisionerRepository;

@Service
public class KuisionerServiceImpl implements KuisionerService {
	@Autowired
	private KuisionerRepository repositoryKuisioner;

	@Override
	public List<Kuisioner> getAllKuisioner() {
		// TODO Auto-generated method stub
		return repositoryKuisioner.get("aKuisionerAktif = TRUE", "", -1, -1);
	}

}
