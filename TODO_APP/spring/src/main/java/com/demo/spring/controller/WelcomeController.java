package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.spring.service.LoginService;




@Controller
public class WelcomeController {
@Autowired
LoginService loginService;
	//The normal method
//	@RequestMapping("/Login")
//	public String one(@RequestParam String name, ModelMap model) {
//		model.put("name", name);
//		return "login";
//	}
	
	//working with forms
	
//	@RequestMapping("/Login")
//	public String Login(ModelMap model) {
//		return "login";
//	}
//	

	private  String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return  principal.toString();
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String ShowWelcomePage(ModelMap model) {
		model.put("name", getLoggedInUserName() );
		return "welcome";
	}
	
	
}


