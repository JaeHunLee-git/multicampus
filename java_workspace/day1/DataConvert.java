class DataConvert{
	
	public static void main(String[] args){
		short s1 = 3;
		short s2=4;
		
		/*
		전통적으로 컴퓨터 시스템이 4바이트 인 32비트에 최적화되어 있었으므로
		아직까지도 프로그래밍 언어에서 연산 수행시 32비트가 가장 빠르다.
		따라서 java 에서는 무조건이 아닌 int 보다 작은 숫자형에 한해서만
		즉 byte, short  한해서는, 연산 수행직전 자동 int형으로의 형변환이 발생
		byte  < short < int < long 
		*/

		//첫번째 해결책
		int result = s1 + s2;
		
		//손실을 감안해서라도, short으로 변환하길 원하면 
		//강제형변환 연산자인 cast 연산자를 사용하자
		short result2 = (short)(s1 + s2);
	}
}