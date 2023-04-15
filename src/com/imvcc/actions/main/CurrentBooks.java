package com.imvcc.actions.main;

import java.util.List;

import com.imvcc.domain.Book;
import com.imvcc.domain.Category;


public class CurrentBooks {
	private Category curCat;
	private List<Book> books;
	private int totalPages;
	public Category getCurCat() {
		return curCat;
	}
	public void setCurCat(Category curCat) {
		this.curCat = curCat;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
