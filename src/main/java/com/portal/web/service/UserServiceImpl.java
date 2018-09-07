package com.portal.web.service;

import java.util.Arrays;
import java.util.HashSet;

import javax.mail.internet.MimeMessage;

import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	
	@Autowired
	private JavaMailSender sender;

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

	@Override
	public APIMessage retrievePassword(User user) {
		APIMessage apiMessage=new APIMessage();
		try {
			User selectedUser=userRepository.findByEmail(user.getEmail());
			if(selectedUser!=null) {
				sendEmail(selectedUser);
				apiMessage.setStatus("success");
				apiMessage.setMessage("Email is sent successfully");
			}else {
				apiMessage.setStatus("error");
				apiMessage.setMessage("Email is not valid.Please try again.");
			}
		}catch (Exception e) {
			apiMessage.setStatus("error");
			apiMessage.setMessage("Operation failed.Please contact support");
		}
		return apiMessage;
	}
	
	private void sendEmail(User user) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(user.getEmail());
		helper.setText("Hello,"
				+ "Your password is "+user.getPassword());
		helper.setSubject("Requested Password ");
		sender.send(message);
	}

	@Override
	public APIMessage updatePassword(User user) {
		APIMessage apiMessage=new APIMessage();
		try {
			User retrieveUser=userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
			retrieveUser.setPassword(user.getPassword());
			userRepository.save(retrieveUser);
			apiMessage.setStatus("success");
			apiMessage.setMessage("Password updated successfully");
		}catch(Exception e) {
			apiMessage.setStatus("error");
			apiMessage.setMessage("Password updation failed");
		}
		return apiMessage;
	}
}
