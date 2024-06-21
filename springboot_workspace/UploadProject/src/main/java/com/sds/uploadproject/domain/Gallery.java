package com.sds.uploadproject.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Gallery {
	private String title; //제목
	private MultipartFile file; //실제 파일을 담아놓은 객체
	private String filename; //파일명만 담는 변수
}
