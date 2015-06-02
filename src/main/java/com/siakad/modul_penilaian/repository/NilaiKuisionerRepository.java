package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.NilaiKuisioner;

public interface NilaiKuisionerRepository {
	public UUID insert(NilaiKuisioner nilaiKuisioner);
	public List<NilaiKuisioner> getByPembPertanyaan(UUID idPemb, UUID idPertanyaan);
}
