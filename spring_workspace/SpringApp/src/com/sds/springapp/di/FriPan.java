package com.sds.springapp.di;


//요리 도구 중 프라이팬을 정의한다
public class FriPan implements Pan{
	public void boil() {
		System.out.println("요리를 불로 데워요");		
	}
}
