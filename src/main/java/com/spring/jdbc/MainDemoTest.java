package com.spring.jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainDemoTest {
	public static void main(String[] args) throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		
		ErKouNvDAO erkounvDAO = (ErKouNvDAO) context.getBean("ErKouNvDAO");
		
		List<ErKouNv> erkounvs = erkounvDAO.queryErKouNv();
		
		for (ErKouNv ekn: erkounvs){
			System.out.println("play ID" + ekn.getPlayId());
			System.out.println("play Kill" + ekn.getPlayKill());
			System.out.println("play Name" + ekn.getPlayName());
		}
	}

}
