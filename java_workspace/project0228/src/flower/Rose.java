package flower; //이 클래스는 bin 밑에 flower로 존재할것임
						// 즉 컴파일되면, ~~bin/flower 패키지안에 Rose.class 두겠다
public class Rose{
	String color="red"; //default  접근제한자가 적용됨(무조건 같은 패키지내에 있는)
	//클래스만 허용한다(주의- 상속관계의 자식이 다른 패키지에 있을 경우 접근 불가)
	private String name="장미";	 //현재 클래스내의 메서드만 접근가능
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
}
