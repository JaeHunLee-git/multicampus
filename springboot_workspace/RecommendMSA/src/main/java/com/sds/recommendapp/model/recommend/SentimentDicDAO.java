package com.sds.recommendapp.model.recommend;

import com.sds.recommendapp.domain.SentimentDic;

public interface SentimentDicDAO {

	public SentimentDic select(String word);
	
}
