package com.sds.recommendapp.model.member;

import java.util.List;

import com.sds.recommendapp.domain.Sns;


public interface SnsService {
	public List selectAll();
	public Sns selectByName(String sns_name);
}
