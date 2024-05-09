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
import com.sds.movieadmin.domain.MovieDoc;
import com.sds.movieadmin.exception.MovieException;
import com.sds.movieadmin.exception.UploadException;
import com.sds.movieadmin.model.mongo.MongoMovieDAO;

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
	
	
	@Autowired
	private MongoMovieDAO mongoMovieDAO;
	
	
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
		movieDAO.deleteAll(); //기존의 mysql의 모든 데이터 지우기 
										
		mongoMovieDAO.delete();//몽고DB 컬렉션의 document 들도 모두 지우기
		
		//4단계:  DAO에게 일시키기(엑셀의 영화 수만큼..)
		for(Movie dto : movieList) { //dto에는 엑셀의 정보가 들어있다..
			movieDAO.insert(dto); //대량 등록 (selectKey에 의해 movie_idx가 채워져있게 됨)
			
			//몽고db에 넣을 데이터를 MovieDoc에 채우기
			MovieDoc doc = new MovieDoc();
			doc.setMovie_idx(dto.getMovie_idx());
			
			Movie result = movieApiService.getMovie(dto); //네트워크를 통해 영화진흥원의 정보를 가져온다..			
			doc.setMovieNm(result.getMovieNm());
			
			//리스트를 꺼내서 배열로 옮겨담자 
			String[] genres = new String[result.getGenres().size()];
			for(int i=0;i<genres.length;i++) {
				genres[i] = result.getGenres().get(i).getGenreNm();
			}
			doc.setGenres(genres);//배열 대입
			
			String[] directors = new String[result.getDirectors().size()];
			for(int i=0;i<directors.length;i++) {
				directors[i] = result.getDirectors().get(i).getPeopleNm();
			}
			doc.setDirectors(directors);//배열 대입
			
			String[] actors = new String[result.getActors().size()];
			for(int i=0;i<actors.length;i++) {
				actors[i] = result.getActors().get(i).getPeopleNm();
			}
			doc.setActors(actors);//배열 대입
			
			String[] nations = new String[result.getNations().size()];
			for(int i=0;i<nations.length;i++) {
				nations[i] = result.getNations().get(i).getNationNm();
			}
			doc.setNations(nations);//배열 대입
			
			//몽고  db insert 
			mongoMovieDAO.insert(doc);
			System.out.println("몽고 db 에  한건 등록");
		}
		
	}
	
	//모두 삭제 
	
}

