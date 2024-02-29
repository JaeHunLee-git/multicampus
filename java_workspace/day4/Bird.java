/*
모든 새들의 최상위 객체인 그냥 새를 정의한다
*/
class Bird{
	/*최상위 객체일 수록 */
	boolean hasWing=true; //모든 새는 날개가 있으므로..
	String color;

	public Bird(String color){
		this.color=color;
		System.out.println("난 부모 새");
	}

}
