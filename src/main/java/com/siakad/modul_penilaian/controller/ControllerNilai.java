package com.siakad.modul_penilaian.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.sia.main.domain.KonversiNilai;
import com.sia.main.domain.Krs;
import com.sia.main.domain.Nilai;
import com.sia.main.domain.Pemb;
import com.siakad.modul_penilaian.service.AjaxResponse;
import com.siakad.modul_penilaian.service.JSONNilai;
import com.siakad.modul_penilaian.service.KomponenNilaiService;
import com.siakad.modul_penilaian.service.KonversiNilaiService;
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
	
	@Autowired
	private KonversiNilaiService serviceKonversi;
	
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
		List<Nilai> listNilai = serviceNilai.getNilaiKelas(krsInfo);
		Pemb pemb = servicePemb.getById(idPemb);
		String namaKelas = pemb.getMk().getNamaMK() + " " + pemb.getNmPemb();
		
		ModelAndView kelolaNilai = new ModelAndView();
		kelolaNilai.setViewName("kelola_nilai");
		kelolaNilai.addObject("krsInfo", krsInfo);
		kelolaNilai.addObject("listKomponen", komp);
		kelolaNilai.addObject("listNilai", listNilai);
		kelolaNilai.addObject("namaKelas", namaKelas);
		
		return kelolaNilai;
	}
	
	@RequestMapping(value = "/lihat_nilai/{idPemb}/", method = RequestMethod.GET)
	public ModelAndView tampilkanLihatNilai(@PathVariable UUID idPemb, Locale locale, Model model) {
		List<Krs> krsInfo = serviceKrs.getPesertaKelas(idPemb);
		Pemb pemb = servicePemb.getById(idPemb);
		String namaKelas = pemb.getMk().getNamaMK() + " " + pemb.getNmPemb();
		
		ModelAndView lihatNilai = new ModelAndView();
		lihatNilai.setViewName("lihat_nilai");
		lihatNilai.addObject("krsInfo", krsInfo);
		lihatNilai.addObject("namaKelas", namaKelas);
		
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
		return new AjaxResponse("ok", "Komponen berhasil dihapus", null);
	}
	
	@RequestMapping(value = "/kelola_nilai/{idPemb}/simpan_komponen/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse simpanKomponenNilai(@RequestBody KomponenNilai[] listKomponen, @PathVariable UUID idPemb) {
		List<KomponenNilai> listKomponenNilai = new ArrayList<KomponenNilai>();
		for (KomponenNilai komponen : listKomponen) {
			Pemb foreignPemb = servicePemb.getById(idPemb);
			komponen.setPemb(foreignPemb);
			
			listKomponenNilai.add(komponen);
		}
		serviceKomp.simpanKomponen(listKomponenNilai);
		return new AjaxResponse("ok", "Komponen berhasil disimpan", null);
	}
	
	@RequestMapping(value = "/kelola_nilai/{idPemb}/simpan_nilai/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse simpanNilai(@RequestBody JSONNilai[] listNilaiJSON, @PathVariable UUID idPemb) {
		List<Nilai> listNilai = new ArrayList<Nilai>();
		for (JSONNilai nilaiJSON : listNilaiJSON) {
			Nilai nilai = new Nilai();
			nilai.setKrs(serviceKrs.getById(nilaiJSON.getIdKrs()));
			nilai.setKomponenNilai(serviceKomp.getById(nilaiJSON.getIdKomp()));
			nilai.setNilai(nilaiJSON.getNilai());
			
			listNilai.add(nilai);
		}
		serviceNilai.submitNilai(listNilai);
		List<Krs> listKrs = serviceKrs.getPesertaKelas(idPemb);
		for (Krs krs : listKrs) {
			krs.setNilaiAkhir(serviceNilai.getNilaiAkhir(krs));
			krs.setKonversiNilai(serviceKonversi.getByBatas(krs.getNilaiAkhir()));
			serviceKrs.updateNilaiAkhir(krs);
		}
		return new AjaxResponse("ok", "Nilai berhasil disimpan", null);
	}
	
	@RequestMapping(value = "/konversi_nilai/", method = RequestMethod.GET)
	public ModelAndView tampilkanKonversiNilai(Locale locale, Model model) {
		List<KonversiNilai> listKonversi = serviceKonversi.getKonversiNilai();
		ModelAndView daftarKonversi = new ModelAndView();
		daftarKonversi.setViewName("daftar_konversi_nilai");
		daftarKonversi.addObject("listKonversi", listKonversi);
		
		return daftarKonversi;
	}
	
	@RequestMapping(value = "/konversi_nilai/tambah_konversi/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse tambahKonversiNilai(@RequestBody KonversiNilai konversi) {
		List<KonversiNilai> listKonversi = serviceKonversi.getKonversiNilai();
		HashMap<String, Integer> hashHuruf = new HashMap<String, Integer>();
		HashMap<Double, Integer> hashNilai = new HashMap<Double, Integer>();
		HashMap<Double, Integer> hashBatas = new HashMap<Double, Integer>();
		
		boolean aKonversiUnik = true;
		String messageError = "Konversi gagal ditambah:\n";
		
		for (KonversiNilai konversiNilai : listKonversi) {
			Integer i;
			i = hashHuruf.get(konversiNilai.getHuruf());
			if(i == null)
				hashHuruf.put(konversiNilai.getHuruf(), 1);
			else
				hashHuruf.put(konversiNilai.getHuruf(), i + 1);
			
			i = hashNilai.get(konversiNilai.getNilaiHuruf());
			if(i == null)
				hashNilai.put(konversiNilai.getNilaiHuruf(), 1);
			else
				hashNilai.put(konversiNilai.getNilaiHuruf(), i + 1);
			
			i = hashBatas.get(konversiNilai.getBatasBawah());
			if(i == null)
				hashBatas.put(konversiNilai.getBatasBawah(), 1);
			else
				hashBatas.put(konversiNilai.getBatasBawah(), i + 1);
		}
		
		if(hashHuruf.get(konversi.getHuruf()) != null) {
			messageError += "Huruf harus unik\n";
			aKonversiUnik = false;
		}
		if(hashNilai.get(konversi.getNilaiHuruf()) != null) {
			messageError += "Nilai harus unik\n";
			aKonversiUnik = false;
		}
		if(hashBatas.get(konversi.getBatasBawah()) != null) {
			messageError += "Batas harus unik\n";
			aKonversiUnik = false;
		}
		
		if(aKonversiUnik) {
			UUID idKonversi = serviceKonversi.tambahKonversiNilai(konversi);
			return new AjaxResponse("ok", "Konversi berhasil ditambah", idKonversi);
		}
		else
			return new AjaxResponse("fail", messageError, null);
	}
	
	@RequestMapping(value = "/konversi_nilai/hapus_konversi/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse hapusKonversiNilai(@RequestBody UUID idKonversi) {
		serviceKonversi.hapusKonversiNilai(idKonversi);
		return new AjaxResponse("ok", "Konversi berhasil dihapus", null);
	}
	
	@RequestMapping(value = "/konversi_nilai/simpan_konversi/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse simpanKonversiNilai(@RequestBody KonversiNilai[] listKonversi) {
		HashMap<String, Integer> hashHuruf = new HashMap<String, Integer>();
		HashMap<Double, Integer> hashNilai = new HashMap<Double, Integer>();
		HashMap<Double, Integer> hashBatas = new HashMap<Double, Integer>();
		
		boolean aKonversiUnik = true;
		
		for (KonversiNilai konversiNilai : listKonversi) {
			Integer i;
			i = hashHuruf.get(konversiNilai.getHuruf());
			if(i == null)
				hashHuruf.put(konversiNilai.getHuruf(), 1);
			else {
				hashHuruf.put(konversiNilai.getHuruf(), i + 1);
				aKonversiUnik = false;
			}
			
			i = hashNilai.get(konversiNilai.getNilaiHuruf());
			if(i == null)
				hashNilai.put(konversiNilai.getNilaiHuruf(), 1);
			else {
				hashNilai.put(konversiNilai.getNilaiHuruf(), i + 1);
				aKonversiUnik = false;
			}
			
			i = hashBatas.get(konversiNilai.getBatasBawah());
			if(i == null)
				hashBatas.put(konversiNilai.getBatasBawah(), 1);
			else {
				hashBatas.put(konversiNilai.getBatasBawah(), i + 1);
				aKonversiUnik = false;
			}
		}
		
		if(aKonversiUnik == true) {
			serviceKonversi.simpanKonversiNilai(listKonversi);
			return new AjaxResponse("ok", "Konversi berhasil disimpan", null);
		}
		else {
			return new AjaxResponse("fail", "Masing-masing konversi harus unik", null);
		}
	}
}
