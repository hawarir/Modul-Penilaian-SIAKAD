package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sia.main.domain.Pemb;
import com.siakad.modul_penilaian.repository.PembRepository;

@Service
public class PembServiceImpl implements PembService {
	@Autowired
	private PembRepository repositoryPemb;
	
	@Override
	public List<Pemb> getAllPembelajaran() {
		// TODO Auto-generated method stub
		return repositoryPemb.leftJoinMk();
	}
	
	@Override
	public List<Pemb> getPembAktif(UUID idTglSmt) {
		// TODO Auto-generated method stub
		return repositoryPemb.get("id_tgl_smt = '" + idTglSmt + "'", "", -1, -1);
	}

	@Override
	public Pemb getById(UUID idPemb) {
		// TODO Auto-generated method stub
		return repositoryPemb.getById(idPemb);
	}

}
