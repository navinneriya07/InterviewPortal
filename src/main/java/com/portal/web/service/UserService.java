package com.portal.web.service;

import com.portal.web.model.User;

public interface UserService {
	
	User findUserByEmailAndPassword(String email,String password);

	void saveUser(User user);
}
