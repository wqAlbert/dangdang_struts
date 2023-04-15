package com.imvcc.service;

import java.util.List;

import com.imvcc.domain.Book;

public interface BookService {
	public List<Book> findBookByCatId(Integer id,int curPage,int pageSize);
	public List<Book> findNewBook(int size);
	public List<Book> findHotBook(int size);
	public List<Book> findRecommendBook(int size);
	public List<Book> findNewBoardBook(int size);
}
