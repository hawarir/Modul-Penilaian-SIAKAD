package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Krs;

public interface KrsService {
	public List<Krs> getPesertaKelas(UUID idPemb);
	public void updateNilaiAkhir(Krs krs);
	public double getNilaiMutu(UUID idKrs);
	public Krs getById(UUID idKrs);
	public List<Krs> getKrsAktifByPd(UUID idPd, UUID idTglSmt);
}
