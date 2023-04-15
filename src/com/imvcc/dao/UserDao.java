package com.imvcc.dao;

import java.util.List;

import com.imvcc.domain.User;

public interface UserDao {

	public User addUser(User user);

	public User update(User user);

	public User delete(User user);

	public int updateByExample(User userExample);

	public User findById(Integer id);

	public List<User> findByProperty(String properName,Object value);

	public List<User> findByExample(User userExample);
}
