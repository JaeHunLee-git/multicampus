package com.sds.mvcframerwork.blood.model;

//이 클래스의 목적: jsp안에 혈액형 판단에 대한 로직(모델) 코드를 두면 
//jsp(View)와 로직이 하나로 합쳐져 있기 때문에, 추후 유지보수 내용 상, 디자인을 변경해야 할 상황
//즉 극단적으로 기존의 디자인을 완전히 버려야 하는 상황이 발생하면, 로직도 함께 버려야 한다..
//즉 재사용성이 없다!!! (추후 중복 개발해야 함==시간이 든다 == 돈이 든다)
public class BloodManager {
	public String getAdvice(String blood) {
		String msg=null;
		
		if(blood.equals("A")){
			msg="세심하고 꼼꼼하게 일을 처리한다";
		}else if(blood.equals("B")){
			msg="귀가 얇지 않다";
		}else if(blood.equals("AB")){
			msg="다채롭다";
		}else if(blood.equals("O")){
			msg="사교적이고 긍정적이다";
		}
		return msg;
	}
}
