package com.imvcc.dao;

import java.util.List;

import com.imvcc.domain.Category;

public interface CategoryDao {
	public static final int CATEGORY_ID=1;
	public List<Category> findCatByParentId(Integer parentId);
	public Category findCatById(Integer id);
}
