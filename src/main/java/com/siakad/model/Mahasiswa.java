package com.siakad.model;

import java.util.ArrayList;
import java.util.List;

public class Mahasiswa {
	private String NRP;
	private String nama;
	
	public Mahasiswa(String NRP, String nama) {
		this.NRP = NRP;
		this.nama = nama;
	}
	
	public String getNRP() {
		return NRP;
	}
	public void setNRP(String NRP) {
		this.NRP = NRP;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public static List<Mahasiswa> generateMahasiswa() {
		List<Mahasiswa> listMhs = new ArrayList<Mahasiswa>();
		listMhs.add(new Mahasiswa("5111100152", "Amanda Tiara Averousi"));
		listMhs.add(new Mahasiswa("5111100171", "Hawari Rahman"));
		listMhs.add(new Mahasiswa("5111100050", "Tommy Nurwantoro"));
		
		return listMhs;
	}
}
