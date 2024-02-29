/*변수와 자료형*/
class DataType2{

    public static void main(String[] args){
        /*
        문자 : char 2 byte 차지함
        숫자 : 실수, 정수
         - 실수 : float(4) < double(8) 
         - 정수 : byte(1) < short(2) < int(4) < long(8)
        논리값 : boolean (1)
        */
        byte b=127; //이 byte 형에는 256 종의 경우의 숫자를 넣을수 있다..
		
		//자바에서는 모든 실수에 특별한 표시를 하지 않으면 디폴트값은 double 간주
		//따라서 float 자료형을 사용하고 싶다면, 소수점끝에 f 를 표기한다
		float x=0.96f; //소수점이므로 실수이다
        System.out.println(x);

		//논리값 
		boolean k=true;

		//문자 자료형 주의) 문자열이 아님 

		char m='Y'; //철자 하나만을 담을 수 있다
		System.out.println(m);


    }

}