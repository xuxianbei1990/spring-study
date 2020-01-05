package Demo.Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * @Configuration等价于配置了一个XML文件。http://blog.csdn.net/javaloveiphone/article/details/52182899
 * @ComponentScan等价于配置了一个扫描路径 <context:component-scan base-package="Demo.Annotation">
 */
@Configuration
@ComponentScan(basePackages = "Demo.Annotation")
public class FruitService {
	@Autowired
	private Apple apple;
	
	@Autowired
	private GinSeng ginseng;
	
	//注解Bean常用两种使用方式。
	/*
	 * 详细见FruitDemo的 ClassPathXmlApplicationContext 方法
	 */
	@Bean(name="gApp")
	public Fruit<?> getApple(){
		System.out.println("Apple" + apple.getClass().getName().hashCode());;
		System.out.println(ginseng.getClass().getName().hashCode());
		return new Apple();
	}
	@Bean
	public Fruit<?> getGinseng(){
		return new GinSeng();
	}

}
