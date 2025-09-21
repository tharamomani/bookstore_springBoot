package com.bookstore.service;

import java.util.List;

import com.bookstore.entity.Book;

public interface BookService {
	
	Book findBookById(Long id);
	
	List<Book> getAllBooks();
	
	Book createBook(Book book);
	
	Book updateBook(Book book, Long id);
	
	void deleteBook(Long id) throws Exception;
	
	Book saveBook(Book book);

}
