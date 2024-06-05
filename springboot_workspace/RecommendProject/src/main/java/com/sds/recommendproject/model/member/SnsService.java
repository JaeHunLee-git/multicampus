package com.sds.recommendproject.model.member;

import java.util.List;

import com.sds.recommendproject.domain.Sns;


public interface SnsService {
	public List selectAll();
	public Sns selectByName(String sns_name);
}
