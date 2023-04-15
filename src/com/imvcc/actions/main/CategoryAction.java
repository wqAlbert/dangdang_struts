package com.imvcc.actions.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.imvcc.actions.BaseAction;
import com.imvcc.dao.CategoryDao;
import com.imvcc.domain.Category;
import com.imvcc.service.CategoryService;
import com.imvcc.service.ServiceFacotry;

public class CategoryAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Category> listCats;
	private CategoryService catService=ServiceFacotry.getCategoryService();
	
	Logger log =Logger.getLogger(CategoryAction.class);
	
	public List<Category> getListCats() {
		return listCats;
	}
	public void setListCats(List<Category> listCats) {
		this.listCats = listCats;
	}
	public String execute() {
		log.debug("Dang:正在创建主目录");
		listCats=catService.createCategory(CategoryDao.CATEGORY_ID);//CategoryDao.CATEGORY_ID=1
		return "success";
	}
}
