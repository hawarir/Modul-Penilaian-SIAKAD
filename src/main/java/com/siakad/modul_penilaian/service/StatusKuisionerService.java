package com.siakad.modul_penilaian.service;

import java.util.UUID;

import com.sia.main.domain.StatusKuisioner;

public interface StatusKuisionerService {
	public boolean apakahKuisionerTerisi(UUID idKrs, UUID idKuisioner);
	public UUID masukkanStatus(StatusKuisioner status);
}
