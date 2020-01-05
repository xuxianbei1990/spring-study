package Demo.Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

//http://blog.csdn.net/l153097889/article/details/52476219
//@PropertySource注解可以从properties文件中，获取对应的key-value值，将其赋予变量；
/*
 * @PropertySource({
	"classpath:config.properties",
	"classpath:db.properties" //if same key, this will 'win'
})
 * @PropertySources({
	@PropertySource("classpath:config.properties"),
	@PropertySource("classpath:db.properties")
})
 */
@Configuration  
@PropertySource("classpath:jdbc.properties") 
public class PropertySourceDemo {
	
	//测试失败待定
	@Value(value = "${db.mysql.driver}")
	private String driver;
	
	@Value("${db.mysql.url}")
	private String url;
	
	private String name, 
				password;
	
	@Autowired
	private Environment env;
	
	@Bean
	public PropertySourceDemo fillProperty(){
		name = env.getProperty("db.mysql.username");
		password = env.getProperty("db.mysql.password");
		System.out.println("driver:" + driver + "--url:" + url + 
				"--name:" + name + "--password" + password);
		return new PropertySourceDemo();
			
	}
	

}
