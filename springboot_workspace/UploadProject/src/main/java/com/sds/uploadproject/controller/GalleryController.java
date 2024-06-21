package com.sds.uploadproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sds.uploadproject.domain.Gallery;
import com.sds.uploadproject.model.gallery.GalleryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	//업로드 폼 요청 처리 
	@GetMapping("/gallery/registform")
	public String getRegistForm() {
		return "gallery/regist";
	}
	
	//업로드 요청 처리 
	@PostMapping("/gallery/regist")
	public String regist(Gallery gallery, Model model) {
		log.debug("업로드 요청 받음");
		
		galleryService.save(gallery); //여기서 만일 에러가 난다면 GlobalExceptionHandler 가 처리
		model.addAttribute("gallery", gallery);
		
		return "gallery/result";
	}
	
}









