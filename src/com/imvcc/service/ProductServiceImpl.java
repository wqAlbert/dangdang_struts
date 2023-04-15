package com.imvcc.service;

import com.imvcc.dao.DaoFactory;
import com.imvcc.dao.ProductDao;
import com.imvcc.domain.Product;

public class ProductServiceImpl implements ProductService{
	private ProductDao productDao=DaoFactory.getProductDao();
	public Product findById(Integer id) {
		return productDao.findById(id);
	}

}
