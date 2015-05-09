package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.KomponenNilai;

public interface KomponenNilaiService {
	public List<KomponenNilai> getAllKomponen(UUID idPemb);
	public UUID tambahKomponen(KomponenNilai komp);
	public void editKomponen(UUID idKomp, String nama, double presentase);
	public void hapusKomponen(UUID idKomp);
	public KomponenNilai getById(UUID idKomp);
}
