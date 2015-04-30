package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sia.main.domain.Pd;
import com.siakad.modul_penilaian.repository.PdRepository;

@Service
public class PdServiceImpl implements PdService {
	@Autowired
	private PdRepository repositoryPd;
	
	@Override
	public List<Pd> getAll() {
		// TODO Auto-generated method stub
		return repositoryPd.get("", "", -1, -1);
	}

	@Override
	public List<Pd> getByPembelajaran(String pemb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pd getById(UUID idPd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(Pd pd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(UUID idPd) {
		// TODO Auto-generated method stub
		return null;
	}

}
