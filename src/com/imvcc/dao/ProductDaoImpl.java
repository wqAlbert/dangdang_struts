package com.imvcc.dao;

import org.hibernate.Session;

import com.imvcc.domain.Product;
/**
 * Dao 实现
 * @author Mr.rong实现
 *
 */
public class ProductDaoImpl extends BaseDao implements ProductDao{
	/**
	 * 根据Id超找茶品
	 */
	public Product findById(Integer id) {
		Session session=getSession();
		return (Product) session.get(Product.class, id);
	}
}
