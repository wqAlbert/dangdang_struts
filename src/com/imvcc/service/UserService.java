package com.imvcc.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.imvcc.domain.User;

public interface UserService {
	public User addUser(User user);
	public User updateUser(User user);
	public User findByEmail(String email);
	public User findById(Integer id);
	public User findByProperty(String propertyName,Object value);
	public User validateUser(User user);
	
	
	
}
