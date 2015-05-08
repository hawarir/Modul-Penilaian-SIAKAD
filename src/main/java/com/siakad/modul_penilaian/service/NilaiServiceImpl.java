package com.siakad.modul_penilaian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
