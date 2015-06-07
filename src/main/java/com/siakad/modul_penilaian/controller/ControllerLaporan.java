package com.siakad.modul_penilaian.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sia.main.domain.Ipk;
import com.sia.main.domain.Ips;
import com.sia.main.domain.Krs;
import com.sia.main.domain.Pd;
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
	public ModelAndView tampilkanNilaiPerPeriode(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		if(!isLogin(session)){ mav.setViewName("redirect:/login/");	return mav;}
		if(!hasMenu(session, "Nilai Per Periode"))	{ mav.setViewName("redirect:/");return mav;}else{mav = addNavbar(session,mav);}
		
		Pd pd = (Pd) session.getAttribute("pd");
		
		List<Krs> daftarKrs = serviceKrs.ambilSemuaBerdasarkanPd(pd.getIdPd());
		Pd pesertaDidik = servicePd.ambilPd(pd.getIdPd());
		List<Ips> daftarIps = serviceIps.ambilBerdasarkanPd(pd.getIdPd());		
		
		mav.setViewName("laporan_nilai_per_periode");
		mav.addObject("daftarKrs", daftarKrs);
		mav.addObject("pd", pesertaDidik);
		mav.addObject("daftarIps", daftarIps);
		
		return mav;
	}
	
	@RequestMapping(value="/lihat_transkrip/", method = RequestMethod.GET)
	public ModelAndView tampilkanTranskrip(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		if(!isLogin(session)){ mav.setViewName("redirect:/login/");	return mav;}
		if(!hasMenu(session, "Transkrip Nilai"))	{ mav.setViewName("redirect:/");return mav;}else{mav = addNavbar(session,mav);}
		
		Pd pd = (Pd) session.getAttribute("pd");
		
		List<Krs> daftarKrs = serviceKrs.ambilKrsTerakhirBerdasarkanPd(pd.getIdPd());
		Pd pesertaDidik = servicePd.ambilPd(pd.getIdPd());
		Ipk ipk = serviceIpk.ambilIpkBerdasarkanPd(pd.getIdPd());
		
		mav.setViewName("laporan_transkrip");
		mav.addObject("daftarKrs", daftarKrs);
		mav.addObject("pd", pesertaDidik);
		mav.addObject("ipk", ipk);
		
		return mav;
	}
}
