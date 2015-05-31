package com.siakad.modul_penilaian.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siakad.modul_penilaian.repository.PendidikPengajarRepository;

@Service
public class PendidikPengajarServiceImpl implements PendidikPengajarService {
	@Autowired
	private PendidikPengajarRepository repositoryPendidikPengajar;

	@Override
	public void masukkanNilaiIpd(UUID idPemb, double nilai) {
		// TODO Auto-generated method stub
		repositoryPendidikPengajar.updateNilaiIpd(idPemb, nilai);
	}

}
