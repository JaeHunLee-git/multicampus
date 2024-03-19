<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="java.io.File"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%
    //클라이언트가 전송한 텍스트와 바이너리가 합쳐진 상태이므로, 기존의 request객체만으로
    //는 업로드를 처리할 수 없다.. 왜? request.getParameter() 텍스트 데이터만 처리가능..
    //결론) 업로드 컴포넌트를 이용해야 함 (Apache Fileupload )

    //업로드와 관련된 설정 정보를 담당하는 객체 
    DiskFileItemFactory factory=new DiskFileItemFactory();

    //용량 제한 
    int maxSize = 1*1024*1024; //1M byte
    factory.setSizeThreshold(maxSize);

    //저장 경로
    String savePath="D:/javaee_workspace/site0318/data";
    factory.setRepository(new File(savePath));


    //업로드를 처리하는 객체인 ServletFileUpload 객체를 생성하면서 설정 정보 객체를 
    //생성자의 매개변수로 넘기자 
    ServletFileUpload upload=new ServletFileUpload(factory);

    //업로드 처리 (업로드 된 내용 분석)
    //reqeust 내장객체를 매개변수로 넣으면 업로드된 요청을 분석하여, html의 모든 
    //컴포넌트들을 FileItem 라는 객체로 받게 됨..
    List<FileItem> itemList=upload.parseRequest(request); 

    //각 아이템에 담겨진 데이터를 끄집어 내자 
    String title=null;
    String writer=null;
    String content=null;
    String filename=null;

    for( FileItem item : itemList){
        if(item.isFormField()){ //텍스트 박스라면...
            out.print(item.getFieldName()+"의 값은"+item.getString("utf-8")+"<br>"); //text 박스의 값을 추출하는 메서드..
            if(item.getFieldName().equals("title")){ //넘겨받은 컴포넌트가 제목일때...
                title = item.getString("utf-8");
            }else if(item.getFieldName().equals("writer")){//넘겨받은 컴포넌트가 작성자일때...
                writer = item.getString("utf-8");
            }else if(item.getFieldName().equals("content")){//넘겨받은 컴포넌트가 내용일때...
                content = item.getString("utf-8");
            }
        }else{
            //반복문 내에 섞여 있는 FileItem 중, 바이너리 파일을 골라내어 , 서버에 저장처리..
            out.print("파일명은 "+item.getName()+"<br>"); //파일의 이름을 추출 
            long time = System.currentTimeMillis();
            String ext = item.getName().substring(item.getName().lastIndexOf(".")+1 , item.getName().length());

            filename=time+"."+ext; //새롭게  파일명 만듦
            item.write(new File(savePath+"/"+filename)); //서버의 하드에 저장!!
        }        
    }

    //오라클 연동 
    Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 로드 
    out.print("드라이버 로드 <br>");

    String url="jdbc:oracle:thin:@localhost:1521:XE";
    String user="seshop";
    String pass="1234";

    Connection con=null;
    PreparedStatement pstmt=null;

    con = DriverManager.getConnection(url, user, pass);

    if(con !=null){
        out.print("접속 성공<br>");

        String sql="insert into gallery(gallery_idx, title , writer, content, filename)";
        sql+=" values(seq_gallery.nextval, ?,?,?,? )";

        pstmt = con.prepareStatement(sql);//쿼리문 준비객체 생성 
        
        //쿼리문 수행전 반드시 바인드 변수값이 할당되어야 한다 
        pstmt.setString(1, title);
        pstmt.setString(2, writer);
        pstmt.setString(3, content);
        pstmt.setString(4, filename);

        //쿼리 수행 후, 영향 받은 레코드 수 받기 
        int result = pstmt.executeUpdate();//DML
        
        if(result>0){
            out.print("<script>");
            out.print("alert('등록성공');");
            out.print("location.href='/gallery/list.jsp';"); //리스트 요청 
            out.print("</script>");
        }else{
            out.print("<script>");
            out.print("alert('등록실패');");
            out.print("history.back();"); //이전 페이지 보여주기 
            out.print("</script>");
        }
    }
    if(pstmt!=null)pstmt.close();
    if(con!=null)con.close();
%>