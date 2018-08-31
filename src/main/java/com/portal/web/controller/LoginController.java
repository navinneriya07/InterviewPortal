package com.portal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.portal.web.model.User;
import com.portal.web.service.UserService;

@Controller("/api/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@CrossOrigin("*")
	@RequestMapping(value = "/validateLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView validateUserLogin(@RequestBody User user) {
		System.out.println("user :"+user);
		return null;
	}
}
