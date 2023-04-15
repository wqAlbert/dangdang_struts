package com.imvcc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.imvcc.common.SessionConstant;
import com.imvcc.dao.DaoFactory;
import com.imvcc.dao.UserDao;
import com.imvcc.domain.User;
import com.imvcc.util.DegistUtil;
import com.imvcc.util.EncoderUtil;

/**
 * ΪUser�ṩ���ݿ���ʵķ���
 * 
 * @author Administrator
 * 
 */
public class UserServiceImpl implements UserService {

	private Logger log = Logger.getLogger(UserServiceImpl.class);
	private UserDao userDao = DaoFactory.getUserDao();

	/**
	 * ����û�
	 */
	public User addUser(User user) {
		log.debug("Dang:����û�,��������");
		// md5 and base64 password
		user.setPassword(DegistUtil.produceDegistCode(user.getPassword()));
		// get last time
		user.setLastLoginTime(System.currentTimeMillis());
		user.setIsEmailVerify(false);
		user.setUserIntegral(0);
		
		// get save to database
		log.debug("Dang:����UserDao,д������");
		user = userDao.addUser(user);
		user.setEmailVerifyCode(EncoderUtil.produceUUIDCode(user.getId()));
		
		
		return user;
	}

	/**
	 * ���ҵ����ʼ��Ŀ�����
	 * 
	 * @param email
	 * @return
	 */
	@Deprecated
	public User findByEmail(String email) {
		log.debug("Dang:����Email�Ƿ����");
		List<User> users = userDao.findByProperty("email", email);
		if (users.size() == 0)
			return null;
		else
			return users.get(0);
	}

	/**
	 * �������Բ���һ������
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public User findByProperty(String propertyName, Object value) {
		log.debug("Dang:����-" + propertyName + "-���в���");
		List<User> users = userDao.findByProperty(propertyName, value);
		if (users.size() == 0)
			return null;
		else
			return users.get(0);
	}

	/**
	 * ����Id�����û�
	 */
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	/**
	 * ��֤�û���������login.action
	 */
	public User validateUser(User user) {
		System.out.println("user.getPasswordǰ="+user.getPassword());
		user.setPassword(DegistUtil.produceDegistCode(user.getPassword()));
		System.out.println("user.getPassword��="+user.getPassword());
		
		List<User> users = userDao.findByProperty("email", user.getEmail());
		if (users.size() == 0) {
			return null;
		}
		User dbUser = users.get(0);
		System.out.println("dbUser="+dbUser.getPassword());
		System.out.println("user.getPassword="+user.getPassword());
		if(!user.getPassword().equals(dbUser.getPassword())){
			return null;
		}
		return dbUser;
	}

	/**
	 * ����User��������login.action
	 */
	public User updateUser(User user) {
		return userDao.update(user);
	}

	
	

	
	
	


	public static void main(String[] args) {
		String str = "rongyandong$hello";
		System.out.println(str.substring(str.lastIndexOf("$") + 1));
	}

}
