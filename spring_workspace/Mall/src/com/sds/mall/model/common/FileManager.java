package com.sds.mall.model.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sds.mall.domain.Product;
import com.sds.mall.exception.UploadException;

//오직 파일과 관련된 업무만을 처리하는 모델 객체
//FileManager가 스프링의 빈으로 등록되기만 한다면, 아래의 ServletContext를 
//자동 주입 받을 수 있다.
@Component   // <component scan=/> 의 스캔 대상이 되어 인스턴스를 스프링이 올려준다
public class FileManager {
	
	@Autowired
	private String savePath;
	
	@Autowired
	private ServletContext servletContext;
	
	//서버에 지정된 경로에 파일로 저장시키기 
	//throws의 의미: 이 메서드를 호출한 者에게, 예외의 처리를 전가시킴(긍정적 관점에서는 예외 전달 )
	public void save(Product product) throws UploadException{ 
		MultipartFile photo = product.getPhoto();
		
		try {
			String realPath=servletContext.getRealPath(savePath);//외부에서 주입받은 루트를 기준으로 한경로를 대상으로
			//물리적 경로를 얻자
			//d:/worspace~~~~~/resources/product_img/날짜.jpg
			String filename = photo.getOriginalFilename();
			
			String ext = getExt(filename); //확장자 구하기 
			
			String newName=createFilename(ext); //최종 파일명..
			
			photo.transferTo(new File(realPath+newName));
			
			//새롭게 생성된 파일명을 Product DTO에 심어놓자, Product DTO는 컨트롤러부터 시작하여 모델 영역에서 주소값이
			//공유되고 있다.. 왜?? 메서드의 매개변수로 계속 전달하고 있기 때문에..
			product.setFilename(newName);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			//catch문을 작성하면서, 로그 출력만 했다면 외부의 계층에서는 에러가 났음을 알수가 없다..
			//따라서 여기서 발생한 에러의 정보를 밖으로 전달하자 
			//전달하기 위해서는 여기서 일부러 개발자가 원하는 예외를 일으켜야 한다.. throw 로...
			throw new UploadException("업로드에 실패하였습니다.\n문제가 계속되면 업로드하실 파일의 크기를 확인해 주세요", e);
		}
	}
	
	//확장자 추출하기 
	public String getExt(String path) {
		return path.substring(path.lastIndexOf(".")+1, path.length());
	}
	
	//파일명 생성하기(현재 밀리세컨드 이용), 확장자를 넘겨주면 완성된 파일명을 반환하는 메서드 
	public String createFilename(String ext) {
		long time = System.currentTimeMillis();
		
		return time+"."+ext;
	}
	
	
}









