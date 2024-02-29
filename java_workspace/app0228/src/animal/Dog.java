/*
[패키지를 사용하는 이유 ]  

1) .java 와 .class 파일을 함께 두지 않고 분리시켜 개발
2) 관련성 있는 클래스들은 같은 디렉토리끼리 묶어서 개발 
3) 클래스명 중복 문제를 피해갈 수 있다.
*/
package animal;

//패키지에 담겨진, 클래스를 외부에서 사용하게 하려면 반드시
//해당 클래스를 public 으로 오픈해야 한다
public class Dog{
	private String name="뽀미"; // default 접근제한자가 적용
	private int age=9;
	
	//변수를  public 으로 놓아도 되지만, 중요한 정보일 경우가 있으므로 
	//변수를 public 으로 놓는 개발방식은 거의 찾아볼수 없다..
	//따라서 소중한 변수를 private 외부의 접근을 막고, 오직 허용된 메서드를
	//통해서만 변수값을 제어할 수 있도록 하는 클래스 정의 기법을 가리켜 
	//캡슐화(은닉화) -encapsulation
	
	public String getName(){  //getter  reading
		return name;
	}
	
	public void setName(String name){ //setter  writing
		this.name=name;
	}

}
