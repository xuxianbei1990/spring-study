package aop.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aop.service.CustomerService;

public class App {
	static void printMsg(CustomerService cust){
		System.out.println("*************************");
		cust.printName();
		System.out.println("*************************");
		cust.printURL();
		System.out.println("*************************");
		try {
			cust.printThrowException();
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//方法调用之前
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "aop/applicationContext.xml" });
		CustomerService cust = (CustomerService)appContext.getBean("customerServiceProxy");
		printMsg(cust);
		//方法调用之后
//		ApplicationContext appContextafter = new ClassPathXmlApplicationContext("afterMethod.xml" );
//		CustomerService custafter = (CustomerService)appContextafter.getBean("customerServiceProxy");
//		printMsg(custafter);
		//异常时候
//		ApplicationContext appContextafter = new ClassPathXmlApplicationContext("throwException.xml" );
//		CustomerService custafter = (CustomerService)appContextafter.getBean("customerServiceProxy");
//		printMsg(custafter);
		//环绕通知
//		ApplicationContext appContextafter = new ClassPathXmlApplicationContext("AroundMethod.xml" );
//		CustomerService custafter = (CustomerService)appContextafter.getBean("customerServiceProxy");
//		printMsg(custafter);
//		((AbstractApplicationContext) appContextafter).close();  
	}

}
