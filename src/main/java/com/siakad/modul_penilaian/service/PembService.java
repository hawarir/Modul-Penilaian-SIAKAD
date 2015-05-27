package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Pemb;

public interface PembService {
	public List<Pemb> ambilSemuaPemb();
	public List<Pemb> ambilPembAktif(UUID idTglSmt);
	public Pemb ambilPemb(UUID idPemb);
}
