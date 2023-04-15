package com.imvcc.dao;

import com.imvcc.domain.Product;

/**
 * the interface for product Data access object
 * @author Mr.rong
 *
 */
public interface ProductDao {
	public Product findById(Integer id);
}
