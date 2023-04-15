package com.imvcc.dao;

public class DaoFactory{
	/**
	 * get user data access object
	 * @return
	 */
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
	/**
	 * 返回目录的数据库访问对象
	 * @return
	 */
	public static CategoryDao getCategoryDao() {
		return new CategoryDaoImpl();
	}
	/**
	 * 返回图书的数据库访问对象
	 * @return
	 */
	public static BookDao getBookDao() {
		return new BookDaoImpl();
	}
	public static ProductDao getProductDao() {
		return new ProductDaoImpl();
	}
	public static OrderDao getOrderDao() {
		return new OrderDaoImpl();
	}
}
