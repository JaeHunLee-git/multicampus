package com.sds.movieapp.model.cs.notice;

import java.util.List;
import java.util.Map;

import com.sds.movieapp.domain.NoticeDoc;

public interface NoticeService {
	public int selectCount(); //게시물 수 
	public List selectAll(Map map);//모든 게시물 가져오기
	public NoticeDoc select(NoticeDoc notice);
	public void regist(NoticeDoc notice);
	public void update(NoticeDoc notice);
	public void delete(NoticeDoc notice);	
}
