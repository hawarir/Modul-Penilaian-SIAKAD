package com.siakad.modul_penilaian;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.siakad.model.Mahasiswa;

@Controller
public class ControllerNilai {
	
	@RequestMapping(value = {"/kelola_nilai/", "/lihat_nilai/"}, method = RequestMethod.GET)
	public ModelAndView tampilkanDaftarKelas(Locale locale, Model model) {
		List<String> kelas = generateKelas();
		ModelAndView daftarKelas = new ModelAndView();
		daftarKelas.setViewName("daftar_kelas");
		daftarKelas.addObject("listKelas", kelas);
		
		return daftarKelas;
	}

	@RequestMapping(value = "/kelola_nilai/{namaKelas}", method = RequestMethod.GET)
	public ModelAndView tampilkanKelolaNilai(@PathVariable String namaKelas, Locale locale, Model model) {
		List<Mahasiswa> mhs = Mahasiswa.generateMahasiswa();
		List<String> komp = generateKomponenNilai();
		
		ModelAndView kelolaNilai = new ModelAndView();
		kelolaNilai.setViewName("kelola_nilai");
		kelolaNilai.addObject("namaKelas", namaKelas);
		kelolaNilai.addObject("listMhs", mhs);
		kelolaNilai.addObject("listKomponen", komp);
		
		return kelolaNilai;
	}
	
	@RequestMapping(value = "/lihat_nilai/{namaKelas}", method = RequestMethod.GET)
	public ModelAndView tampilkanLihatNilai(@PathVariable String namaKelas, Locale locale, Model model) {
		List<Mahasiswa> mhs = Mahasiswa.generateMahasiswa();
		
		ModelAndView lihatNilai = new ModelAndView();
		lihatNilai.setViewName("lihat_nilai");
		lihatNilai.addObject("namaKelas", namaKelas);
		lihatNilai.addObject("listMhs", mhs);
		
		return lihatNilai;
	}
	
	public List<String> generateKelas() {
		List<String> listKelas = new ArrayList<String>();
		listKelas.add("Konstruksi Perangkat Lunak A");
		listKelas.add("Perancangan Perangkat Lunak B");
		listKelas.add("Manajemen Proyek Perangkat Lunak C");
		
		return listKelas;
	}
	
	public List<String> generateKomponenNilai() {
		List<String> listKomponen = new ArrayList<String>();
		listKomponen.add("Kuis");
		listKomponen.add("UTS");
		listKomponen.add("UAS");
		listKomponen.add("Final Project");
		
		return listKomponen;
	}
}
