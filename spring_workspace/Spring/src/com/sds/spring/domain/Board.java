package com.sds.spring.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Board {
	@Id
	private int board_idx;
	
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}
