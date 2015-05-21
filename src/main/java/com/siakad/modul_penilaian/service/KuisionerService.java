package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Kuisioner;

public interface KuisionerService {
	public List<Kuisioner> getAllKuisioner();
	public UUID tambahKuisioner(Kuisioner kuisioner);
	public Kuisioner getById(UUID idKuisioner);
}
