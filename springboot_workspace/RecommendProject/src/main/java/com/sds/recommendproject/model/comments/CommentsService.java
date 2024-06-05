package com.sds.recommendproject.model.comments;

import java.util.List;

import com.sds.recommendproject.domain.CommentsDoc;
import com.sds.recommendproject.domain.MovieDoc;



public interface CommentsService {
	
	public void registComments(CommentsDoc comments, MovieDoc movie);//영화평 등록
	public List selectAllByMember(int member_idx); //사용자의 모든 영화평 가져오기 
	public CommentsDoc select(int member_idx);//영화평 하나 가져오기
	
}
