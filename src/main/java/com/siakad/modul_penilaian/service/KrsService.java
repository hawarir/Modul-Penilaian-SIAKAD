package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Krs;

public interface KrsService {
	public List<Krs> ambilPesertaKelas(UUID idPemb);
	public void perbaruiNilaiAkhir(Krs krs);
	public double ambilNilaiMutu(UUID idKrs);
	public Krs ambilKrs(UUID idKrs);
	public List<Krs> ambilKrsAktifBerdasarkanPd(UUID idPd, UUID idTglSmt);
	public List<Krs> ambilSemuaBerdasarkanPd(UUID idPd);
}
