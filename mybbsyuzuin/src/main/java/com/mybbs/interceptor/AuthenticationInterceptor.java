package com.mybbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//	인터셉트 기능을 정의한 객체 (HandlerInterceptorAdapter를 상속 받아야함)
public class AuthenticationInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러 진입 전 인터셉트 하는 것
		System.out.println("인터셉터 prehandler");
		//	세션에서 값을 가져온다. 
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("userid");
		if(username==null) {
			//	현재 로그인을 하지 않은 상태
			response.sendRedirect(request.getContextPath()+"/login");
			return false;	//	23번에 지정한 경로로 가라
			
		}
		
		return super.preHandle(request, response, handler);	//	이 리턴을 만나면 니가 가고 싶은 컨트롤러로 가라
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 진입 후 인터셉트 하는 것
		System.out.println("인터셉트 postHandler");
		super.postHandle(request, response, handler, modelAndView);
	}

}
