package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Krs;

public interface KrsRepository {
	public List<Krs> get(String where, String order, int limit, int offset);
	public List<Krs> leftJoinPd(String where, String order);
	public Krs getById(UUID idKrs);
}
