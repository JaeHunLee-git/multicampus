package com.sds.recommendproject.model.recommend;

import com.sds.recommendproject.domain.SentimentDic;

public interface SentimentDicDAO {

	public SentimentDic select(String word);
	
}
