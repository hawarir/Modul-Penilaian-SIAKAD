package com.siakad.modul_penilaian.controller;

import java.util.List;
import java.util.UUID;

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
public class ControllerLaporan {
	@Autowired
	private KrsService serviceKrs;
	
	@Autowired
	private PdService servicePd;
	
	@Autowired
	private IpkService serviceIpk;
	
	@Autowired
	private IpsService serviceIps;
	
	@RequestMapping(value="/lihat_nilai_periode/", method = RequestMethod.GET)
	public ModelAndView tampilkanNilaiPerPeriode() {
		UUID idPd = UUID.fromString("56f893a7-8988-444d-9e03-aa94832c88b0");
		List<Krs> daftarKrs = serviceKrs.ambilSemuaBerdasarkanPd(idPd);
		Pd pesertaDidik = servicePd.ambilPd(idPd);
		List<Ips> daftarIps = serviceIps.ambilBerdasarkanPd(idPd);
		
		ModelAndView lamanNilaiPeriode = new ModelAndView();
		lamanNilaiPeriode.setViewName("laporan_nilai_per_periode");
		lamanNilaiPeriode.addObject("daftarKrs", daftarKrs);
		lamanNilaiPeriode.addObject("pd", pesertaDidik);
		lamanNilaiPeriode.addObject("daftarIps", daftarIps);
		
		return lamanNilaiPeriode;
	}
	
	@RequestMapping(value="/lihat_transkrip/", method = RequestMethod.GET)
	public ModelAndView tampilkanTranskrip() {
		UUID idPd = UUID.fromString("56f893a7-8988-444d-9e03-aa94832c88b0"); // hardcode id_pd Hawari Rahman
		List<Krs> daftarKrs = serviceKrs.ambilKrsTerakhirBerdasarkanPd(idPd);
		Pd pesertaDidik = servicePd.ambilPd(idPd);
		Ipk ipk = serviceIpk.ambilIpkBerdasarkanPd(idPd);
		
		ModelAndView lamanTranskrip = new ModelAndView();
		lamanTranskrip.setViewName("laporan_transkrip");
		lamanTranskrip.addObject("daftarKrs", daftarKrs);
		lamanTranskrip.addObject("pd", pesertaDidik);
		lamanTranskrip.addObject("ipk", ipk);
		
		return lamanTranskrip;
	}
}
