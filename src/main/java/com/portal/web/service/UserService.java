package com.portal.web.service;

import com.portal.web.model.User;

public interface UserService {
	
	User findUserByEmail(String email);

	void saveUser(User user);
}
