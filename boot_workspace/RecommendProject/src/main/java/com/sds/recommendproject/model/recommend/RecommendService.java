package com.sds.recommendproject.model.recommend;

import java.util.List;

import com.sds.recommendproject.domain.CommentsDoc;


public interface RecommendService {
	public List getList(int member_idx);
}
