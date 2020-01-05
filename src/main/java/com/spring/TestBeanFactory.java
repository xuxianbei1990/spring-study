package com.spring;

public class TestBeanFactory {

	public static void main(String[] args) throws Exception {
		BeanFactory factory = new ClassPathXmlApplicationContext("src/main/resources/applicationContext.xml");
		Object o = factory.getBean("c");
		Moveable m = (Moveable) o;
		m.run();
	}

}
