package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sia.main.domain.KomponenNilai;
import com.siakad.modul_penilaian.repository.KomponenNilaiRepository;

@Service
public class KomponenNilaiServiceImpl implements KomponenNilaiService {
	@Autowired
	private KomponenNilaiRepository repositoryKompNilai;
	
	@Override
	public List<KomponenNilai> getAllKomponen(UUID idPemb) {
		// TODO Auto-generated method stub
		return repositoryKompNilai.get("id_pemb='" + idPemb + "'", "", -1, -1);
	}

	@Override
	public void tambahKomponen(KomponenNilai komp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editKomponen(UUID idKomp, String nama, double presentase) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hapusKomponen(UUID idKomp) {
		// TODO Auto-generated method stub

	}

}
