package com.imvcc.service;

import java.util.List;

import com.imvcc.dao.BookDao;
import com.imvcc.dao.DaoFactory;
import com.imvcc.domain.Book;

public class BookServiceImpl implements BookService{
  /**
   * 
   */
	BookDao bookDao=DaoFactory.getBookDao();
	
	public List<Book> findBookByCatId(Integer id,int curPage,int pageSize) {

		int firstResult=(curPage-1)*pageSize;
		return bookDao.findBookByCatId(id,firstResult,pageSize);
	}
	
	
	public List<Book> findNewBook(int size) {
		List<Book> bookList=bookDao.findNewBook(size);
		System.out.println("Service:newBooklist.Size="+bookList.size());
		return bookList;
	}
	
	public List<Book> findHotBook(int size){
		return bookDao.findHotBook(size);
	}
	
	public List<Book> findRecommendBook(int size){
		List<Book> bookList=bookDao.findRecommendBook(size);
		System.out.println("recommendBooklist.Size="+bookList.size());
		return bookList;
	}

	public List<Book> findNewBoardBook(int size){
		return bookDao.findNewBoardBook(size);
	}
}
