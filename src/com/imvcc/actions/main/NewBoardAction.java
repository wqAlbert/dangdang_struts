package com.imvcc.actions.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.imvcc.domain.Book;
import com.imvcc.service.BookService;
import com.imvcc.service.ServiceFacotry;

public class NewBoardAction {
	Logger log =Logger.getLogger(NewAction.class);
	
	private BookService bookService=ServiceFacotry.getBookService();
	
	private List<Book> newHotBooks;
	
	private int size = 8;
	
	

	public List<Book> getNewHotBooks() {
		return newHotBooks;
	}

	public void setNewHotBooks(List<Book> newHotBooks) {
		this.newHotBooks = newHotBooks;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String execute() {
		log.debug("Dang:正在加载新书热卖榜");
		//用新上架图书作为新书热卖榜
		newHotBooks = bookService.findNewBook(size);
		return "success";
	}
}
