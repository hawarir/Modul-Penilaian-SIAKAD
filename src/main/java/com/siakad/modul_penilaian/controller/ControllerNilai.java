package com.siakad.modul_penilaian.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sia.main.domain.KomponenNilai;
import com.sia.main.domain.Krs;
import com.sia.main.domain.Nilai;
import com.sia.main.domain.Pemb;
import com.siakad.modul_penilaian.service.AjaxResponse;
import com.siakad.modul_penilaian.service.JSONNilai;
import com.siakad.modul_penilaian.service.KomponenNilaiService;
import com.siakad.modul_penilaian.service.KrsService;
import com.siakad.modul_penilaian.service.NilaiService;
import com.siakad.modul_penilaian.service.PembService;

@Controller
public class ControllerNilai {
	@Autowired
	private PembService servicePemb;
	
	@Autowired
	private KrsService serviceKrs;
	
	@Autowired
	private KomponenNilaiService serviceKomp;
	
	@Autowired
	private NilaiService serviceNilai;
	
	@RequestMapping(value = {"/kelola_nilai/", "/lihat_nilai/"}, method = RequestMethod.GET)
	public ModelAndView tampilkanDaftarKelas(Locale locale, Model model) {
		List<Pemb> kelas = servicePemb.getAllPembelajaran();
		ModelAndView daftarKelas = new ModelAndView();
		daftarKelas.setViewName("daftar_kelas");
		daftarKelas.addObject("listKelas", kelas);
		return daftarKelas;
	}

	@RequestMapping(value = "/kelola_nilai/{idPemb}/", method = RequestMethod.GET)
	public ModelAndView tampilkanKelolaNilai(@PathVariable UUID idPemb, Locale locale, Model model) {
		List<Krs> krsInfo = serviceKrs.getPesertaKelas(idPemb);
		List<KomponenNilai> komp = serviceKomp.getAllKomponen(idPemb);
		
		ModelAndView kelolaNilai = new ModelAndView();
		kelolaNilai.setViewName("kelola_nilai");
		kelolaNilai.addObject("krsInfo", krsInfo);
		kelolaNilai.addObject("listKomponen", komp);
		
		return kelolaNilai;
	}
	
	@RequestMapping(value = "/lihat_nilai/{idPemb}/", method = RequestMethod.GET)
	public ModelAndView tampilkanLihatNilai(@PathVariable UUID idPemb, Locale locale, Model model) {
		List<Krs> krsInfo = serviceKrs.getPesertaKelas(idPemb);
		
		ModelAndView lihatNilai = new ModelAndView();
		lihatNilai.setViewName("lihat_nilai");
		lihatNilai.addObject("krsInfo", krsInfo);
		
		return lihatNilai;
	}
	
	@RequestMapping(value = "/kelola_nilai/{idPemb}/tambah_komponen/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse tambahKomponenNilai(@RequestBody KomponenNilai komponen, @PathVariable UUID idPemb) {
		Pemb foreignPemb = servicePemb.getById(idPemb);
		komponen.setPemb(foreignPemb);
		UUID idNew = serviceKomp.tambahKomponen(komponen);
		return new AjaxResponse("ok", "Komponen berhasil ditambah", idNew);
	}
	
	@RequestMapping(value = "/kelola_nilai/{idPemb}/hapus_komponen/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse hapusKomponenNilai(@RequestBody UUID idKomp) {
		serviceKomp.hapusKomponen(idKomp);
		return new AjaxResponse();
	}
	
	@RequestMapping(value = "/kelola_nilai/{idPemb}/simpan_nilai/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse simpanNilai(@RequestBody JSONNilai[] listNilaiJSON) {
		List<Nilai> listNilai = new ArrayList<Nilai>();
		for (JSONNilai nilaiJSON : listNilaiJSON) {
			Nilai nilai = new Nilai();
			nilai.setKrs(serviceKrs.getById(nilaiJSON.getIdKrs()));
			nilai.setKomponenNilai(serviceKomp.getById(nilaiJSON.getIdKomp()));
			nilai.setNilai(nilaiJSON.getNilai());
			
			listNilai.add(nilai);
		}
		
		serviceNilai.submitNilai(listNilai);
		return new AjaxResponse("ok", "Nilai berhasil disimpan", null);
	}
}
