package com.sds.mall.model.member;

import java.util.List;

import com.sds.mall.domain.MemberDetail;

public interface MemberDetailDAO {
	public void insert(MemberDetail memberDetail); //등록
	public List selectAll(); //모든 레코드 가져오기 
	public MemberDetail select(int member_detail_idx); //한건 가져오기
	public void update(MemberDetail memberDetail); //한건 수정 
	public void delete(MemberDetail memberDetail);//한건 삭제 
	
}
