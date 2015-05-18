package com.siakad.modul_penilaian.service;

import java.util.List;

import com.sia.main.domain.Ipk;

public interface IpkService {
	public List<Ipk> ambilSemuaIpk();
	public void masukkanIpk(Ipk ipk);
}
