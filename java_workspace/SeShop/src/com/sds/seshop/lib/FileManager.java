package com.sds.seshop.lib;

//파일과 관련 한 유용한 기능들을 지원하는 나만의 클래스 
public class FileManager {
	
	public static String getExt(String filename) {
		int dotIndex = filename.lastIndexOf("."); //경로 중 가장 마지막 점
		
		String ext = filename.substring(dotIndex+1, filename.length()); //확장자
		System.out.println("확장자는 "+ext);
		
		return ext; //확장자 반환
	}
}
