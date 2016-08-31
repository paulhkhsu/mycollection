package com.myrest.webcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {

	@Value("${db.password}")
	private String dbPassword;

	@RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
	public ModelAndView index() {
		System.out.println("test " + dbPassword);
		return new ModelAndView("index");
	}

}
