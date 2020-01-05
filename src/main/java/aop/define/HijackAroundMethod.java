package aop.define;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class HijackAroundMethod implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Method name:" + invocation.getMethod().getName());
		System.out.println("Method arguments:" + Arrays.toString(invocation.getArguments()));

		System.out.println("HijackAroundMethdo : Before method hijacked!");

		try {
			//必须调用“methodInvocation.proceed();” 继续在原来的方法执行，否则原来的方法将不会执行。
			Object result = invocation.proceed();
			System.out.println("HijackAroundMethod : Before after hijacked!");
            return result;
		} catch (IllegalArgumentException e) {
			System.out.println("HijackAroundMethod : Throw exception hijacked!");
			throw e;
		}
	}

}
