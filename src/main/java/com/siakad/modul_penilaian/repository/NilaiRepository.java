package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Nilai;

public interface NilaiRepository {
	public void insertBulkNilai(List<Nilai> listNilai);
	public UUID insertNilai(Nilai nilai);
	public void updateNilai(Nilai nilai);
	public Nilai getId(Nilai nilai);
	public List<Nilai> getByKrs(List<UUID> listIdKrs);
	public double getNilaiAkhir(UUID idKrs);
}
