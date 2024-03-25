package com.sds.testapp.parsing.xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

public class XMLLoader {
	public static URI getXMLURI() throws IOException {
		String serviceKey="TPK6sq5VdCOFrijK99CmJHQCEVer9GwK4sxLvP6ED6dBExrBc6FO298QjQadJsw7C4sDZ8yBXJfsYZ/VT6LG0A==";
		
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/MedicInstitService/MedicalInstitInfo"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="+serviceKey); /* Service Key */
		//urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "="+ URLEncoder.encode("xml", "UTF-8")); /* JSON방식으로 호출 시 파라미터 resultType=json 입력 */
		//urlBuilder.append("&" + URLEncoder.encode("instit_nm", "UTF-8") + "="+ URLEncoder.encode("동아대학교병원", "UTF-8")); /* 의료기관명 */
		//urlBuilder.append("&" + URLEncoder.encode("instit_kind", "UTF-8") + "="+ URLEncoder.encode("상급종합병원", "UTF-8")); /* 기관분류 */
		
		URL url = new URL(urlBuilder.toString());
		
		/*
		 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		 * conn.setRequestMethod("GET"); conn.setRequestProperty("Content-type",
		 * "application/json"); System.out.println("Response code: " +
		 * conn.getResponseCode()); BufferedReader rd; if (conn.getResponseCode() >= 200
		 * && conn.getResponseCode() <= 300) { rd = new BufferedReader(new
		 * InputStreamReader(conn.getInputStream())); } else { rd = new
		 * BufferedReader(new InputStreamReader(conn.getErrorStream())); } StringBuilder
		 * sb = new StringBuilder(); String line; while ((line = rd.readLine()) != null)
		 * { sb.append(line); } rd.close(); conn.disconnect();
		 * 
		 */
		URI uri = null;
		
		try {
			uri = url.toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return uri;
	}
}