package com.siakad.modul_penilaian.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerBeranda {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView tampilkanBeranda(Locale locale, Model model) {
		ModelAndView beranda = new ModelAndView();
		beranda.setViewName("beranda");
		
		return beranda;
	}
}
