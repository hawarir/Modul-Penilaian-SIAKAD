package com.siakad.modul_penilaian.service;

import java.util.List;

import com.sia.main.domain.Ips;

public interface IpsService {
	public void masukkanIps(Ips ips);
	public List<Ips> ambilSemuaIps();
}
