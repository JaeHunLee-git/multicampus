package com.sds.testapp.parsing.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler{
	boolean isItem;
	boolean isName;
	boolean isLati;
	boolean isLongi;
	boolean isAddr;
	List list;
	Hospital dto;
	
	@Override
	public void startDocument() throws SAXException {
		list = new ArrayList<Hospital>();
	}
	
	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		//System.out.print("<"+tag+">");
		
		if(tag.equals("item")) {
			isItem=true;
			dto = new Hospital(); //빈 dto 생성
		}else if(tag.equals("instit_nm")) {
			isName=true;			
		}else if(tag.equals("lat")) {
			isLati=true;
		}else if(tag.equals("lng")) {
			isLongi=true;
		}else if(tag.equals("street_nm_addr")) {
			isAddr=true;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		
		
		if(isName) {
			dto.setName(str);
		}else if(isLati) {
			dto.setLati(Double.parseDouble(str));
		}else if(isLongi) {
			dto.setLongi(Double.parseDouble(str));
		}else if(isAddr) {
			dto.setAddr(str);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
		//System.out.println("</"+tag+">");
		
		if(tag.equals("item")) {
			isItem=false;
			list.add(dto); // dto 넣기 
		}else if(tag.equals("instit_nm")) {
			isName=false;			
		}else if(tag.equals("lat")) {
			isLati=false;
		}else if(tag.equals("lng")) {
			isLongi=false;
		}else if(tag.equals("street_nm_addr")) {
			isAddr=false;
		}

	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("담겨진 총 수는 "+list.size());
		
		for(int i=0;i<list.size();i++) {
			Hospital vo=(Hospital)list.get(i);
			
			System.out.println(vo.getName());
		}
	}
}
