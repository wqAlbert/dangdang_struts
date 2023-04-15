package com.imvcc.dao;

import java.util.List;

import com.imvcc.domain.Book;

public interface BookDao {
	public List<Book> findBookByCatId(Integer id,int firstResult,int maxResult);
	public List<Book> findNewBook(int size);
	public List<Book> findHotBook(int size);
	public List<Book> findRecommendBook(int size);
	public List<Book> findNewBoardBook(int size);
}
