package com.portal.web.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.web.model.APIMessage;
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
	public APIMessage saveUser(User registerUserDetails) {
		APIMessage apiMessage=new APIMessage();
		try {
			String selectedRole=registerUserDetails.getRoles().iterator().next().getRole();
			System.out.println("selected role :"+selectedRole);
			Role userRole = roleRepository.findByRole(selectedRole);
			registerUserDetails.getRoles().clear();
			registerUserDetails.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			userRepository.save(registerUserDetails);
			apiMessage.setStatus("success");
			apiMessage.setMessage("User Added Successfully");
		}catch (Exception e) {
			apiMessage.setStatus("error");
			apiMessage.setMessage("Please contact support.Operation failed.");
		}
		return apiMessage;
	}
}
