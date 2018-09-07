package com.portal.web.service;

import com.portal.web.model.APIMessage;
import com.portal.web.model.User;

public interface UserService {
	
	User findUserByEmailAndPassword(String email,String password);

	APIMessage saveUser(User user);
	
	APIMessage retrievePassword(User user);
	
	APIMessage updatePassword(User user);
}
