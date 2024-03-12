package db;

import java.sql.DriverManager; // java SE에 rt.jar 에 들어있슴 즉 이미 우리가 가지고 있슴
import java.sql.SQLException; // java SE에 rt.jar 에 들어있슴 즉 이미 우리가 가지고 있슴
import java.sql.Connection;

public class OracleTest{
	public static void main(String[] args) {
		/*오라클 뿐 아니라, 모든 DBMS를 연동하고 싶다면, 해당 DBMS 사에서 
			제공하는 드라이버를 이용해야 한다. 
		*/
		try{
			//드라이버 로드 
			Class.forName("oracle.jdbc.driver.OracleDriver"); //Class에 대한 정보를 가진 클래스를 Class 클래스라하는데, 
			//이 클래스의 static 즉 클래스 메서드 중에서 forName() 메서드를 이용하면, 
			//오라클 연동과 관련된 드라이버 클래스를 jvm에 메모리 영역에 Load 시킬 수 있다 
			//왜 이렇게 해야 하나? 위의 패키지 및 클래스는 javaSE에 기본으로 탑재된 jar가 
			//아니라, 방금 우리가 등록된 외부 라이브러리이기 때문.. 
			System.out.println("드라이버 로드 성공");

			//batman/1234 으로 접속 
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String id="batman";
			String pass="1234";

			Connection con=null;  //접속 성공 시 , 그 접속 정보를 가진 객체이다
			//따라서 만일 이 객체가 null 이라면 접속에 실패한 거다!!

			con = DriverManager.getConnection(url, id, pass); //접속 시도시 에러가 날 가능성이
			//있으므로, 예외 처리 강요됨
			if(con !=null){
				System.out.println("접속 성공!!");
			}
		}catch(ClassNotFoundException e){ 
			//해당 우려가 정말 현실화 되어, 에러가 발생한다면, jvm 에 의해 
			//예외 객체인 ClassNotFoundException 의 인스턴스가 생성되어, 
			//catch문의 매개변수 e로 전달된다!!
			System.out.println("드라이버를 확인해 주세요");
		}catch(SQLException e){
			System.out.println("접속에 문제가 발생했네요..");
		}

	}
}


