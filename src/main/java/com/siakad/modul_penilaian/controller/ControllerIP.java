package com.siakad.modul_penilaian.controller;

import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sia.main.domain.Ipk;
import com.sia.main.domain.Ips;
import com.sia.main.domain.Krs;
import com.sia.main.domain.Pd;
import com.sia.main.domain.TglSmt;
import com.siakad.modul_penilaian.service.IpkService;
import com.siakad.modul_penilaian.service.IpsService;
import com.siakad.modul_penilaian.service.KrsService;
import com.siakad.modul_penilaian.service.PdService;
import com.siakad.modul_penilaian.service.PembService;
import com.siakad.modul_penilaian.service.TglSmtService;

@Controller
public class ControllerIP {
	@Autowired
	private PdService servicePd;
	
	@Autowired
	private TglSmtService serviceTglSmt;
	
	@Autowired
	private PembService servicePemb;
	
	@Autowired
	private KrsService serviceKrs;
	
	@Autowired
	private IpsService serviceIps;
	
	@Autowired
	private IpkService serviceIpk;
	
	@RequestMapping(value = "/lihat_ips/", method = RequestMethod.GET)
	public ModelAndView tampilkanIPS() {
		List<Ips> listIps = serviceIps.ambilSemuaIps();
		ModelAndView indeksPrestasi = new ModelAndView();
		indeksPrestasi.setViewName("daftar_ips");
		indeksPrestasi.addObject("listIps", listIps);
		
		return indeksPrestasi;
	}
	
	@RequestMapping(value = "/lihat_ipk/", method = RequestMethod.GET)
	public ModelAndView tampilkanIPK() {
		List<Ipk> listIpk = serviceIpk.ambilSemuaIpk();
		ModelAndView indeksPrestasi = new ModelAndView();
		indeksPrestasi.setViewName("daftar_ipk");
		indeksPrestasi.addObject("listIpk", listIpk);
		
		return indeksPrestasi;
	}
	
	@RequestMapping(value = "/update_ip/", method = RequestMethod.GET)
	public ModelAndView tampilkanLamanUpdate() {
		ModelAndView lamanUpdate = new ModelAndView();
		lamanUpdate.setViewName("update_ip");
		
		return lamanUpdate;
	}
	
	@RequestMapping(value = "/update_ips/", method = RequestMethod.GET)
	public void updateIPS() {
		List<Pd> listAllPd = servicePd.ambilSemuaPd();
		TglSmt tglSmtAktif = serviceTglSmt.ambilTglSmtAktif();
		
		for (Pd pd : listAllPd) {
			double jumlahMutu = 0.0;
			int jumlahSks = 0;
			List<Krs> listKrsAktif = serviceKrs.ambilKrsAktifBerdasarkanPd(pd.getIdPd(), tglSmtAktif.getIdTglSmt());
			for (Krs krs : listKrsAktif) {
				jumlahMutu += serviceKrs.ambilNilaiMutu(krs.getIdKrs());
				jumlahSks += krs.getPemb().getMk().getJumlahSKS();
			}
			double nilaiIps = jumlahMutu/jumlahSks;
			
			Ips ips = new Ips();
			ips.setNilaiIps(nilaiIps);
			ips.setPd(pd);
			ips.setTglSmt(tglSmtAktif);
			ips.setTglBuatIps(LocalDateTime.now());
			
			serviceIps.masukkanIps(ips);
		}
	}
	
	@RequestMapping(value = "/update_ipk/", method = RequestMethod.GET)
	public void updateIPk() {
		List<Pd> listAllPd = servicePd.ambilSemuaPd();
		
		for (Pd pd : listAllPd) {
			double jumlahMutu = 0.0;
			int jumlahSks = 0;
			List<Krs> listSemuaKrsPd = serviceKrs.ambilSemuaBerdasarkanPd(pd.getIdPd());
			for (Krs krs : listSemuaKrsPd) {
				jumlahMutu += serviceKrs.ambilNilaiMutu(krs.getIdKrs());
				jumlahSks += krs.getPemb().getMk().getJumlahSKS();
			}
			double nilaiIpk = jumlahMutu/jumlahSks;
			
			Ipk ipk = new Ipk();
			ipk.setNilaiIpk(nilaiIpk);
			ipk.setPd(pd);
			
			serviceIpk.masukkanIpk(ipk);
		}
	}
}
