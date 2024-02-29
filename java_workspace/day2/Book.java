class Book{
	int price=300;

	public static void main(String[] args){
		//아직 Book의 인스턴스를 만든적도 없는데, 클래스에 붙어있는
		//price 변수를 쓸수가 없는 단계이다...new 한적이 없다.
		//System.out.println(price);	

		Book b = new Book();
		System.out.println(b.price);
	}
}