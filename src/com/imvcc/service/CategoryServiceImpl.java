package com.imvcc.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.imvcc.actions.main.CurrentBooks;
import com.imvcc.dao.CategoryDao;
import com.imvcc.dao.DaoFactory;
import com.imvcc.domain.Category;

public class CategoryServiceImpl implements CategoryService {

	//private BookService bookService = ServiceFacotry.getBookService();
	private CategoryDao catDao = DaoFactory.getCategoryDao();

	Logger log = Logger.getLogger(CategoryServiceImpl.class);
	/**
	 * 创建目录
	 */
	public List<Category> createCategory(Integer parentId) {
		return catDao.findCatByParentId(parentId);
	}
	/**
	 * 根据Id查找所有目录
	 */
	public Category findCatById(Integer id) {
		if(id==null) {
			
			return null;
		}
		Category cat=catDao.findCatById(id);
		
		return cat;
	}

	/**
	 * 获取当前图书信息，目录，目录下的图书，总页数
	 */
//	public CurrentBooks getCurrrentBooks(Category category, Integer sc,
//			Integer ssc, Integer page, int pageSize) {
//		int totalResults = 0;
//		CurrentBooks curBooks = new CurrentBooks();
//		log.debug("Dang:准备开始搜索指定目下的图书");
//		if (ssc == null) {
//			log.debug("Dang:没有三级目录，搜索二级目录所有图书");
//			totalResults = category.getProductCount();
//			curBooks.setBooks(bookService.findBookByCatId(sc, page, pageSize));
//			curBooks.setCurCat(category);
//		} else {
//			log.debug("Dang:在三级目录下搜索所有的图书");
//			for (Category cat : category.getSubCats()) {
//				if (cat.getId().equals(ssc)) {
//					totalResults = cat.getProductCount();
//					curBooks.setCurCat(cat);
//					break;
//				}
//			}
//			curBooks.setBooks(bookService.findBookByCatId(ssc, page, pageSize));
//		}
//		log.debug("Dang:计算总页数");
//		curBooks.setTotalPages(totalResults / pageSize
//				+ (totalResults % pageSize == 0 ? 0 : 1));
//		return curBooks;
//	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
	
		CategoryServiceImpl cs = new CategoryServiceImpl();
		List<Category> t = cs.createCategory(1);
		for (Category cat : t) {
			System.out.println(cat.getName());
			/*for (Category cat1 : cat.getSubCats()) {
				System.out.println("---------" + cat1.getName());
			}*/
		}
		
	}
}
