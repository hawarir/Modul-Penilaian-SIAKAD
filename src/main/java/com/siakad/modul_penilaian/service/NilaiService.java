package com.siakad.modul_penilaian.service;

import java.util.List;

import com.sia.main.domain.Krs;
import com.sia.main.domain.Nilai;

public interface NilaiService {
	public void submitNilai(List<Nilai> listNilai);
	public List<Nilai> getNilaiKelas(List<Krs> listKrs);
	public double getNilaiAkhir(Krs krs);
}
