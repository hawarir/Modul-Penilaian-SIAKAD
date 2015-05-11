package com.siakad.modul_penilaian.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sia.main.domain.Kuisioner;
import com.siakad.modul_penilaian.service.KuisionerService;

@Controller
public class ControllerKuisioner {
	@Autowired
	private KuisionerService serviceKuisioner;
	
	@RequestMapping(value = "/kelola_kuisioner/", method = RequestMethod.GET)
	public ModelAndView tampilkanDaftarKuisioner(Locale locale, Model model) {
		List<Kuisioner> listKuisionerAktif = serviceKuisioner.getAllKuisioner();
		
		ModelAndView daftarKuisioner = new ModelAndView();
		daftarKuisioner.setViewName("daftar_kelola_kuisioner");
		daftarKuisioner.addObject("listKuisioner", listKuisionerAktif);
		
		return daftarKuisioner;
	}
}
