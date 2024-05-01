package com.sds.movieadmin.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.exception.UploadException;

import lombok.Data;

//파일과 관련된 업무를 전담하는 객체 
@Component
public class FileManager {
	
	//jsp에서의 application 내장 객체는 ServletContext 자료형이고, 이 객체를 스프링이 메모리에서 관리하다가
	//@autowire 명시하면 자동으로 주입시켜준다..
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private String savePath;
	
	//확장자 반환 
	public String getExt(String path) {
		return path.substring(path.lastIndexOf(".")+1 , path.length());
	}
	
	//파일명 생성 
	public String createFilename(String filename) {
		//파일명 만들기 
		long time = System.currentTimeMillis(); //34234234234
		String ext = getExt(filename);
		
		return time+"."+ext;
	}
	
	//파일저장(웹서버에 저장)
	public String save(Movie movie) throws UploadException{
		//Movie DTO에서 MultipartFile 을 꺼내어, 하드디스크에 저장하자 
		MultipartFile file=movie.getFile(); //업로드된 파일이 저장된 객체 꺼내기
		
		String realPath=null;
		String newName=null;
		try {
			realPath = servletContext.getRealPath(savePath);
			System.out.println(realPath);
			newName = createFilename(file.getOriginalFilename());
			
			file.transferTo(new File(realPath+newName));
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패", e);
		}
		return realPath+newName;
	}
	
}
