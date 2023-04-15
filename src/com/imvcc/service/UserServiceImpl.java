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
 * 为User提供数据库访问的服务
 * 
 * @author Administrator
 * 
 */
public class UserServiceImpl implements UserService {

	private Logger log = Logger.getLogger(UserServiceImpl.class);
	private UserDao userDao = DaoFactory.getUserDao();

	/**
	 * 添加用户
	 */
	public User addUser(User user) {
		log.debug("Dang:添加用户,加密密码");
		// md5 and base64 password
		user.setPassword(DegistUtil.produceDegistCode(user.getPassword()));
		// get last time
		user.setLastLoginTime(System.currentTimeMillis());
		user.setIsEmailVerify(false);
		user.setUserIntegral(0);
		
		// get save to database
		log.debug("Dang:生产UserDao,写入数据");
		user = userDao.addUser(user);
		user.setEmailVerifyCode(EncoderUtil.produceUUIDCode(user.getId()));
		
		
		return user;
	}

	/**
	 * 查找电子邮件的可用性
	 * 
	 * @param email
	 * @return
	 */
	@Deprecated
	public User findByEmail(String email) {
		log.debug("Dang:查找Email是否存在");
		List<User> users = userDao.findByProperty("email", email);
		if (users.size() == 0)
			return null;
		else
			return users.get(0);
	}

	/**
	 * 根据属性查找一个对象
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public User findByProperty(String propertyName, Object value) {
		log.debug("Dang:根据-" + propertyName + "-进行查找");
		List<User> users = userDao.findByProperty(propertyName, value);
		if (users.size() == 0)
			return null;
		else
			return users.get(0);
	}

	/**
	 * 根据Id查找用户
	 */
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	/**
	 * 验证用户，服务于login.action
	 */
	public User validateUser(User user) {
		System.out.println("user.getPassword前="+user.getPassword());
		user.setPassword(DegistUtil.produceDegistCode(user.getPassword()));
		System.out.println("user.getPassword后="+user.getPassword());
		
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
	 * 更新User，服务于login.action
	 */
	public User updateUser(User user) {
		return userDao.update(user);
	}

	
	

	
	
	


	public static void main(String[] args) {
		String str = "rongyandong$hello";
		System.out.println(str.substring(str.lastIndexOf("$") + 1));
	}

}
