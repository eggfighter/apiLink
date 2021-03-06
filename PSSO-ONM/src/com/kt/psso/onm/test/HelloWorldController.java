package com.kt.psso.onm.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.POST, value="/hello.do")
	public ModelAndView helloWorld() {
		ModelAndView mav = new ModelAndView("hello");
		mav.addObject("message", "Hello, World");
		return mav;
	}
}
