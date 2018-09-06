package com.portal.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.web.model.APIMessage;
import com.portal.web.model.Role;
import com.portal.web.model.User;
import com.portal.web.service.UserService;

@Controller()
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
	@ResponseBody
	public User validateUserLogin(@RequestBody User user) {
		/*
		 * System.out.
		 * println("-------------------------------------finally come -----------------------------------------------"
		 * ); User user1 = new User("navinneriya07@gmail.com", "123456"); User user2 =
		 * new User("navin@gmail.com", "123456");
		 * 
		 * Role role1=new Role("ADMIN"); Role role2=new Role("USER");
		 * 
		 * roleRepository.saveAll(new HashSet<Role>() { { add(new Role("ADMIN", new
		 * HashSet<User>() { { add(user1); } } ));
		 * 
		 * add(new Role("USER", new HashSet<User>() { { add(user1); add(user2); } }));
		 * }});
		 * 
		 * for (Role role : roleRepository.findAll())
		 * System.out.println(role.toString());
		 * 
		 * userRepository.saveAll(new HashSet<User>() { { add(new
		 * User("navinneriya07@gmail.com","123456", new HashSet<Role>() { { add(role1);
		 * add(role2); } } ));
		 * 
		 * add(new User("navin@gmail.com","123456", new HashSet<Role>() { { add(role2);
		 * } })); }});
		 * 
		 * for (User user : userRepository.findAll())
		 * System.out.println(user.toString());
		 */

		User retrieveUser = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
		if (retrieveUser != null) {
			Set<Role> roles = retrieveUser.getRoles();
			for (Role role : roles)
				System.out.println("role name :" + role.getRole());
			return retrieveUser;
		}
		return null;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value="signUp",method=RequestMethod.POST)
	@ResponseBody
	public APIMessage registerUser(@RequestBody User registerUserDetails) {
		System.out.println("registerUserDetails :"+registerUserDetails);
		APIMessage apiMessage=userService.saveUser(registerUserDetails);
		return apiMessage;
	}
}
