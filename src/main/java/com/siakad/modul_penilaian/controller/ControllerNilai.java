package com.siakad.modul_penilaian.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sia.main.domain.Krs;
import com.sia.main.domain.Pd;
import com.sia.main.domain.Pemb;
import com.siakad.modul_penilaian.service.KrsService;
import com.siakad.modul_penilaian.service.PdService;
import com.siakad.modul_penilaian.service.PembService;

@Controller
public class ControllerNilai {
	@Autowired
	private PembService servicePemb;
	
	@Autowired
	private KrsService serviceKrs;
	
	@RequestMapping(value = {"/kelola_nilai/", "/lihat_nilai/"}, method = RequestMethod.GET)
	public ModelAndView tampilkanDaftarKelas(Locale locale, Model model) {
		List<Pemb> kelas = servicePemb.getAllPembelajaran();
		ModelAndView daftarKelas = new ModelAndView();
		daftarKelas.setViewName("daftar_kelas");
		daftarKelas.addObject("listKelas", kelas);
		return daftarKelas;
	}

	@RequestMapping(value = "/kelola_nilai/{idPemb}", method = RequestMethod.GET)
	public ModelAndView tampilkanKelolaNilai(@PathVariable UUID idPemb, Locale locale, Model model) {
		List<Krs> krsInfo = serviceKrs.getPesertaKelas(idPemb);
		List<String> komp = generateKomponenNilai();
		
		ModelAndView kelolaNilai = new ModelAndView();
		kelolaNilai.setViewName("kelola_nilai");
		kelolaNilai.addObject("krsInfo", krsInfo);
		kelolaNilai.addObject("listKomponen", komp);
		
		return kelolaNilai;
	}
	
	@RequestMapping(value = "/lihat_nilai/{idPemb}", method = RequestMethod.GET)
	public ModelAndView tampilkanLihatNilai(@PathVariable UUID idPemb, Locale locale, Model model) {
		List<Krs> krsInfo = serviceKrs.getPesertaKelas(idPemb);
		
		ModelAndView lihatNilai = new ModelAndView();
		lihatNilai.setViewName("lihat_nilai");
		lihatNilai.addObject("krsInfo", krsInfo);
		
		return lihatNilai;
	}
	
	@RequestMapping(value = "/kelola_nilai/{idPemb}/simpan", method = RequestMethod.POST)
	public ModelAndView simpanNilai() {
		return null;
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
