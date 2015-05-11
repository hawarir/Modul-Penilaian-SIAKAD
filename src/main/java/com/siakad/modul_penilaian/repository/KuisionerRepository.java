package com.siakad.modul_penilaian.repository;

import java.util.List;

import com.sia.main.domain.Kuisioner;

public interface KuisionerRepository {
	public List<Kuisioner> get(String where, String order, int limit, int offset);
}
