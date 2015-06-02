package com.siakad.modul_penilaian.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ControllerFile {
	
	@RequestMapping(value = "/kelola_nilai/unggah_file/", method = RequestMethod.POST)
	public void unggahFile(@RequestParam("file") File file) {
		if(file != null) {
			try {
				// byte[] bytes = file.getBytes();
				
				// Membuat directory untuk menyimpan file
				String rootPath = System.getProperty("catalina.home");
				System.out.println(rootPath);
				/*File dir = new File(rootPath + File.separator + "tmpFiles");
				if(!dir.exists())
					dir.mkdirs();
				
				// Buat file di server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getName());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();*/
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
