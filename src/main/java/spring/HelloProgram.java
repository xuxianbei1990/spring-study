package spring;

import spring.helloworld.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class HelloProgram {
	public static void main(String[] args){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("beans.xml");
		
		HelloWorldService service = 
				(HelloWorldService)context.getBean("helloWorldService");
		
		HelloWorld hw = service.getHelloWorld();
		
		hw.sayHello();
		
		//bean实例化有哪些方式?
		//1直接调用类进行无参数创建
		EarthWill earthwill = 
				(EarthWill)context.getBean("Orochi");
		earthwill.Kill();
		//2使用静态工厂方法实例化(简单工厂模式)
		earthwill = 
				(EarthWill)context.getBean("StaticFactoryEarthWill", EarthWill.class);
		earthwill.Kill();
		//3 使用工厂模式实例化
		earthwill = (EarthWill)context.getBean("FireChris", EarthWill.class);
		earthwill.Kill();
	}

}
