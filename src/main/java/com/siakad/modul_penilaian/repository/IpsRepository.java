package com.siakad.modul_penilaian.repository;

import java.util.List;
import java.util.UUID;

import com.sia.main.domain.Ips;

public interface IpsRepository {
	public List<Ips> getAll();
	public UUID insert(Ips ips);
	public void update(Ips ips);
	public Ips find(UUID idPd, UUID idTglSmt);
}
