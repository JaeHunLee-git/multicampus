package com.sds.uploadproject.model.gallery;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sds.uploadproject.domain.Gallery;
import com.sds.uploadproject.exception.GalleryException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GalleryService {
	@Value("${upload.location}")
	private String uploadLocation;
	
	@Autowired
	private ResourceLoader resourceLoader; //클래스패스를 기준으로 경로를 처리할 수 있는 객체
	
	//저장 처리 
	public void save(Gallery gallery) throws GalleryException{
		MultipartFile file = gallery.getFile();
		
		log.debug("title is "+gallery.getTitle());
		log.debug("original filename is "+file.getOriginalFilename());	
		
		//스프링부트에서 파일을 저장할 경로는 클래스패스를 기준으로 처리해야 한다.. 
		try {
			File directory = resourceLoader.getResource(uploadLocation).getFile();
			log.debug("파일을 저장할 경로는 "+directory.getAbsolutePath());
			
			Path path =Paths.get(directory.getAbsolutePath());
			Path savePath = path.resolve(file.getOriginalFilename());		
			Files.copy(file.getInputStream(), savePath);
			
			log.debug("savePath = "+savePath.toString());
			
			gallery.setFilename(file.getOriginalFilename());//파일명
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new GalleryException("업로드 실패", e);
		}
		
		
	}
}




