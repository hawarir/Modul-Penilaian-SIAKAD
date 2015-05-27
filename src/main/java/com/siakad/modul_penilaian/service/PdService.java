package com.siakad.modul_penilaian.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sia.main.domain.Pd;

public interface PdService {
	public List<Pd> ambilSemuaPd();
}
