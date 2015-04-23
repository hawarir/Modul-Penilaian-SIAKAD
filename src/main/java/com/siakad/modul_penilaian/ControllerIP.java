package com.siakad.modul_penilaian;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.siakad.model.Mahasiswa;

@Controller
public class ControllerIP {
	
	@RequestMapping("/lihat_ip/kumulatif/")
	public ModelAndView tampilkanIPKumulatif(Locale locale, Model model) {
		List<Mahasiswa> mhs = Mahasiswa.generateMahasiswa();
		ModelAndView indeksPrestasi = new ModelAndView();
		indeksPrestasi.setViewName("indeks_prestasi");
		indeksPrestasi.addObject("listMhs", mhs);
		
		return indeksPrestasi;
	}
}
