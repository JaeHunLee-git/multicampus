package com.sds.spring.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/*
  Mybatis 에서는 쿼리문 수행 객체  - SqlSession   , SqlSessionFactory
  Hibernate 에서는 쿼리문 수행 객체 - Session     , SessionFactory
*/
public class HibernateManager {
	private static HibernateManager instance;
	private SessionFactory factory;
	
	private HibernateManager() {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("com/sds/spring/hibernate/hibernate.cfg.xml").build();  
		try {
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public static HibernateManager getInstance() {
		if (instance == null) {
			instance = new HibernateManager();
		}
		return instance;
	}
	
	//Session 반환 메서드 
	public Session getSession() {
		return factory.openSession();
	}
	
	//Session 반납
	public void release(Session session) {
		session.close();
	}
}










