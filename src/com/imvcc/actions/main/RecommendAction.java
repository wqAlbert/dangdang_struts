package com.imvcc.actions.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.imvcc.domain.Book;
import com.imvcc.service.BookService;
import com.imvcc.service.ServiceFacotry;

public class RecommendAction {
    Logger log =Logger.getLogger(RecommendAction.class);
	
	private BookService bookService=ServiceFacotry.getBookService();
	
	private List<Book> newBooks;//动态数据
	
	private int size;
	
	
	public List<Book> getNewBooks() {
		return newBooks;
	}

	public void setNewBooks(List<Book> newBooks) {
		this.newBooks = newBooks;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String execute() {
		log.debug("Dang:正在加载编辑推荐图书");
		newBooks = bookService.findNewBook(size);
		System.out.println("Action:"+newBooks.size());
		return "success";
	}
}
