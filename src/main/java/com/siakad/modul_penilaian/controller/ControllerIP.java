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

import com.sia.main.domain.Ips;
import com.sia.main.domain.Krs;
import com.sia.main.domain.Pd;
import com.sia.main.domain.TglSmt;
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
	
	@RequestMapping("/lihat_ip/")
	public ModelAndView tampilkanIP(Locale locale, Model model) {
		ModelAndView indeksPrestasi = new ModelAndView();
		indeksPrestasi.setViewName("indeks_prestasi");
		
		return indeksPrestasi;
	}
	
	@RequestMapping("/update_ip/")
	public void updateIP(Locale locale, Model model) {
		List<Pd> listAllPd = servicePd.getAll(); //ambil semua mahasiswa yang masih aktif
		TglSmt tglSmtAktif = serviceTglSmt.getTglSmtAktif(); //ambil semester yang masih aktif sekarang
		
		//ambil nilai indeks prestasi pada masing-masing pelajaran peserta didik
		//mutu adalah hasi perkalian antara nilai huruf dan jumlah sks
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
			
			System.out.println(ips.getPd().getNmPd() + ": " + ips.getNilaiIps() + " pada " + ips.getTglSmt().getSmt().getNmSmt());
			
			serviceIps.masukkanIps(ips);
		}
	}
}
