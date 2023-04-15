package com.imvcc.service;

import java.util.List;

import com.imvcc.actions.main.CurrentBooks;
import com.imvcc.domain.Category;

public interface CategoryService {
	public List<Category> createCategory(Integer parentId);
	public Category findCatById(Integer id);
	/**
	 * 获得当前图书信息
	 * @param category
	 * @param sc
	 * @param ssc
	 * @param page
	 * @param pageSize
	 * @return
	 */
	//public CurrentBooks getCurrrentBooks(Category category, Integer sc, Integer ssc, Integer page, int pageSize);

}
