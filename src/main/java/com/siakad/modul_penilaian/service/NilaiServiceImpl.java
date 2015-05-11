package com.siakad.modul_penilaian.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sia.main.domain.Krs;
import com.sia.main.domain.Nilai;
import com.siakad.modul_penilaian.repository.NilaiRepository;

@Service
public class NilaiServiceImpl implements NilaiService {
	@Autowired
	private NilaiRepository repositoryNilai;
	
	@Override
	public void submitNilai(List<Nilai> listNilai) {
		// TODO Auto-generated method stub
		repositoryNilai.insertBulkNilai(listNilai);
	}

	@Override
	public List<Nilai> getNilaiKelas(List<Krs> listKrs) {
		// TODO Auto-generated method stub
		List<UUID> listIdKrs = new ArrayList<UUID>();
		for (Krs krs : listKrs) {
			listIdKrs.add(krs.getIdKrs());
		}
		return repositoryNilai.getByKrs(listIdKrs);
	}

}
