package com.siakad.modul_penilaian.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerIP {
	
	@RequestMapping("/lihat_ip/")
	public ModelAndView tampilkanIP(Locale locale, Model model) {
		ModelAndView indeksPrestasi = new ModelAndView();
		indeksPrestasi.setViewName("indeks_prestasi");
		
		return indeksPrestasi;
	}
	
	@RequestMapping("/update_ip/")
	public void updateIP(Locale locale, Model model) {
		
	}
}
