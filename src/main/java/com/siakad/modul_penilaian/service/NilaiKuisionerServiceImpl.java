package com.siakad.modul_penilaian.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sia.main.domain.NilaiKuisioner;
import com.siakad.modul_penilaian.repository.NilaiKuisionerRepository;

@Service
public class NilaiKuisionerServiceImpl implements NilaiKuisionerService {
	@Autowired
	private NilaiKuisionerRepository repositoryNilaiKuisioner;
	
	@Override
	public UUID simpanNilaiKuisioner(NilaiKuisioner nilaiKuisioner) {
		// TODO Auto-generated method stub
		return repositoryNilaiKuisioner.insert(nilaiKuisioner);
	}

}
