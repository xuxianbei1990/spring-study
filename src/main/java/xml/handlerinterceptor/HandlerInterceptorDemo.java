package xml.handlerinterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandlerInterceptorDemo implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(">>>HandlerInterceptorDemo>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet "
				+ "渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(">>>HandlerInterceptorDemo>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调"
				+ "用之后）");
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调"
				+ "用之前）");
		
		// 不拦截api类的请求
        if (StringUtils.startsWith(request.getServletPath(), "/api")) {
            return true;
        }
		// 不拦截登录数据请求
		if (StringUtils.equals(request.getServletPath(), "/login")) {
			return true;
		}
		HttpSession session = request.getSession();
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
	}

}
