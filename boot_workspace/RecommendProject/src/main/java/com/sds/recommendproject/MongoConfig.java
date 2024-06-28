package com.sds.recommendproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

//스프링의  빈설정 xml을 대신하는 자바의 클래스 
//몽고DB에서는 마치 Spring-Mybatis 처럼 CRUD 를 수행하는 ~~~Template 객체를 지원한다.
//따라서 MongoTemplate 객체를 빈으로 등록하여, 우리의 DAO에서 주입받아 사용해보자
//<bean id="mongoClient" class="MongoClient"></bean>  : 몽고db 접속 객체
//<bean id="mongoTemplate" class="MongoTemplate"></bean> : CRUD 수행 객체

@Configuration
public class MongoConfig {

	@Bean
	public MongoClient  mongoClient() {
		return MongoClients.create("mongodb://test:1234@localhost:27017");//접속 문자열 넣기
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), "movie");
	}
}





