package com.siakad.modul_penilaian.repository;

import java.util.UUID;

import com.sia.main.domain.NilaiKuisioner;

public interface NilaiKuisionerRepository {
	public UUID insert(NilaiKuisioner nilaiKuisioner);
}
