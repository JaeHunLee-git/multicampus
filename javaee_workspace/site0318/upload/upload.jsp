<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.io.File"%>
<%

    /* 클라이언트가 전송한 제목, 바이너리 파일을 넘겨받아 서버의 원하는 경로에 저장하자
        = 업로드
    */
    //클라이언트가 요청을 시도하면 , 서버측에서는 이 요청을 처리하기 위한 내장객체들이
    //메모리에 생성되는데, 요청내용과 관련된 객체는 request 이고, 응답정보를구성하기 위한
    //객체는 response 객체이다 
    //요청시 전송되어온, 파라미터에 인코딩을 지정해야 한글이 깨지지 않음
    request.setCharacterEncoding("utf-8");//전송되어오는 데이터가 깨지지 않도록 처리

    //클라이언트가 multipart/form-data 형식으로 데이터를 전송할 경우 , 더이상 문자열로
    //취급해서는 안됌!!! 따라서 아래와 같은 reqest.getParameter("") 문자열을 취급하는 
    //메서드 이므로, 복합된 데이터형식인 업로드 처리를 할수 없다..
    //결론: 업로드 컴포넌트를 이용해야 한다
    //String title = request.getParameter("title"); //html에서 전송한 변수 즉 파라미터명을 기재
    //out.print("클라이언트가 전송한 제목은 "+title);

    //바이너리 파일은 자체가 String 으로 처리될 수 없기 때문에, 또한 파일도 서버에 저장
    //하는 복잡한과정을 거쳐야 하므로, 별도의 업로드 컴포넌트를 이용해야 한다..
    //String photo = request.getParameter("photo");

    //첫번째 생성자: 
    String savePath="D:/javaee_workspace/site0318/data";

    //cos.jar는 생성자 호출 만으로도 업로드 완료
    int maxSize=1*1024*1024; //업로드 용량 제한 (byte)
    String encoding="utf-8";

    MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, encoding);

    //제목을 추출 , cos.jar 에서는 기존의 request의 메서드명을 유지함 
    String title = multi.getParameter("title");
    out.print("넘겨받은 제목은 "+title+"<br>");

    out.print("업로드 성공");

    //오라클에 제목과, 파일명 넣기 
    long time = System.currentTimeMillis();
    
    //확장자..는 방금 업로드된 파일명을 얻어와서 처리해야 함... 
    String photo = multi.getOriginalFileName("photo");
    //out.print(photo);
    
    //파일의 경로 에서 가장 마지막 점. 다음부터 , 문자열의 마지막까지 구하자 
    //결국  String 처리
    String ext= photo.substring(photo.lastIndexOf(".")+1, photo.length());

    out.print(ext);

    String filename= time+"."+ext;  // 23648923748.webp


    //업로드 컴포넌트에 의해 이미 서버에 저장되어진 파일을 File 클래스로 얻어와서 
    //이름을 새롭게 우리가 바꿔버리자!!
    
    File ori = new File(savePath+"/"+photo);//업로드 된 원본
    boolean flag=ori.renameTo(new File(savePath+"/"+filename)); //원본을 지정된 file 인스턴스로 바꾸기
    
    out.print(flag);

    //파일명과 제목을 오라클에 넣자!!
    /*
    JDBC 코드 작성 4단계 
    1) 드라이버 로드 
    2) 접속 
    3) 쿼리문 수행 
    4) 자원해제
    */

    Class.forName("oracle.jdbc.driver.OracleDriver");
    out.print("드라이버 로드 성공 <br>");

    String url="jdbc:oracle:thin:@localhost:1521:XE";
    String user="seshop";
    String pass="1234";

    Connection con=null; //접속 정보를가진객체, 추후 닫을때 사용가능
    PreparedStatement pstmt=null; //쿼리수행 객체 
    con = java.sql.DriverManager.getConnection(url, user, pass);

    if(con !=null){
        out.print("접속 성공<br>");
        String sql="insert into gallery(gallery_idx, title , filename)";
        sql+=" values(seq_gallery.nextval, ?,? )";
        //물음표는 바인드 변수를 의미, 바인드 변수값을 반드시 지정 후, 쿼리문 수행해야함 

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, title);  //첫번째 바인드 변수값 지정 
        pstmt.setString(2, filename); //두번째 바인드 변수값 지정 

        //모든 준비가 끝났으므로, 쿼리실행 
        int result = pstmt.executeUpdate(); //쿼리 수행 후 정수(반영된 레코드 수) 반환
        if(result <1){
            out.print("입력 실패 ㅜㅜ<br>");
        }else{
            out.print("입력 성공 ㅜㅜ<br>");
        }
    }

    if(pstmt!=null)pstmt.close();
    if(con!=null)con.close();
%>