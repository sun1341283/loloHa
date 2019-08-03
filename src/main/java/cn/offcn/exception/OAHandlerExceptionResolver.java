package cn.offcn.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class OAHandlerExceptionResolver implements HandlerExceptionResolver{
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		//�ڿ���̨���쳣��Ϣ
		ex.printStackTrace();
		
		String message=null;
		//�ж�ex�ǲ��������Զ����쳣
		if(ex instanceof OAException) {
			message=ex.getMessage();
		}else {
			message="δ֪����";
		}
		ModelAndView view=new ModelAndView();
		view.addObject("message", message);
		view.setViewName("message/error");
		
		return view;
	}

}
