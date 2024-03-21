package com.sds.sessionapp.common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	String host="smtp.gmail.com"; //구글  smpt  메일 주소
	String user="multicam202402@gmail.com"; //지금 사용중인 구글의 이메일
	//String password="dslh qheh sotc koab"; //앱 비밀번호
	String password="hnhx lgwe myjl pytn";
	

	//설정 정보 모으기 
	Properties props=new Properties(); //java.util 의 객체중 Map의 자식 객체 
	
	//메일 발송 메서드 
	public void send(String name, String to) {   //to는 메일 받게 될 상대방 주소
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // java 1.8 이하는 이 설정도 추가 
		
		//구글로부터 인증받기
		//java.mail.Session
		Session session=Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		//인증을 받았다면, 구글의 메일 서버를 사용할 수 있다..java로...
		//메일의 내용을 구성하기 위한 객체인 MimeMessage 객체 사용해보자 
		MimeMessage message=new MimeMessage(session);
		
		try {
			//받는 사람 설정
			message.addRecipient(Message.RecipientType.TO , new InternetAddress(to));
			
			//보내는 사람 
			message.setFrom(new InternetAddress(user));
			
			//보낼 제목 
			message.setSubject(name+"님 회원가입을 축하드립니다");
			
			//보낼 내용 
			message.setContent("<h1>저희  사이트</h1>에 가입을 축하드려요", "text/html;charset=utf-8");
			
			//메일 발송 
			Transport.send(message);
			
			System.out.println("메일 발송 성공");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}

	/*
	public static void main(String[] args) {
		MailService ms = new MailService();
		ms.send("민진호", "zino1187@naver.com");

	}
	 */
}
