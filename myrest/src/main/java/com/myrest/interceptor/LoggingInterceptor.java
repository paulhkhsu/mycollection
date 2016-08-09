package com.myrest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myrest.controller.EmployeeController;

public class LoggingInterceptor implements HandlerInterceptor {
	static private final Logger log = LogManager.getLogger(LoggingInterceptor.class
			.getName());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		long startTime = System.currentTimeMillis();
		request.setAttribute("reqStartTime", startTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long)request.getAttribute("reqStartTime");

		long endTime = System.currentTimeMillis();

		long executeTime = endTime - startTime;
		modelAndView.addObject("executeTime",executeTime);

		log.debug(modelAndView.getViewName() + " " + executeTime);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("---afterCompletion---");
	}
}
