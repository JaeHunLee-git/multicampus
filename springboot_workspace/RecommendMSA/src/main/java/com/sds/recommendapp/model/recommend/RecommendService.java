package com.sds.recommendapp.model.recommend;

import java.util.List;

import com.sds.recommendapp.domain.CommentsDoc;


public interface RecommendService {
	public List getList(int member_idx);
}
