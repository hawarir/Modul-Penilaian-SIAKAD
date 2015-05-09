package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Krs;

public interface KrsService {
	public List<Krs> getPesertaKelas(UUID idPemb);
	public List<Krs> getNilaiPesertaDidik(UUID idPd);
	public Krs getById(UUID idKrs);
}
