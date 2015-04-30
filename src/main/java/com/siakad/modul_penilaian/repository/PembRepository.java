package com.siakad.modul_penilaian.repository;

import java.util.List;

import com.sia.main.domain.Pemb;

public interface PembRepository {
	public List<Pemb> get(String where, String order, int limit, int offset);
	public List<Pemb> leftOuterJoin();
}
