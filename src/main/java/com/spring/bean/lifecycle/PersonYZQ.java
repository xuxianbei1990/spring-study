package com.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//人类玉藻前
public class PersonYZQ implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

	private String name;
	private String address;
	private int phone;

	private BeanFactory beanFactory;
	private String beanName;

	public PersonYZQ() {
		System.out.println("【构造器】调用PersonYZQ的构造器实例化");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("【注入属性】注入属性name" + name);
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		System.out.println("【注入属性】注入属性address");
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		System.out.println("【注入属性】注入属性phone");
		this.phone = phone;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public String getBeanName() {
		return beanName;
	}

	public String toString() {
		return "人类玉藻前 名字：" + this.name + "  地址:" + this.address + " 联系电话：" + this.phone;
	}

	// 通过<bean>的init-method属性指定的初始化方法
	public void myInit() {
		System.out.println("【init-method】调用<bean>的init-method属性指定的初始化方法");
	}

	// <bean>的destroy-method属性指定的初始化方法
	public void myDestory() {
		System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
	}

	// DiposibleBean接口方法
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
	}

	// InitializingBean接口方法
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
	}

	// BeanNameAware接口方法
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()" + name);
		this.beanName = name;
	}

	// BeanFactoryAware接口方法
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()" + beanFactory.getClass().getName());
		this.beanFactory = beanFactory;
	}

}
