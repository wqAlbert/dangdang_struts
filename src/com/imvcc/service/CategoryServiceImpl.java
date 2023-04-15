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
	 * ����Ŀ¼
	 */
	public List<Category> createCategory(Integer parentId) {
		return catDao.findCatByParentId(parentId);
	}
	/**
	 * ����Id��������Ŀ¼
	 */
	public Category findCatById(Integer id) {
		if(id==null) {
			
			return null;
		}
		Category cat=catDao.findCatById(id);
		
		return cat;
	}

	/**
	 * ��ȡ��ǰͼ����Ϣ��Ŀ¼��Ŀ¼�µ�ͼ�飬��ҳ��
	 */
//	public CurrentBooks getCurrrentBooks(Category category, Integer sc,
//			Integer ssc, Integer page, int pageSize) {
//		int totalResults = 0;
//		CurrentBooks curBooks = new CurrentBooks();
//		log.debug("Dang:׼����ʼ����ָ��Ŀ�µ�ͼ��");
//		if (ssc == null) {
//			log.debug("Dang:û������Ŀ¼����������Ŀ¼����ͼ��");
//			totalResults = category.getProductCount();
//			curBooks.setBooks(bookService.findBookByCatId(sc, page, pageSize));
//			curBooks.setCurCat(category);
//		} else {
//			log.debug("Dang:������Ŀ¼���������е�ͼ��");
//			for (Category cat : category.getSubCats()) {
//				if (cat.getId().equals(ssc)) {
//					totalResults = cat.getProductCount();
//					curBooks.setCurCat(cat);
//					break;
//				}
//			}
//			curBooks.setBooks(bookService.findBookByCatId(ssc, page, pageSize));
//		}
//		log.debug("Dang:������ҳ��");
//		curBooks.setTotalPages(totalResults / pageSize
//				+ (totalResults % pageSize == 0 ? 0 : 1));
//		return curBooks;
//	}
	/**
	 * ����
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
