package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sia.main.domain.Krs;
import com.siakad.modul_penilaian.repository.KrsRepository;

@Service
public class KrsServiceImpl implements KrsService {
	@Autowired
	private KrsRepository repositoryKrs;

	@Override
	public List<Krs> getPesertaKelas(UUID idPemb) {
		// TODO Auto-generated method stub
		return repositoryKrs.leftJoinPd("id_pemb='" + idPemb + "'", "");
	}

	@Override
	public List<Krs> getNilaiPesertaDidik(UUID idPd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Krs getById(UUID idKrs) {
		// TODO Auto-generated method stub
		return repositoryKrs.getById(idKrs);
	}
	
}
