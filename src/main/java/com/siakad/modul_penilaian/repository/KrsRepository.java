package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Krs;

public interface KrsRepository {
	public List<Krs> getByPemb(UUID idPemb);
	public List<Krs> getAktifByPd(UUID idPd, UUID idTglSmt);
	public List<Krs> getAllByPd(UUID idPd, String specialCond);
	public Krs getById(UUID idKrs);
	public void update(Krs krs);
}
