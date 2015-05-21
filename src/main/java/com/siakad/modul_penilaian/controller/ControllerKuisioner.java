package com.siakad.modul_penilaian.controller;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sia.main.domain.Kuisioner;
import com.sia.main.domain.PertanyaanKuisioner;
import com.siakad.modul_penilaian.service.AjaxResponse;
import com.siakad.modul_penilaian.service.JSONPertanyaan;
import com.siakad.modul_penilaian.service.KuisionerService;
import com.siakad.modul_penilaian.service.PertanyaanKuisionerService;

@Controller
public class ControllerKuisioner {
	@Autowired
	private KuisionerService serviceKuisioner;
	
	@Autowired
	private PertanyaanKuisionerService servicePertanyaan;
	
	@RequestMapping(value = "/kelola_kuisioner/", method = RequestMethod.GET)
	public ModelAndView tampilkanDaftarKuisioner(Locale locale, Model model) {
		List<Kuisioner> listKuisionerAktif = serviceKuisioner.getAllKuisioner();
		
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
	
	@RequestMapping(value = "/kelola_kuisioner/tambah_pertanyaan/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse tambahPertanyaan(@RequestBody JSONPertanyaan pertanyaanJSON) {
		PertanyaanKuisioner pertanyaanKuisioner = new PertanyaanKuisioner();
		pertanyaanKuisioner.setPertanyaan(pertanyaanJSON.getPertanyaan());
		pertanyaanKuisioner.setaPertanyaanAktif(true);
		pertanyaanKuisioner.setKuisioner(serviceKuisioner.getById(pertanyaanJSON.getIdKuisioner()));
		
		UUID idPertanyaanBaru = servicePertanyaan.tambahPertanyaan(pertanyaanKuisioner);
		return new AjaxResponse("ok", "Pertanyaan berhasil ditambahkan", idPertanyaanBaru);
	}
	
	@RequestMapping(value = "/kelola_kuisioner/hapus_pertanyaan/", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse hapusPertanyaan(@RequestBody UUID idPertanyaan) {
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
			pertanyaan.setKuisioner(serviceKuisioner.getById(pertanyaanJSON.getIdKuisioner()));
			
			servicePertanyaan.simpanPertanyaan(pertanyaan);
		}
		
		return new AjaxResponse("ok", "Pertanyaan berhasil disimpan", null);
	}
}
