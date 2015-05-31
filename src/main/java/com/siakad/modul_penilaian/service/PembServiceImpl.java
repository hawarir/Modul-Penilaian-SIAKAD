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
	public List<Pemb> ambilSemuaPemb() {
		// TODO Auto-generated method stub
		return repositoryPemb.getAll();
	}
	
	@Override
	public List<Pemb> ambilPembAktif(UUID idTglSmt) {
		// TODO Auto-generated method stub
		return repositoryPemb.get("id_tgl_smt = '" + idTglSmt + "'", "", -1, -1);
	}

	@Override
	public Pemb ambilPemb(UUID idPemb) {
		// TODO Auto-generated method stub
		return repositoryPemb.getById(idPemb);
	}

}
