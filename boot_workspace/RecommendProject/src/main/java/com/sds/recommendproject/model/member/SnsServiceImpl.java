package com.sds.recommendproject.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.recommendproject.domain.Sns;

@Service
public class SnsServiceImpl implements SnsService{

	@Autowired
	private SnsDAO snsDAO;
	
	@Override
	public List selectAll() {
		return snsDAO.selectAll();
	}

	@Override
	public Sns selectByName(String sns_name) {
		return snsDAO.selectByName(sns_name);
	}
	
}
