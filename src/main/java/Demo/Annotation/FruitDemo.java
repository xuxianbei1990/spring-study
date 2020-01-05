package Demo.Annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FruitDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context;
		Fruit fruit;
//		context = new ClassPathXmlApplicationContext("annotation-applicationContext.xml");
//	    fruit = (Fruit)context.getBean("getGinseng");
//		System.out.println(fruit.getClass().getName());
//	    fruit = (Fruit)context.getBean("gApp");
//		System.out.println(fruit.getClass().getName());
		
		context = new AnnotationConfigApplicationContext(FruitService.class);
	    fruit = (Fruit)context.getBean("getGinseng");
		System.out.println("AnnotationCon" + fruit.getClass().getName());
		
		ConfigurationDemo configurationDemo = (ConfigurationDemo)context.getBean("configurationDemo");
		System.out.println(configurationDemo.getClass().getName());
		
//		context = new AnnotationConfigApplicationContext(PropertySourceDemo.class);
//		context.getBean("fillProperty");
		

	}

}
