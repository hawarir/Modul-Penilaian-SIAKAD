package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.KonversiNilai;

public interface KonversiNilaiService {
	public List<KonversiNilai> getKonversiNilai();
	public UUID tambahKonversiNilai(KonversiNilai konversi);
	public void hapusKonversiNilai(UUID idKonversi);
	public void simpanKonversiNilai(KonversiNilai[] listKonversi);
	public KonversiNilai getByBatas(double batas);
}
