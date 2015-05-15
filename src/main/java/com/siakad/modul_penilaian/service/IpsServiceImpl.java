package com.siakad.modul_penilaian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sia.main.domain.Ips;
import com.siakad.modul_penilaian.repository.IpsRepository;

@Service
public class IpsServiceImpl implements IpsService {
	@Autowired
	private IpsRepository repositoryIps;

	@Override
	public void masukkanIps(Ips ips) {
		// TODO Auto-generated method stub

	}

}
