package com.siakad.modul_penilaian.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sia.main.domain.Menu;
import com.sia.main.domain.MenuPeran;
import com.sia.main.domain.Modul;
import com.sia.main.domain.Pd;
import com.sia.main.domain.Pengguna;
import com.sia.main.domain.PeranPengguna;
import com.sia.main.domain.Ptk;
import com.siakad.modul_penilaian.service.MenuPeranService;
import com.siakad.modul_penilaian.service.PenggunaService;
import com.siakad.modul_penilaian.service.PeranPenggunaService;

@Controller
public class ControllerBeranda extends ControllerSession {
	@Autowired
	private PenggunaService penggunaService;

	@Autowired
	private PeranPenggunaService peranPenggunaService;

	@Autowired
	private MenuPeranService menuPeranService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session, Locale locale, Model model) {
		ModelAndView mav = new ModelAndView();

		if(!isLogin(session)){ mav.setViewName("redirect:/login/");	return mav;}
		if(!hasMenu(session, "dashboard"))	{ mav.setViewName("redirect:/");return mav;}else{mav = addNavbar(session,mav);}
		Pengguna pengguna = (Pengguna)session.getAttribute("pengguna");
		Ptk ptk = null;
		Pd pd = null;
		if(session.getAttribute("pd")!=null)
		{
			pd = (Pd) session.getAttribute("pd");
		}else
		{
			ptk = (Ptk) session.getAttribute("ptk");
		}
		List<PeranPengguna> listPeranPengguna = (List<PeranPengguna>)session.getAttribute("listPeranPengguna");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		String workingDir = System.getProperty("user.dir");

		List<Modul> listModul = (List<Modul>)session.getAttribute("listModul");
		List<List<Menu>> listListMenu = (List<List<Menu>>)session.getAttribute("listListMenu");

		mav.setViewName("beranda");
		mav.addObject("serverTime", formattedDate);
		mav.addObject("directory", workingDir);
		mav.addObject("pengguna", pengguna);
		mav.addObject("ptk", ptk);
		mav.addObject("pd", pd);
		mav.addObject("listPeranPengguna", listPeranPengguna);
		mav.addObject("listModul", listModul);
		mav.addObject("listListMenu", listListMenu);

		return mav;
	}

	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public ModelAndView login(Locale locale, Model model) {

		ModelAndView mav = new ModelAndView();
		String errorMessage = model.asMap().get("errorMessage")==null?null:model.asMap().get("errorMessage").toString();

		mav.setViewName("login");
		mav.addObject("errorMessage",errorMessage);

		return mav;
	}

	private void setsession(HttpServletRequest request,PeranPengguna peranPengguna, Pengguna pengguna, Pd pd, Ptk ptk
			,List<Modul> listModul	,List<List<Menu>> listListMenu){
		request.getSession().setAttribute("pengguna", pengguna);
		request.getSession().setAttribute("peranPengguna", peranPengguna);
		request.getSession().setAttribute("pd", pd);
		request.getSession().setAttribute("ptk", ptk);
		request.getSession().setAttribute("listModul", listModul);
		request.getSession().setAttribute("listListMenu", listListMenu);
	}

	@RequestMapping(value = "/loginsubmit", method = RequestMethod.POST)
	public ModelAndView loginSubmit(HttpServletRequest request,Locale locale, @RequestParam("username") String username, @RequestParam("password") String password
			, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();

		List<Pengguna> listPengguna = penggunaService.get("username='"+username+"' and password = md5('"+password+"')");
		if(listPengguna.size()==0) 
		{
			String errorMessage ="Autentikasi Gagal";
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			mav.setViewName("redirect:/login/");
			return mav;
		}
		else
		{
			Pengguna pengguna = listPengguna.get(0);
			System.out.println(pengguna.getIdPengguna());
			List<PeranPengguna> listPeranPengguna = peranPenggunaService.get("pengguna.idPengguna ='"+pengguna.getIdPengguna()+"' ");
			if(listPeranPengguna.size()==0)
			{
				String errorMessage ="Anda tidak mempunyai hak akses";
				redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
				mav.setViewName("redirect:/login/");
				return mav;
			}
			if(listPeranPengguna.size()==1)
			{
				PeranPengguna peranPengguna = listPeranPengguna.get(0);
				Pd pd = pengguna.getPd();
				Ptk ptk = pengguna.getPtk();

				List<MenuPeran> listMenuPeran = menuPeranService.get("peran.idPeran='"+peranPengguna.getPeran().getIdPeran()+"'","modul.urutanModul asc, menu.namaMenu asc");
				List<Modul> listModul = new ArrayList<Modul>();
				List<List<Menu>> listListMenu = new ArrayList<List<Menu>>();
				for (MenuPeran menuPeran : listMenuPeran) {
					if(!listModul.contains(menuPeran.getMenu().getModul())) 
					{
						listModul.add(menuPeran.getMenu().getModul());
						List<Menu> listMenu = new ArrayList<Menu>();
						listMenu.add(menuPeran.getMenu());
						listListMenu.add(listMenu);
					}
					else
					{
						Integer position = listModul.indexOf(menuPeran.getMenu().getModul());
						listListMenu.get(position).add(menuPeran.getMenu());
					}
				}
				setsession(request, peranPengguna, pengguna, pd, ptk, listModul, listListMenu);

				mav.setViewName("redirect:/");
				return mav;
			}
			mav.addObject("listPeranPengguna", listPeranPengguna);
			mav.addObject("pengguna",pengguna);
			mav.setViewName("hak_akses");
			return mav;
		}
	}

	@RequestMapping(value = "/peransubmit", method = RequestMethod.POST)
	public ModelAndView peranSubmit(HttpServletRequest request,Locale locale, @RequestParam("idPengguna") String idPengguna, @RequestParam("idPeranPengguna") String idPeranPengguna
			, final RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();

		Pengguna pengguna = penggunaService.getById(UUID.fromString(idPengguna));
		Pd pd = pengguna.getPd();
		Ptk ptk = pengguna.getPtk();

		List<PeranPengguna> listPeranPengguna = peranPenggunaService.get("pengguna.idPengguna ='"+pengguna.getIdPengguna()+"' ");
		for (PeranPengguna peranPengguna : listPeranPengguna) {
			if(peranPengguna.getIdPeranPengguna().toString().equals(idPeranPengguna))
			{
				List<MenuPeran> listMenuPeran = menuPeranService.get("peran.idPeran='"+peranPengguna.getPeran().getIdPeran()+"'","modul.urutanModul asc, menu.namaMenu asc");
				List<Modul> listModul = new ArrayList<Modul>();
				List<List<Menu>> listListMenu = new ArrayList<List<Menu>>();
				for (MenuPeran menuPeran : listMenuPeran) {
					if(!listModul.contains(menuPeran.getMenu().getModul())) 
					{
						listModul.add(menuPeran.getMenu().getModul());
						List<Menu> listMenu = new ArrayList<Menu>();
						listMenu.add(menuPeran.getMenu());
						listListMenu.add(listMenu);
					}
					else
					{
						Integer position = listModul.indexOf(menuPeran.getMenu().getModul());
						listListMenu.get(position).add(menuPeran.getMenu());
					}
				}
				setsession(request, peranPengguna, pengguna, pd, ptk, listModul, listListMenu);

				mav.setViewName("redirect:/");
				return mav;
			}
		}

		String errorMessage ="Anda tidak mempunyai hak akses";
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		mav.setViewName("redirect:/login/");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,Locale locale) {
		ModelAndView mav = new ModelAndView();

		request.getSession().removeAttribute("pengguna");
		request.getSession().removeAttribute("peran");
		request.getSession().removeAttribute("pd");
		request.getSession().removeAttribute("ptk");
		request.getSession().removeAttribute("listModul");
		request.getSession().removeAttribute("listListMenu");

		mav.setViewName("redirect:/login/");
		return mav;
	}
}
