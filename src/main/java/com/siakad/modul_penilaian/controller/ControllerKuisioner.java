package com.siakad.modul_penilaian.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sia.main.domain.Krs;
import com.sia.main.domain.Kuisioner;
import com.sia.main.domain.NilaiKuisioner;
import com.sia.main.domain.PertanyaanKuisioner;
import com.sia.main.domain.StatusKuisioner;
import com.sia.main.domain.TglSmt;
import com.siakad.modul_penilaian.service.AjaxResponse;
import com.siakad.modul_penilaian.service.JSONNilaiKuisioner;
import com.siakad.modul_penilaian.service.JSONPertanyaan;
import com.siakad.modul_penilaian.service.KrsService;
import com.siakad.modul_penilaian.service.KuisionerService;
import com.siakad.modul_penilaian.service.NilaiKuisionerService;
import com.siakad.modul_penilaian.service.PertanyaanKuisionerService;
import com.siakad.modul_penilaian.service.StatusKuisionerService;
import com.siakad.modul_penilaian.service.TglSmtService;

@Controller
public class ControllerKuisioner {
	@Autowired
	private KuisionerService serviceKuisioner;
	
	@Autowired
	private PertanyaanKuisionerService servicePertanyaan;
	
	@Autowired
	private NilaiKuisionerService serviceNilaiKuisioner;
	
	@Autowired
	private KrsService serviceKrs;
	
	@Autowired
	private StatusKuisionerService serviceStatus;
	
	@Autowired
	private TglSmtService serviceTglSmt;
	
	@RequestMapping(value = "/isi_kuisioner/", method = RequestMethod.GET)
	public ModelAndView tampilkanDaftarIsiKuisioner() {
		UUID idPd = UUID.fromString("56f893a7-8988-444d-9e03-aa94832c88b0"); // hardcode id_pd Hawari Rahman
		TglSmt tglSmtAktif = serviceTglSmt.ambilTglSmtAktif();
		List<Krs> daftarKrs = serviceKrs.ambilKrsAktifBerdasarkanPd(idPd, tglSmtAktif.getIdTglSmt());
		List<Kuisioner> daftarKuisioner = serviceKuisioner.ambilSemuaKuisioner();
		List<Boolean> daftarStatus = new ArrayList<Boolean>();
		
		for (Krs krs : daftarKrs) {
			for (Kuisioner kuisioner : daftarKuisioner) {
				daftarStatus.add(serviceStatus.apakahKuisionerTerisi(krs.getIdKrs(), kuisioner.getIdKuisioner()));
			}
		}
		
		System.out.println(daftarStatus);
		
		ModelAndView daftarIsiKuisioner = new ModelAndView();
		daftarIsiKuisioner.setViewName("daftar_kuisioner");
		daftarIsiKuisioner.addObject("daftarKrs", daftarKrs);
		daftarIsiKuisioner.addObject("daftarKuisioner", daftarKuisioner);
		daftarIsiKuisioner.addObject("daftarStatus", daftarStatus);
		
		return daftarIsiKuisioner;
	}
	
	@RequestMapping(value = "/isi_kuisioner/", method = RequestMethod.POST)
	public ModelAndView tampilkanIsiKuisioner(@RequestParam("idKrs") UUID idKrs, @RequestParam("idKuisioner") UUID idKuisioner) {
		Kuisioner kuisioner = serviceKuisioner.ambilKuisioner(idKuisioner);
		List<PertanyaanKuisioner> daftarPertanyaan = servicePertanyaan.ambilBerdasarKuisioner(idKuisioner);
		
		ModelAndView isiKuisioner = new ModelAndView();
		isiKuisioner.setViewName("isi_kuisioner");
		isiKuisioner.addObject("kuisioner", kuisioner);
		isiKuisioner.addObject("daftarPertanyaan", daftarPertanyaan);
		isiKuisioner.addObject("idKrs", idKrs);
		
		return isiKuisioner;
	}
	
	@RequestMapping(value = "/isi_kuisioner/{idKrs}/{idKuisioner}/simpan_kuisioner/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse submitKuisioner(@RequestBody JSONNilaiKuisioner[] daftarNilai, @PathVariable("idKrs") UUID idKrs, @PathVariable("idKuisioner") UUID idKuisioner) {
		double totalNilai = 0;
		for (JSONNilaiKuisioner nilai : daftarNilai) {
			NilaiKuisioner nilaiKuisioner = new NilaiKuisioner();
			nilaiKuisioner.setKrs(serviceKrs.ambilKrs(idKrs));
			nilaiKuisioner.setPertanyaanKuisioner(servicePertanyaan.getById(nilai.getIdPertanyaan()));
			nilaiKuisioner.setNilaiPertanyaan(nilai.getNilaiPertanyaan());
			
			totalNilai += nilai.getNilaiPertanyaan();
			serviceNilaiKuisioner.simpanNilaiKuisioner(nilaiKuisioner);
		}
		
		double nilaiAkhir = totalNilai / (double) daftarNilai.length;
		
		StatusKuisioner status = new StatusKuisioner();
		status.setaKuisionerTerisi(true);
		status.setKrs(serviceKrs.ambilKrs(idKrs));
		status.setKuisioner(serviceKuisioner.ambilKuisioner(idKuisioner));
		status.setNilaiKuisioner(nilaiAkhir);
		serviceStatus.masukkanStatus(status);
		
		return new AjaxResponse("ok", "Kuisioner berhasil disimpan", null);
	}
	
	@RequestMapping(value = "/kelola_kuisioner/", method = RequestMethod.GET)
	public ModelAndView tampilkanDaftarKelolaKuisioner(Locale locale, Model model) {
		List<Kuisioner> listKuisionerAktif = serviceKuisioner.ambilSemuaKuisioner();
		
		ModelAndView daftarKuisioner = new ModelAndView();
		daftarKuisioner.setViewName("kelola_kuisioner");
		daftarKuisioner.addObject("listKuisioner", listKuisionerAktif);
		
		return daftarKuisioner;
	}
	
	@RequestMapping(value = "/kelola_kuisioner/tambah_kuisioner/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse tambahKuisioner(@RequestBody Kuisioner kuisionerBaru) {
		UUID idKuisionerBaru = serviceKuisioner.tambahKuisioner(kuisionerBaru);
		return new AjaxResponse("ok", "Kuisioner berhasil ditambahkan", idKuisionerBaru);
	}
	
	@RequestMapping(value = "/kelola_kuisioner/hapus_kuisioner/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse hapusKuisioner(@RequestParam("idKuisioner") UUID idKuisioner) {
		serviceKuisioner.hapusKuisioner(idKuisioner);
		return new AjaxResponse("ok", "Kuisioner berhasil dihapus", null);
	}
	
	@RequestMapping(value = "/kelola_kuisioner/ambil_pertanyaan/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse ambilSemuaPertanyaan(@RequestParam("idKuisioner") UUID idKuisioner) {
		List<PertanyaanKuisioner> listPertanyaan = servicePertanyaan.ambilBerdasarKuisioner(idKuisioner);
		return new AjaxResponse("ok", "Pertanyaan dari Kuisioner", listPertanyaan);
	}
	
	@RequestMapping(value = "/kelola_kuisioner/tambah_pertanyaan/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse tambahPertanyaan(@RequestBody JSONPertanyaan pertanyaanJSON) {
		PertanyaanKuisioner pertanyaanKuisioner = new PertanyaanKuisioner();
		pertanyaanKuisioner.setPertanyaan(pertanyaanJSON.getPertanyaan());
		pertanyaanKuisioner.setaPertanyaanAktif(true);
		pertanyaanKuisioner.setKuisioner(serviceKuisioner.ambilKuisioner(pertanyaanJSON.getIdKuisioner()));
		
		UUID idPertanyaanBaru = servicePertanyaan.tambahPertanyaan(pertanyaanKuisioner);
		return new AjaxResponse("ok", "Pertanyaan berhasil ditambahkan", idPertanyaanBaru);
	}
	
	@RequestMapping(value = "/kelola_kuisioner/hapus_pertanyaan/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse hapusPertanyaan(@RequestParam("idPertanyaan") UUID idPertanyaan) {
		servicePertanyaan.hapusPertanyaan(idPertanyaan);
		return new AjaxResponse("ok", "Pertanyaan berhasil dihapus", null);
	}
	
	@RequestMapping(value = "/kelola_kuisioner/simpan_pertanyaan/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse simpanPertanyaan(@RequestBody JSONPertanyaan[] listPertanyaanJSON) {
		PertanyaanKuisioner pertanyaan = new PertanyaanKuisioner();
		for (JSONPertanyaan pertanyaanJSON : listPertanyaanJSON) {
			pertanyaan.setIdPertanyaanKuisioner(pertanyaanJSON.getIdPertanyaan());
			pertanyaan.setPertanyaan(pertanyaanJSON.getPertanyaan());
			pertanyaan.setaPertanyaanAktif(true);
			pertanyaan.setKuisioner(serviceKuisioner.ambilKuisioner(pertanyaanJSON.getIdKuisioner()));
			
			servicePertanyaan.simpanPertanyaan(pertanyaan);
		}
		
		return new AjaxResponse("ok", "Pertanyaan berhasil disimpan", null);
	}
	
	@RequestMapping(value = "/update_kuisioner/", method = RequestMethod.GET)
	public void updateRekapKuisioner() {
		TglSmt tglSmtAktif = serviceTglSmt.ambilTglSmtAktif();
		List<Krs> daftarKrsAktif = serviceKrs.ambilKrsAktif(tglSmtAktif.getIdTglSmt());
		
		for (Krs krs : daftarKrsAktif) {
			
		}
	}
}
