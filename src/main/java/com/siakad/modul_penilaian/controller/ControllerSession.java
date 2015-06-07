package com.siakad.modul_penilaian.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.sia.main.domain.Menu;
import com.sia.main.domain.Modul;
import com.sia.main.domain.Pd;
import com.sia.main.domain.Pengguna;
import com.sia.main.domain.Ptk;

@Controller
public class ControllerSession {
	public boolean isLogin(HttpSession session){
		if(session.getAttribute("pengguna")==null) return false;
		return true;
	}
	
	public boolean hasMenu(HttpSession session, String menu){
		if(session.getAttribute("listListMenu")==null) return false;
		List<List<Menu>> listListMenu = (List<List<Menu>>) session.getAttribute("listListMenu");
		System.out.println(listListMenu.size());
		for (List<Menu> list : listListMenu) {
			for (Menu menu2 : list) {
				System.out.println(menu2.getNamaMenu());
				if(menu2.getNamaMenu().equals(menu)) return true;
			}
		}
		return false;
	}
	
	public ModelAndView addNavbar(HttpSession session,ModelAndView mav)
	{
		List<Modul> listModul = (List<Modul>)session.getAttribute("listModul");
		List<List<Menu>> listListMenu = (List<List<Menu>>)session.getAttribute("listListMenu");
		Pengguna pengguna = (Pengguna)session.getAttribute("pengguna");
		Ptk ptk = null;
		Pd pd = null;
		if(session.getAttribute("pd")!=null)
		{
			pd = (Pd) session.getAttribute("pd");
			System.out.println(pd.getNmPd()+"lololol");
		}else
		{
			ptk = (Ptk) session.getAttribute("ptk");
			System.out.println(ptk.getNmPtk()+"lololol");
		}
		mav.addObject("listModul", listModul);
		mav.addObject("listListMenu", listListMenu);
		mav.addObject("userPengguna", pengguna);
		mav.addObject("userPtk", ptk);
		mav.addObject("userPd", pd);
		return mav;
	}
}
