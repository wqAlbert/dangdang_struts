package com.imvcc.actions.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.imvcc.domain.Book;
import com.imvcc.service.BookService;
import com.imvcc.service.ServiceFacotry;

public class NewAction {
	Logger log =Logger.getLogger(NewAction.class);
	
	private BookService bookService=ServiceFacotry.getBookService();
	
	private List<Book> newBooks;
	
	private int size = 8;
	
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
		log.debug("Dang:正在加载最新上架图书");
		newBooks = bookService.findNewBook(size);
		return "success";
	}

}
