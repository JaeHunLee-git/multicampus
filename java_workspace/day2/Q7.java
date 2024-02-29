class Q7{

	public static void main(String[] args) {
		System.out.println("당신인 호출시 넘긴 배열의 길이는 "+args.length );

		//두개의 숫자를 입력받아, 두수의 합을 구하는 코드를 완성하시오 
		int sum  = Integer.parseInt(args[0]) + Integer.parseInt(args[1]);
		System.out.println("입력하신 두수의 합은 "+sum);
	}

	//main(); // java.exe에 의해 호출, 즉 시스템이 호출..
}
