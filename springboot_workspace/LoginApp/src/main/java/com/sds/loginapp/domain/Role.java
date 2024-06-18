package com.sds.loginapp.domain;

import lombok.Data;

@Data
public class Role {
	private int role_idx;
	private String role_name;
	private int level;
}
