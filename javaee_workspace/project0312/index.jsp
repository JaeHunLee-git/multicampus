<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>

<%
    //jsp는 자바의 문법을 따르므로,  변수 선언 시 String 으로 선언
    //오라클 접속 접속 

    //jsp 의 코드 영역은 서버에서 실행되어 버린다..따라서 원본 그대로를 
    //클라이언트에게 보내주지 않는다.. 즉 아래의 코드를 수행한 후, 클라이언트인
    //웹브라우저가 이해할 수 있는 형식인 html 형식으로 응답정보를 재구성하여 보낸다 
    String url="jdbc:oracle:thin:@localhost:1521:XE";
    String user="batman";
    String pass="1234";
    
    /*데이터 베이스 연동 4단계 
        1) 드라이버 로드 
        2) 접속 
        3) 쿼리문 날리기 
        4) 자원 해제 
    */
    
    //javaEE 는 javaSE 를 포함하기 때문에  STANDARD 코드가 가능
    Class.forName("oracle.jdbc.driver.OracleDriver");  //드라이버 로드 
    out.print("드라이버 로드 성공<br>");

    Connection con=null; //접속 후 그 정보를 가진 인터페이스 객체 
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    con = DriverManager.getConnection(url, user, pass); //접속 시도

    if(con ==null){
        out.print("접속 실패<br>");
    }else{
        out.print("접속 성공<br>");

        String sql="select * from car";
        pstmt=con.prepareStatement(sql); //쿼리수행 객체 

        //쿼리실행 
        rs=null; //결과 집합인 레코드를 담는 객체 
        rs = pstmt.executeQuery(); //select문 일 경우 그 결과를 가져온다...
        
        //rs가 최초에는 아무런 레코드도 가리키고 있지 않는다. 즉 커서가 꼭대기로 올라와있다.
        //따라서 필요에 따라 커서를 원하는 위치 두고, 그 위치에 해당하는 레코드를 가져오자 
        
        out.print("<table width=\"100%\" border=\"1px\">");

        while(true){
            boolean flag = rs.next(); //한칸 전진 후, 레코드가 있다면 true, 아니면 false 가 반환 
            if(flag==false)break; //루프 멈출때는 break 문 쓴다 
            
            out.print("<tr>");
            //out.print(rs.getInt("car_idx")+", "+rs.getString("name")+","+rs.getInt("price")+"<br>");
            
            out.print("<td>"+ rs.getInt("car_idx") +"</td>");                    
            out.print("<td>"+ rs.getString("name") +"</td>");                    
            out.print("<td>"+ rs.getInt("price") +"</td>");                    
            out.print("</tr>");
        }
        out.print("</table>");
    }
    
    //더이상 쓰지 않는 자원 해제 
    if(rs!=null)rs.close();
    if(pstmt!=null)pstmt.close();
    if(con!=null)con.close();
%>