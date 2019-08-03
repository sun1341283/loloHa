package cn.offcn.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class OAHandlerExceptionResolver implements HandlerExceptionResolver{
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		//在控制台打异常信息
		ex.printStackTrace();
		
		String message=null;
		//判断ex是不是我们自定义异常
		if(ex instanceof OAException) {
			message=ex.getMessage();
		}else {
			message="未知错误";
		}
		ModelAndView view=new ModelAndView();
		view.addObject("message", message);
		view.setViewName("message/error");
		
		return view;
	}

}
