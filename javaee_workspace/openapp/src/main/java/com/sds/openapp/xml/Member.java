package com.sds.openapp.xml;

import lombok.Data;
//이 객체는 사원 1명을 담기위한 용도의 클래스 즉 DTO로 정의하자 
//Lombok 라이브러리를 이용하면 , getter/setter 일일이 만들지 않아도 된다..

//jdk 1.5 ( 5 version) 부터 프로그래밍 언어 내에서 사용되는 주석을 지원하는데, 이러한 주석을 가리켜 
//어노테이션(Annotation)이라 하며,  @로 표기한다

@Data
public class Member {
	private int empno;
	private String ename;
	private int sal;
}
