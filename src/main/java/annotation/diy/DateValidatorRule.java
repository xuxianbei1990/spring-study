package annotation.diy;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;
@CustomerRule
public class DateValidatorRule extends AbastractCustomerValidatorRule  {

	@Override
	public boolean support(Annotation annotation) {
		return annotation instanceof DateString;
	}

	@Override
	public void validProperty(Annotation annotation, Object property, PostHandler postHander) {
		DateString ds = (DateString)annotation;
		if (parse(ds.pattern(), (String) property) == null) {
			postHander.postHanle(ds.errorCode(), ds.message());
		}
	}

	private Date parse(String pattern, String property)  {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
            return sdf.parse(property); 
		} catch (ParseException e) {
			
		}
		return null;
	}

}
