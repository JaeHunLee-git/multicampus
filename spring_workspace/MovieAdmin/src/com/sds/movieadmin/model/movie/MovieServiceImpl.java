package com.sds.movieadmin.model.movie;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.movieadmin.common.ExcelManager;
import com.sds.movieadmin.common.FileManager;
import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.exception.MovieException;
import com.sds.movieadmin.exception.UploadException;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private FileManager fileManager; //root-context.xml에서 이미 메모리에 올려놓음
	
	@Autowired
	private ExcelManager excelManager;//root-context.xml에서 이미 메모리에 올려놓음
	
	//영화진흥원 조회 서비스 객체 
	@Autowired
	private MovieApiService movieApiService;
	
	@Override
	public int selectCount() {
		return movieDAO.selectCount();
	}
	@Override
	public List selectAll(Map map) {
		List<Movie> siteMovieList = movieDAO.selectAll(map); //우리 사이트에 등록된 목록...이 List 에 들어있는 Movie에는 
		//  영화코드와 url만 존재함..따라서 영화코드를 이용하여 한건 가져오기 조회를 통해 비어있는 영화정보를 Movie dto 에 채워넣자
		
		for(Movie movie : siteMovieList) {
			//오픈 api 호출 객체에게   Movie DTO 맡겨서, 채워진 상태로 돌려받자!
			movieApiService.getMovie(movie);			
		}
		return siteMovieList;
	}
	
	//1건 등록
	public void regist(Movie movie) throws MovieException{
		movieDAO.insert(movie);
	}
	
	//등록업무는 파일저장+db insert가 모두 성공해야, 전체를 성공으로 처리하게됨
	//따라서 이 서비스에서 파일저장은 FileManager에게 일을 전담시키고, db insert는 
	//MovieDAO에게 업무를 전담시켜, 예외가 발생할 경우 모두 없엇떤 일로 무효화시켜야 함..
	@Transactional(propagation = Propagation.REQUIRED)
	public void registExcel(Movie movie) throws UploadException, MovieException{
		
		//1) 파일 서버에 저장 
		String excelPath = fileManager.save(movie); //저장은 이 메서드에서 진행되었으므로, 파일명 또한 알고 있다..따라서 그 파일명을 
		//반환하는 return 값을 메서드에 추가하자
		
		//2) 저장된 엑셀을 읽기 
		List<Movie> movieList = excelManager.getMovieData(excelPath);
		
		//3단계 : 기존의 레코드가 있다면, 지우자
		movieDAO.deleteAll();
		
		//4단계:  DAO에게 일시키기(엑셀의 영화 수만큼..)
		for(Movie dto : movieList) { //dto에는 엑셀의 정보가 들어있다..
			movieDAO.insert(dto); //대량 등록
		}
		
	}
	
	//모두 삭제 
	
}

