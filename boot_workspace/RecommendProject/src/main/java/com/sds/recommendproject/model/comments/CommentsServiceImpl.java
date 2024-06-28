package com.sds.recommendproject.model.comments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.recommendproject.domain.CommentsDoc;
import com.sds.recommendproject.domain.MovieDoc;
import com.sds.recommendproject.domain.SentimentDic;
import com.sds.recommendproject.exception.CommentsException;
import com.sds.recommendproject.model.recommend.MovieDocDAO;
import com.sds.recommendproject.model.recommend.SentimentDicDAO;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.util.common.model.Pair;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mongoCommentsService")
public class CommentsServiceImpl implements CommentsService{

	
	@Autowired
	private MovieDocDAO movieDAO;
	
	@Autowired
	private CommentsDocDAO commentsDAO;
	
	@Autowired
	private Komoran komoran; //형태소 분석객체 
	
	@Autowired
	private SentimentDicDAO sentimentDicDAO;
	
	public void registComments(CommentsDoc comments, MovieDoc movie) throws CommentsException{
		
		String text = comments.getContent().replaceAll("[^a-zA-Z0-9가-힣\\s]", ""); //특수문자 제거 영문자, 숫자, 한글, 공백을 제외한 모든 문자를 제거
		
		KomoranResult  result = komoran.analyze(text);

        // 각 형태소와 품사 태그를 출력
        List<Pair<String, String>> pairList = new ArrayList<Pair<String, String>>();
        
        /*
        result.getList().forEach(pair -> {
        	log.debug("pairList 에 담기 : "+pair.getFirst() + "\t\t" + pair.getSecond());
        	pairList.add(pair);
        });		
         */
        
		//확인해보기
		for(Pair<String, String> pair : result.getList()) {
			log.debug("형태소 분석 쌍은 "+pair.getFirst());
		}

		//분리된 형태소를 가지고 몽고DB의 감성 사전에서 긍정, 부정 여부에 따른 점수를 가져온다
		float score = 0;
		
		for(Pair<String, String> pair  : result.getList() ) {
			
			String word = pair.getFirst(); //형태소 얻기 
			String formattedWord = word + "/" + pair.getSecond(); //두번째는 품사를 표현
			
			log.debug("검색할 word is "+formattedWord);
			
			SentimentDic sentimentDic = sentimentDicDAO.select(formattedWord);
			
			if(sentimentDic !=null) {
				log.debug(formattedWord+" , "+word+", 긍정값: "+sentimentDic.getPOS()+" , 부정값: "+sentimentDic.getNEG());
				score += sentimentDic.getPOS() - sentimentDic.getNEG();
			}else {
				log.debug("사전에 없슴");
			}
		}
		
		log.debug("score = "+score);
		comments.setScore(score);//CommentsDoc 에 최종 점수 대입
		
		//코멘트 등록 
		commentsDAO.insert(comments);
 	}

	
	@Override
	public List selectAllByMember(int member_idx) {
		return null;
	}


	public CommentsDoc select(int member_idx) {
		return null;
	}

	
}
