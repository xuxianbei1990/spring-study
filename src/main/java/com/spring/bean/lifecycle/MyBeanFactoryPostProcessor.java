package com.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

//负责创建BeanPostProcessor 根据定义分发不同BeanPostProcessor 有点转发的味道
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	public MyBeanFactoryPostProcessor(){
		super();
		System.out.println("这是BeanFactoryPostProcessor实现类构造器！！");
	}
	
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		BeanDefinition bd = beanFactory.getBeanDefinition("person");
		bd.getPropertyValues().addPropertyValue("phone", "110");
	}

}
