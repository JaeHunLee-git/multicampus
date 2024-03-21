package com.sds.sessionapp.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionManager {
	
	//평문을 해시값을 변경하여 반환하는 메서드 정의 
	public static String getHashFromText(String text) {
		
		//String buff=""; //스트링은 상수로 취급되므로, 그 내용을 편집이나 변경할 수 없다!!
		//만일 String 을 대상으로 누적식을 하게 되면, 누적 수만큼 새로운 문자열 상수가 생성되어버린다..
		//해결책? 변경 가능만 문자열을 처리해주는 클래스 이용 StringBuilder, StringBuffer
		StringBuilder sb = new StringBuilder();
		
		//원하는 암호화 알고리즘 선택 
		try {
			
			byte[] bytes = text.getBytes();
			//알고리즘에 맡기기 전에, 잘게 쪼개진 데이터 확인해보기..
			/*
			for(int i=0;i<bytes.length;i++) {
				System.out.println(bytes[i]);
			}
			*/
			
			MessageDigest digest= MessageDigest.getInstance("SHA-256");
			
			byte[] result=digest.digest(bytes);
			
			
			for(int i=0;i<result.length;i++) {
				//System.out.println(result[i]);
				String hex = Integer.toHexString(0xff &result[i]); //16문자열로 변환
				//System.out.println(hex);
				if(hex.length()==1)sb.append("0");
				sb.append(hex); //누적
			}
			System.out.println(sb.toString());
			System.out.println(sb.toString().length());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return sb.toString(); //결과 반환
	}
	
	//임시적으로 테스트를 위한 main() 
	/*
	public static void main(String[] args) {
		getHashFromText("banana");
	}
	*/
}




