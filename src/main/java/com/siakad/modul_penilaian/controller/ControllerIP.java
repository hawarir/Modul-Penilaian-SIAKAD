package com.siakad.modul_penilaian.controller;

import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/lihat_ips/")
	public ModelAndView tampilkanIPS(Locale locale, Model model) {
		List<Ips> listIps = serviceIps.ambilSemuaIps();
		ModelAndView indeksPrestasi = new ModelAndView();
		indeksPrestasi.setViewName("daftar_ips");
		indeksPrestasi.addObject("listIps", listIps);
		
		return indeksPrestasi;
	}
	
	@RequestMapping("/lihat_ipk/")
	public ModelAndView tampilkanIPK(Locale locale, Model model) {
		List<Ipk> listIpk = serviceIpk.ambilSemuaIpk();
		ModelAndView indeksPrestasi = new ModelAndView();
		indeksPrestasi.setViewName("daftar_ipk");
		indeksPrestasi.addObject("listIpk", listIpk);
		
		return indeksPrestasi;
	}
	
	@RequestMapping("/update_ips/")
	public void updateIPS(Locale locale, Model model) {
		List<Pd> listAllPd = servicePd.getAll();
		TglSmt tglSmtAktif = serviceTglSmt.ambilTglSmtAktif();
		
		for (Pd pd : listAllPd) {
			double jumlahMutu = 0.0;
			int jumlahSks = 0;
			List<Krs> listKrsAktif = serviceKrs.getKrsAktifByPd(pd.getIdPd(), tglSmtAktif.getIdTglSmt());
			for (Krs krs : listKrsAktif) {
				jumlahMutu += serviceKrs.getNilaiMutu(krs.getIdKrs());
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
	
	@RequestMapping("/update_ipk/")
	public void updateIPk(Locale locale, Model model) {
		List<Pd> listAllPd = servicePd.getAll();
		
		for (Pd pd : listAllPd) {
			double jumlahMutu = 0.0;
			int jumlahSks = 0;
			List<Krs> listSemuaKrsPd = serviceKrs.getAllByPd(pd.getIdPd());
			for (Krs krs : listSemuaKrsPd) {
				jumlahMutu += serviceKrs.getNilaiMutu(krs.getIdKrs());
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
