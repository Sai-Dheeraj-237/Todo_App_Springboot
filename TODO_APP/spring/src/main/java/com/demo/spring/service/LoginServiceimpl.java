package com.demo.spring.service;

import org.springframework.stereotype.Component;

@Component
public class LoginServiceimpl implements LoginService {

	@Override
	public boolean validateLogin(String name, String password) {
		// TODO Auto-generated method stub
		return name.equals("saidheeraj")&&password.equals("1234");
	}

}
