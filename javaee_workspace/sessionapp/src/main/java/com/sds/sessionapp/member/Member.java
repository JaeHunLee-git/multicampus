package com.sds.sessionapp.member;

//클래스 중, 로직이 아닌 오직 데이터만을 담기위한 클래스를 가리켜 DTO(Data Transfer Object)
//데이터 전달 객체 ,  DTO인스턴스 1개 에는 한 사람에 대한 정보를 담는다..
//만일 10사람이라면 dTO 인스턴스 10개를 만들면 된다.. 참고 DTO 용어?? 어플리케이션 설계 분야 용어
public class Member {
	private String id;
	private String pass;
	private String name;
	private String email;
	private int receive;
	private String regdate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getReceive() {
		return receive;
	}
	public void setReceive(int receive) {
		this.receive = receive;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	
	
}
