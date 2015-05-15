package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Pemb;

public interface PembService {
	public List<Pemb> getAllPembelajaran();
	public List<Pemb> getPembAktif(UUID idTglSmt);
	public Pemb getById(UUID idPemb);
}
