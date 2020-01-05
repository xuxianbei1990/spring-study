package annotation.diy;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;

@CustomerRule
public abstract class AbastractCustomerValidatorRule implements CustomerValidatorRule {

	public abstract boolean support(Annotation annotation);

	public void valid(Annotation annotation, Object target, final Field field, final Errors errors) throws Exception {
		
		preHanlde(annotation, target, field, errors);
		PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(target.getClass(), field.getName());
		Method reader = propertyDescriptor.getReadMethod();
		Object property = reader.invoke(target);
		validProperty(annotation, property, new PostHandler() {
			public void postHanle(String errorCode, String message) {
				errors.rejectValue(field.getName(), errorCode, message);
			}
		});
	}

	public static interface PostHandler {
		public void postHanle(String errorCode, String message);
	}

	public abstract void validProperty(Annotation annotation, Object property, PostHandler postHander);

	private void preHanlde(Annotation annotation, Object object, Field field, Errors errors) {
		Assert.notNull(object, object.toString());
		Assert.notNull(annotation, annotation.toString());
		Assert.notNull(annotation, annotation.toString());
		Assert.notNull(errors, errors.toString());
		Assert.notNull(field, field.toString());

	}

}
