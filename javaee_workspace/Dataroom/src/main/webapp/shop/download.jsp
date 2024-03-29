<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="org.apache.poi.ss.usermodel.Cell"%>
<%@page import="org.apache.poi.ss.usermodel.Row"%>
<%@page import="org.apache.poi.ss.usermodel.Sheet"%>
<%@page import="org.apache.poi.xssf.usermodel.XSSFWorkbook"%>
<%@page import="org.apache.poi.ss.usermodel.Workbook"%>
<%@page import="com.sds.dataroom.shop.TopCategory"%>
<%@page import="java.util.List"%>
<%@page import="com.sds.dataroom.shop.TopCategoryDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	TopCategoryDAO dao = new TopCategoryDAO();
%>
<%
	//Topcategory 테이블의 레코드들을 가져와서 엑셀에 반영한 후, 해당 액셀파일을 
	//파일 다운로드 처리해주자 
	List<TopCategory> list = dao.selectAll();
	
	//엑셀정보를 구성한다..(아직 파일이 정해지지 않았다..메모리상에서 처리)
	Workbook workbook = new XSSFWorkbook(); //빈 엑셀 생성 	
	Sheet sheet = workbook.createSheet("topcategory"); //시트 생성
	
	//Row를 List 수만큼 만들자
	for(int i=0;i<list.size();i++){
		TopCategory dto = list.get(i);
		
		Row row = sheet.createRow(i);
		Cell idxCell  = row.createCell(0); //topcategory_idx
		Cell nameCell = row.createCell(1);//topname
		
		//셀에 값 부여하기
		idxCell.setCellValue(dto.getTopcategory_idx());
		nameCell.setCellValue(dto.getTopname());
	}
	
	//위의 메모리상의 엑셀을 파일로 존재시킨 후, 첨부파일로 처리하자 
	String realPath = application.getRealPath("/data/top.xlsx");
	FileOutputStream fos  = new FileOutputStream(realPath);
	workbook.write(fos); //메모리상의 엑셀을 실제 파일에 반영
	workbook.close(); 
	
	//다운로드 헤더 구성 등...
	File file = new File(realPath);
	FileInputStream fis=new FileInputStream(file);
	
	String mimeType = application.getMimeType(realPath);
	out.print("당신이 선택한 파일의 마임타입은 "+mimeType);
	
	//표준을 벗어난 알수없는형식의 마임타입의 경우엔 getMimeType() 가  null 을 반환함 
	if(mimeType==null){ //일반적으로 바이너리 파일과 알수없는 형식의 마임타입 
		mimeType="application/octet-stream";		
	}
	response.setContentType(mimeType);
	
	//응답헤더 구성하기	
	response.setHeader("Content-Disposition", "attachment; filename=\"top.xlsx\"");
	
	//다운 받게 될 파일의 용량 
	response.setContentLength((int)file.length()); //필수 아님
	
	//빨대꽂은 입력스트림으로 데이터를 읽어마시면서, 클라이언트에게 출력 
	//자료실에서 다루는 파일들은 , 바이너리 파일 이므로 바이트 기반의 출력스트림으로 처리..
	OutputStream os=response.getOutputStream();
	
	int data=-1;
	
	while(true){
		data = fis.read(); //1byte 읽기
		if(data==-1)break;
		os.write(data);
	}
	fis.close();
	os.close();

	
%>










