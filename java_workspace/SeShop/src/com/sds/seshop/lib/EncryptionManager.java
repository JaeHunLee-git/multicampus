package com.sds.seshop.lib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * 암호화 방법의 유형 
 * 
 * 1) 양방향(복호화가 가능한 암호화) 
 * 		대칭
 * 		비대칭
 * 
 * 2) 단방향 
 * 		해시함수  - 일단 암호화 시키면, 원본을 절대 추측할 수 없다. 
 * 		해시함수 특징 - 	1) 어떠한 값을 입력해도 언제나 동일한 길의 암호를 반환해 줌
 * 							2) 어떠한 값을 입력해도 중복되지 않음    
 * */
public class EncryptionManager {
	
	public static String getConvertedData(String password){
		//String password="minzino"; //암호화의 대상이 되는 데이터 
		
		String hash=""; //최종적인 암호화 결과를 담게될 스트링
		
		//암호화 알고리즘을 처리하는 객체 MessageDigest 객체
		try {
			MessageDigest digest= MessageDigest.getInstance("SHA-256"); //64자 해시값 만들기
			System.out.println("다이제스트 생성 성공");
			
			byte[] bytes = password.getBytes(); //스트링을 바이트 배열로 변환
			//필수 아님, 구경하기 위함..
			/*
			for(int i=0;i<bytes.length;i++) {
				System.out.println(bytes[i]);
			}
			*/
			
			bytes = digest.digest(bytes); //암호화시킬 대상 데이터를 byte[] 형 배열로 넣어줘야 함
			System.out.println("다이제스트가 처리한 이후의 배열의 길이 "+ bytes.length);
			
			
			for(int i=0;i<bytes.length;i++) {
				//System.out.println(bytes[i]);
				String hex = Integer.toHexString(0xff & bytes[i]);
				System.out.println(hex);
				if(hex.length()==1) {
					hex+="0";
				}
				hash +=hex;
			}
			
			System.out.println(hash.length());	
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("알고리즘 이름을 확인해 주세요");
		}
		
		return hash;
	}

}
