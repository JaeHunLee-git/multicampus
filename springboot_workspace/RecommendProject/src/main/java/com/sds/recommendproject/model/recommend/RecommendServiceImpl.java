package com.sds.recommendproject.model.recommend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.recommendproject.domain.CommentsDoc;
import com.sds.recommendproject.domain.Movie;
import com.sds.recommendproject.domain.MovieDoc;
import com.sds.recommendproject.exception.RecommendException;
import com.sds.recommendproject.model.comments.CommentsDocDAO;
import com.sds.recommendproject.model.movie.MovieApiService;
import com.sds.recommendproject.model.movie.MovieDAO;

import kr.co.shineware.nlp.komoran.core.Komoran;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService{
	
	@Autowired
	private CommentsDocDAO commentsDAO;

	@Autowired
	private MovieDocDAO movieDocDAO;

	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private Komoran komoran;

	@Autowired
	private MovieApiService movieApiService;
	
	double minScore=1.0;
	
	public List getList(int member_idx) throws RecommendException{
		
		List<CommentsDoc> commentsList = commentsDAO.selectAllByMember(member_idx);
		
		
		/*---------------------------------------------------------------
		1) 영화평 리스트를 Mahout 전용 List 로 전환
			CommentsDoc  를 Preference 객체로 전환
			
		2) 영화메타 정보 Map에 담기
		---------------------------------------------------------------*/
		List<Preference> preferences = new ArrayList<>();
		Map<Long, MovieDoc> metadataMap = new HashMap(); // 영화들의 추가 정보를 담을 맵
		
        for (int i = 0; i < commentsList.size(); i++) {
        	CommentsDoc commentsDoc = commentsList.get(i);
        	
        	preferences.add(new GenericPreference(commentsDoc.getMember_idx(), commentsDoc.getMovie_idx(), commentsDoc.getScore()));
        	
        	MovieDoc movieDoc = movieDocDAO.select(commentsDoc.getMovie_idx());
        	metadataMap.put((long)movieDoc.getMovie_idx(), movieDoc); // 맵에 영화들을 담아둔다
        	log.debug("사용자가 평가한 영화는 movie_idx = "+ movieDoc.getMovie_idx()+", 영화명 = "+movieDoc.getMovieNm());
        }	
        

		/*---------------------------------------------------------------
		영화평 리스트에서 유저가 긍정 평가한 영화 추려내기
		---------------------------------------------------------------*/
		List<MovieDoc> likedMovies = preferences.stream()
				.filter(p -> p.getValue() >= minScore) // 지정한 평점 이상인 영화 MovieDoc중 지정한 평점 이상인 것만 골라낸다
				.map(p -> metadataMap.get(p.getItemID())) //골라낸 영화의 id를 이용하여 metadataMap에서 영화를 가져온 후 
																				//map() 그 결과를 List에 담아놓는다 
				.collect(Collectors.toList()); //임시로 담겨진 List를 Collectors.toList() 에 의해 List에 추가하고, 
														//최종 연산자 collect() 에 의해 반환된다
		
		//테스트
		/*
		for(MovieDoc  movie: likedMovies) {
			log.debug("your liked movidNm is "+movie.getMovieNm());			
		}
		*/
		likedMovies.stream().forEach(m->log.debug(m.getMovieNm()));

		/*---------------------------------------------------------------
		홈페이지의 모든 영화 Map으로 전환하기
		---------------------------------------------------------------*/
		List<MovieDoc> movieList = movieDocDAO.selectAll(null);//홈페이지의 모든 영화 List		
		Map<Long, MovieDoc> candidateMap = new HashMap(); //홈페이지 모든 영화를 Map형태로 전환하기 위함
		/*
		for(MovieDoc movie : movieList) {
			candidateMap.put((long)movie.getMovie_idx(), movie);
		}
		아래처럼 stream 이용하여 줄여써 서보자
		*/
		movieList.stream().forEach(m-> candidateMap.put((long)m.getMovie_idx(), m));
		//log.debug("홈페이지의 모든 영화 Map의 길이는 "+candidateMap.size()+", movieList size 는 "+movieList.size());
		

		/*---------------------------------------------------------------
		추천 영화 목록 만들기
		 - 긍정 평가한 영화와 홈페이지의 모든 영화를 비교하기 
		---------------------------------------------------------------*/
		Map<Long, Double> calcualtedMap = new HashMap<>();
		
		for (MovieDoc movie : likedMovies) { //긍정 평가한 영화목록
			for (Map.Entry<Long, MovieDoc> entry : candidateMap.entrySet()) {
				MovieDoc candidate = entry.getValue();
				
				double similarityScore = calculate(movie, candidate);
				
				log.debug(movie.getMovieNm()+"과 "+candidate.getMovieNm()+" 을 비교한  점수는 "+similarityScore);
				
				calcualtedMap.put(entry.getKey() , calcualtedMap.getOrDefault(entry.getKey(), 0.0) + similarityScore);
			}
		}
		
		//log.debug("추천할 영화수는 "+recommendedMovies.size());
		
		// 추천 점수가 높은 순으로 정렬하여 출력
		
		List<Movie> resultList = new ArrayList(); // Map.Entry<Long,  Double> 이 저장
		
		resultList = calcualtedMap.entrySet().stream()
					.sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
					//.filter(entry-> entry.getValue() > maxScore)
					.limit(likedMovies.size())
					.map(entry -> 
						movieApiService.getMovie(movieDAO.select(movieDocDAO.select((int)(long)entry.getKey()).getMovie_idx()))
					) // 영화 ID를 사용하여 MovieDTO 조회
					.collect(Collectors.toList());
					/*
					.forEach(e -> 
					log.debug("=====================추천할 영화명은 "+candidateMap.get(e.getKey()).getMovieNm() + " - " + e.getValue()));
					 */
		
		return resultList;
	}

	private double calculate(MovieDoc movie1, MovieDoc movie2) {
		double score = 0.0;
		
	    // 감독 배열 가져오기
	    String[] directors1 = movie1.getDirectors();
	    String[] directors2 = movie2.getDirectors();

	    // 감독 배열을 리스트로 변환
	    List<String> listDirectors1 = Arrays.asList(directors1);
	    List<String> listDirectors2 = Arrays.asList(directors2);

	    // 최소 하나의 감독이 두 리스트에 모두 존재하는지 확인
	    for (String director : listDirectors1) {
	        if (listDirectors2.contains(director)) {
	        	//log.debug(director+" 감독이 발견된 영화는 "+movie2.getMovieNm());
	        	for(String d  : directors2) {
	        		log.debug(d);
	        	}
	            score += 0.5;
	            break;  // 일치하는 감독이 있으면 더 이상의 비교는 필요 없으므로 루프를 종료
	        }
	    }
	    
	    
	    // 배우 배열 가져오기
	    String[] actors1 = movie1.getActors();
	    String[] actors2 = movie2.getActors();
	    
	    // 배우 배열을 리스트로 변환
	    List<String> listActors1 = Arrays.asList(actors1);
	    List<String> listActors2 = Arrays.asList(actors2);
	    
	    // 최소 하나의 배우가 두 리스트에 모두 존재하는지 확인
	    for (String actor : listActors1) {
	    	if (listActors2.contains(actor)) {
	    		//log.debug(actor+" 배우일치 "+movie2.getMovieNm()+"에서 발견 ");
	    		score += 0.5;
	    		break;  // 일치하는 감독이 있으면 더 이상의 비교는 필요 없으므로 루프를 종료
	    	}
	    }
	    
	    // 국가 배열 가져오기
	    String[] nations1 = movie1.getNations();
	    String[] nations2 = movie2.getNations();
	    
	    // 배우 배열을 리스트로 변환
	    List<String> listNation1 = Arrays.asList(nations1);
	    List<String> listNation2 = Arrays.asList(nations2);
	    
	    // 최소 하나의 국가가 두 리스트에 모두 존재하는지 확인
	    for (String nation : listNation1) {
	    	if (listNation2.contains(nation)) {
	    		score += 0.1;
	    		//log.debug(nation+" 국가일치");
	    		break;  // 일치하는 감독이 있으면 더 이상의 비교는 필요 없으므로 루프를 종료
	    	}
	    }
	    
		return score;
	}
}
