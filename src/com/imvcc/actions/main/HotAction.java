package com.imvcc.actions.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.imvcc.domain.Book;
import com.imvcc.service.BookService;
import com.imvcc.service.ServiceFacotry;

public class HotAction {
	Logger log =Logger.getLogger(HotAction.class);
	
	private BookService bookService=ServiceFacotry.getBookService();
	
	private List<Book> hotBooks;
	
	private int size = 8;
	
	

	public List<Book> getHotBooks() {
		return hotBooks;
	}

	public void setHotBooks(List<Book> hotBooks) {
		this.hotBooks = hotBooks;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String execute() {
		log.debug("Dang:正在加载热销图书");
		hotBooks = bookService.findHotBook(size);
		return "success";
	}

}
