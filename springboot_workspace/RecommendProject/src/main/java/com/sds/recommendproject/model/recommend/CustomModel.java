package com.sds.recommendproject.model.recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sds.recommendproject.domain.CommentsDoc;
import com.sds.recommendproject.domain.MovieDoc;
import com.sds.recommendproject.model.comments.CommentsDocDAO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CustomModel {
	
	private DataModel dataModel;
	
	public CustomModel(List<CommentsDoc> commentsList, int member_idx) {
		/*-------------------------------------------------------------------------------------------
		 맵1)  Key(사용자id)  -  Value( [ (영화id, 평점, 사용자id), (영화id, 평점, 사용자id), (영화id, 평점, 사용자id), ,(영화id, 평점, 사용자id) ] )		 
		 -------------------------------------------------------------------------------------------*/ 
		List<GenericPreference> list = new ArrayList(); //첫번째 맵에 들어갈 List 선언
		
		for (CommentsDoc comments : commentsList) {
			list.add(new GenericPreference(comments.getMember_idx(), comments.getMovie_idx(), comments.getScore()));
		}
		
		// 유저의 아이디를 키값으로 영화평점 리스트를 보관하는 객체 list 객체를 GenericUserPreferenceArray 형태로 바꾸어
		// 맵에 보관한다고 보면 된다
		FastByIDMap<PreferenceArray> userData = new FastByIDMap();
		userData.put(member_idx, new GenericUserPreferenceArray(list));
		
		dataModel= new GenericDataModel(userData);		
	}

}
