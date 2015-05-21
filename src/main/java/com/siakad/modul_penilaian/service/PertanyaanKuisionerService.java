package com.siakad.modul_penilaian.service;

import java.util.UUID;

import com.sia.main.domain.PertanyaanKuisioner;

public interface PertanyaanKuisionerService {
	public UUID tambahPertanyaan(PertanyaanKuisioner pertanyaan);
	public void simpanPertanyaan(PertanyaanKuisioner pertanyaan);
	public void hapusPertanyaan(UUID idPertanyaan);
}
