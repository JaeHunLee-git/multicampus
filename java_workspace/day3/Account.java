/*계좌를 표현한다
자바 클래스의 멤버변수는 즉 데이터는 사실 보호대상이다
따라서 자바에서는 멤벼변수나 메서드에 대해 접근제한자를 제공하여, 
개발자의 보안처리를 지원
 
public              <   protected                           < default                       <private
(완전 공개) (상속관계+같은 디렉토리의 객체끼리만) (같은 디렉토리(패키지)에서만) (변수를 가진 자 스스로만)
*/
class Account{
	String bank="신한은행"; //은행명
	private int balance=50000000;  //잔고
	private String account_num="123456789"; //계좌번호
	String master="홍길동";//계좌주

	//private 으로 변수를 막아놓으면, 선의의 업무처리까지 불가능하게 된다..
	//해결책)  메서드를 통해 간접적으로 접근할 수 있게 해주자 
	//      private 묶여진 변수의 읽게 할수 있도록 제공되는 메서드 정의 패턴을 가리켜 getter라 하며 
	//      변수를 변경할 수 있도록 제공되는 메서드 정의 패턴 setter라 한다..
	//메서드는 외부에서 접근을 할수 있어야 하므로,  public 으로 풀어두자
	
	//결론:  객체지향 언어에서 클래스의 중요 변수들을  private 묶어버리고, 이에 대한 접근방법은 
	//			public 메서드를 통해 사용을 제공하는 메서드 정의 기법을 캡슐화,은닉화(=encapsulation)
	public int getBalance(){ //getter  read
		return balance;
	}
	
	public void setBalance(int balance){ //setter write
		//매개변수와 멤버변수명이 같을때는, 멤버변수앞에 this를 명시해준다 
		//this란? 클래스로부터 탄생된 인스턴스가 자기 자신을 가리키는 레퍼런스 변수 
		this.balance=balance;
	}

	//account_num 에 대한 getter/setter 
	public String getAccount_num(){
		return account_num;
	}

	public void setAccount_num(String account_num){
		this.account_num=account_num
	}

}



