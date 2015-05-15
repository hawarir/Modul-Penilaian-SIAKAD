package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sia.main.domain.Pd;

public interface PdRepository {
	public List<Pd> get(String where, String order, int limit, int offset);
	public Pd getById(UUID idPd);
}
