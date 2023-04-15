package com.imvcc.dao;

import org.hibernate.Session;

import com.imvcc.util.HibernateSessionFactory;
/**
 * 所有基本Data Access Object的基类，提供基本的访问数据库方法
 * @author Mr.rong
 *
 */
public class BaseDao {
	/**
	 * 打开session
	 * @return
	 */
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
}
