package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sia.main.domain.Pd;

public interface PdService {
	public List<Pd> getAll();
	public List<Pd> getByPembelajaran(String pemb);
	public Pd getById(UUID idPd);
	public String save(Pd pd);
	public String delete(UUID idPd);
}
