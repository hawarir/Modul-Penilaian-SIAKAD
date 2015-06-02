package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Pemb;

public interface PembRepository {
	public Pemb getById(UUID idPemb);
	public List<Pemb> getByTglSmt(UUID idTglSmt);
	public List<Pemb> getAll();
}
