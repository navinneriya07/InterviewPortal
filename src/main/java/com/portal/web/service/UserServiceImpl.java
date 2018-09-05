package com.portal.web.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.web.model.Role;
import com.portal.web.model.User;
import com.portal.web.repository.RoleRepository;
import com.portal.web.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User findUserByEmailAndPassword(String email,String password) {
		return userRepository.findByEmailAndPassword(email,password);
	}

	@Override
	public void saveUser(User user) {
		
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}
