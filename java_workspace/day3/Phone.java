class Phone {

	int memory=300; //A
	int price; //B
	
	public void call(){
		int n=0; //C
		System.out.println("nÀÇ °ªÀº "+n); //D
	}

	public static void main(String[] args) {
		Phone p = new Phone();
		p.call();
	}
}
