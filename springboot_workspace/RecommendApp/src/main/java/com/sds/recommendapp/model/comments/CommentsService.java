package com.sds.recommendapp.model.comments;

import com.sds.recommendapp.domain.CommentsDoc;
import com.sds.recommendapp.domain.MovieDoc;

public interface CommentsService {
	
	public void registComments(CommentsDoc commentsDoc, MovieDoc movieDoc);
}
