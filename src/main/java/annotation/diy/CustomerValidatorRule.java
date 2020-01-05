package annotation.diy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.validation.Errors;

public interface CustomerValidatorRule {

	/*
	 * 判断是否支持该注解
	 */
	public boolean support(Annotation annotation);
	
	/*
	 * 校验处理
	 */
	public void valid(Annotation annotation, Object object, Field field, Errors errors)  
            throws Exception;  
}
