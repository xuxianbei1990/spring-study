package Demo.Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * @Configuration�ȼ���������һ��XML�ļ���http://blog.csdn.net/javaloveiphone/article/details/52182899
 * @ComponentScan�ȼ���������һ��ɨ��·�� <context:component-scan base-package="Demo.Annotation">
 */
@Configuration
@ComponentScan(basePackages = "Demo.Annotation")
public class FruitService {
	@Autowired
	private Apple apple;
	
	@Autowired
	private GinSeng ginseng;
	
	//ע��Bean��������ʹ�÷�ʽ��
	/*
	 * ��ϸ��FruitDemo�� ClassPathXmlApplicationContext ����
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
