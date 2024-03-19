<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%
    /*
    클라이언트가 전송한 복합형 데이터(multipart)를 넘겨받아, 수정을 처리... 
    이미지가 넘어온 경우는 기존 파일 삭제 ,새롭게 등록 + db 업데이트 
    이미지가 넘어오지 않았다면, 즉 파일 업로드의 의사가 없기 때문에 db 만 업데이트
    */
    
    DiskFileItemFactory factory = new DiskFileItemFactory();
    int maxSize = 1*1024*1024;
    String savePath = "D:/javaee_workspace/site0318/data/";

    factory.setSizeThreshold(maxSize); //용량 제한 
    factory.setRepository(new File(savePath)); //저장 경로

    //업로드 처리 객체 생성 
    ServletFileUpload upload = new ServletFileUpload(factory); //설정 적용

    //업로드된 데이터 분석 (parse)
    List<FileItem> itemList = upload.parseRequest(request); //내장 객체를 인수로 넣어주면, 업로드 내용 분석 

    //리스트에 채워진 각 FileItem 들을 꺼내서 정보를 사용해보자 
    String title=null;
    String writer=null;
    String content=null;
    String filename=null;
    int gallery_idx=0;

    for(FileItem item  : itemList){
        if(item.isFormField()){//일반 텍스트 데이터와 (input type='text'라면..)
            if(item.getFieldName().equals("title")){//제목이라면...
                title = item.getString("utf-8");
            }else if(item.getFieldName().equals("writer")){//작성자라면...
                writer = item.getString("utf-8");
            }else if(item.getFieldName().equals("content")){//내용이라면...
                content = item.getString("utf-8");
            }else if(item.getFieldName().equals("filename")){//파일명이라면..
                filename=item.getString("utf-8");
            }else if(item.getFieldName().equals("gallery_idx")){//idx라면..
                gallery_idx=Integer.parseInt(item.getString("utf-8"));
            }
        }else{//바이너리 파일 데이터..(input type='file' 이라면...)
            //파일 업로드의 의지가 있다면, 즉 새로운 파일을 업로드한 경우라면..
            //기존 서버에 잇는 파일 물리적 삭제, 저 위의 지역변수 filename  변수값도 
            //새롭게 생성된 파일명으로 수정함..

            //out.print(item.getName().length()); 파일명 길이가 1이상이면 업로드 의지有
            if(item.getName().length() >0){//파일을 업로드 했다면...
                //1) 서버에 있는 기존 파일 지우기 
                File old = new File(savePath+filename); 
                boolean flag = old.delete(); //파일 삭제 

                //2) 새로운 파일명을 만들고,  filename 변수에 새로 생성된 이름으로 대체 
                long time = System.currentTimeMillis(); //45789032475
                String str = item.getName(); 
                String ext = str.substring(str.lastIndexOf(".")+1, str.length());
                filename = time+"."+ext; //기존 변수를 새론 파일명으로 대체 
                
                //파일저장
                item.write(new File(savePath+filename));
            }
        }
    }

    //수정 쿼리 
    //String sql="update gallery set title=? , writer=? , content=? , filename=?";
    //sql+="  where gallery_idx=내가지금보고있는글idx";

    String sql="update gallery set title='"+title+"', writer='"+writer+"', content='"+content+"', filename='"+filename+"'";
    sql+="  where gallery_idx="+gallery_idx;

    out.print(sql);

    Class.forName("oracle.jdbc.driver.OracleDriver");
    
    String url="jdbc:oracle:thin:@localhost:1521:XE";
    String user="seshop";
    String pass="1234";

    Connection con=null;
    PreparedStatement pstmt=null;

    con = DriverManager.getConnection(url, user, pass);

    pstmt = con.prepareStatement(sql); //쿼리 객체 생성 
    int result   = pstmt.executeUpdate(); //DML

    if(result >0){
        out.print("<script>");
        out.print("alert('수정성공');"); //
        out.print("location.href='/gallery/content.jsp?gallery_idx="+gallery_idx+"';"); //다시 content.jsp 재 요청
        out.print("</script>");
    }else{
        out.print("<script>");
        out.print("alert('수정실패');"); //
        out.print("history.back();"); //이전으로..즉 상세보기로 돌려버림..
        out.print("</script>");
    }
    
    if(pstmt!=null)pstmt.close();
    if(con!=null)con.close();
%>




