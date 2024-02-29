/* 자동차를 정의한다*/
class Car{
	/*변수, 함수*/
	
	String color="yellow";//색상이 노란색
	int price=200;			//가격이 200 
	
	//자바에서는  메서드  정의시 반환값 존재 여부를 반드시 알려줘야 한다
	//return 값이 없는 메서드는 호출자가 아무것도 가져갈 것이 없다고 하여 void
	public void setColor(){
		color="red";
	}

	public void setPrice(){
		price=500;
	}

	//return 된 값의 자료형을  void 자리에 작성해야 함	
	public int  getRandom(){
		return 5;
	}

	public boolean  getFlag(){
		return true;
	}
}


