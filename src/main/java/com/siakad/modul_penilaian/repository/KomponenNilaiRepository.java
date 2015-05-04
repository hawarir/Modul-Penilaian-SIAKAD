package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.KomponenNilai;

public interface KomponenNilaiRepository {
	public List<KomponenNilai> get(String where, String order, int limit, int offset);
	public UUID insert(KomponenNilai komp);
	public void update(KomponenNilai komp);
	public void delete(UUID idKomp);
}
