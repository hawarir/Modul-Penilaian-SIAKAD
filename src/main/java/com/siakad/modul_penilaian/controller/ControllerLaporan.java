package com.siakad.modul_penilaian.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sia.main.domain.Ipk;
import com.sia.main.domain.Ips;
import com.sia.main.domain.Krs;
import com.sia.main.domain.Pd;
import com.sia.main.domain.Ptk;
import com.siakad.modul_penilaian.service.IpkService;
import com.siakad.modul_penilaian.service.IpsService;
import com.siakad.modul_penilaian.service.KrsService;
import com.siakad.modul_penilaian.service.PdService;

@Controller
public class ControllerLaporan extends ControllerSession{
	@Autowired
	private KrsService serviceKrs;
	
	@Autowired
	private PdService servicePd;
	
	@Autowired
	private IpkService serviceIpk;
	
	@Autowired
	private IpsService serviceIps;
	
	@RequestMapping(value="/lihat_nilai_periode/", method = RequestMethod.GET)
	public ModelAndView tampilkanDaftarNilaiPerPeriode(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		if(!isLogin(session)){ mav.setViewName("redirect:/login/");	return mav;}
		if(!hasMenu(session, "Nilai Per Periode"))	{ mav.setViewName("redirect:/");return mav;}else{mav = addNavbar(session,mav);}
		
		Pd pd = null;
		List<Pd> daftarPd = new ArrayList<Pd>();
		if(session.getAttribute("pd")!=null) {
			pd = (Pd) session.getAttribute("pd");
			daftarPd.add(pd);
		}
		else {
			daftarPd.addAll(servicePd.ambilSemuaPd());
		}
		
		mav.setViewName("daftar_laporan_per_pd");
		mav.addObject("daftarPd", daftarPd);
		return mav;
	}
	
	@RequestMapping(value="/lihat_nilai_periode/", method = RequestMethod.POST)
	public ModelAndView tampilkanNilaiPerPeriode(@RequestParam("idPd") UUID idPd) {
		ModelAndView mav = new ModelAndView();
		
		List<Krs> daftarKrs = serviceKrs.ambilSemuaBerdasarkanPd(idPd);
		Pd pesertaDidik = servicePd.ambilPd(idPd);
		List<Ips> daftarIps = serviceIps.ambilBerdasarkanPd(idPd);		
		
		mav.setViewName("laporan_nilai_per_periode");
		mav.addObject("daftarKrs", daftarKrs);
		mav.addObject("pd", pesertaDidik);
		mav.addObject("daftarIps", daftarIps);
		
		return mav;
	}
	
	@RequestMapping(value="/lihat_transkrip/", method = RequestMethod.GET)
	public ModelAndView tampilkanDaftarTranskrip(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		if(!isLogin(session)){ mav.setViewName("redirect:/login/");	return mav;}
		if(!hasMenu(session, "Nilai Per Periode"))	{ mav.setViewName("redirect:/");return mav;}else{mav = addNavbar(session,mav);}
		
		Pd pd = null;
		List<Pd> daftarPd = new ArrayList<Pd>();
		if(session.getAttribute("pd")!=null) {
			pd = (Pd) session.getAttribute("pd");
			daftarPd.add(pd);
		}
		else {
			daftarPd.addAll(servicePd.ambilSemuaPd());
		}
		
		mav.setViewName("daftar_laporan_per_pd");
		mav.addObject("daftarPd", daftarPd);
		return mav;
	}
	
	@RequestMapping(value="/lihat_transkrip/", method = RequestMethod.POST)
	public ModelAndView tampilkanTranskrip(@RequestParam("idPd") UUID idPd) {
		ModelAndView mav = new ModelAndView();
		
		List<Krs> daftarKrs = serviceKrs.ambilKrsTerakhirBerdasarkanPd(idPd);
		Pd pesertaDidik = servicePd.ambilPd(idPd);
		Ipk ipk = serviceIpk.ambilIpkBerdasarkanPd(idPd);
		
		mav.setViewName("laporan_transkrip");
		mav.addObject("daftarKrs", daftarKrs);
		mav.addObject("pd", pesertaDidik);
		mav.addObject("ipk", ipk);
		
		return mav;
	}
}
