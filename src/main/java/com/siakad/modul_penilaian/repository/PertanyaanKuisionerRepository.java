package com.siakad.modul_penilaian.repository;

import java.util.UUID;

import com.sia.main.domain.PertanyaanKuisioner;

public interface PertanyaanKuisionerRepository {
	public UUID insert(PertanyaanKuisioner pertanyaan);
	public void update(PertanyaanKuisioner pertanyaan);
	public void delete(UUID idPertanyaan);
}
