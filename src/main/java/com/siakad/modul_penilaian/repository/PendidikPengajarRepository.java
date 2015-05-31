package com.siakad.modul_penilaian.repository;

import java.util.UUID;

public interface PendidikPengajarRepository {
	public void updateNilaiIpd(UUID idPemb, double nilai);
}
