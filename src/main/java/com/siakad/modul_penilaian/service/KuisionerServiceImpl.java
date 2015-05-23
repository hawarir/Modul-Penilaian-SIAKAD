package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

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

	@Override
	public UUID tambahKuisioner(Kuisioner kuisioner) {
		// TODO Auto-generated method stub
		return repositoryKuisioner.insert(kuisioner);
	}

	@Override
	public Kuisioner getById(UUID idKuisioner) {
		// TODO Auto-generated method stub
		return repositoryKuisioner.getById(idKuisioner);
	}

	@Override
	public void hapusKuisioner(UUID idKuisioner) {
		// TODO Auto-generated method stub
		repositoryKuisioner.delete(idKuisioner);
	}

}
