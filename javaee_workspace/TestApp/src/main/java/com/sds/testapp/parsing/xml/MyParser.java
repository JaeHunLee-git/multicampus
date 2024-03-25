package com.sds.testapp.parsing.xml;

import java.io.IOException;
import java.net.URI;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class MyParser {
	
	public static void main(String[] args) {
		URI uri=null;
		
		try {
			uri = XMLLoader.getXMLURI();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		SAXParserFactory factory=SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(uri.toString(), new MyHandler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
