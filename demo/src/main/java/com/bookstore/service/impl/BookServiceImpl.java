package com.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

	@Autowired
	private final BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
	
	/*
	 * findBookById: find the Book By specified Id 
	 * id: the Id that will be used to get the Book
	 */
	@Override
	public Book findBookById(Long id) {
		// TODO Auto-generated method stub
		log.info("find Book By Id " + id);
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
		log.debug("book found with id " + id);
		return book;
	}

	/*
	 * Get All Books: get all specified books in the database (books table)
	 */
	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		log.info("getAllBooks called: will retun all existing Books ");
		List<Book> books = bookRepository.findAll();
		log.debug("found books with size " + books.size());
		return books;
	}

	@Override
	public Book createBook(Book book) {
		// TODO Auto-generated method stub
		log.info("createBook: will create book with Title " + book.getTitle());
		Book savedBook = bookRepository.save(book);
		log.debug("book created with Id " + savedBook.getId());
		return savedBook;
	}

	@Override
	public Book updateBook(Book book, Long id) {
		log.info("updateBook with id " + id);
		Book extBook = findBookById(id);
		extBook.setAuthor(book.getAuthor());
		extBook.setTitle(book.getTitle());
		extBook.setPrice(book.getPrice());
		extBook.setDescription(book.getDescription());
		log.debug("book updated with id " + extBook.getId() + "  and title " + extBook.getTitle());
		return bookRepository.save(extBook);
	}

	@Override
	public void deleteBook(Long id) throws Exception {
		// TODO Auto-generated method stub
		log.warn("you call deleteBook with Id " + id);	
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with Id " + id));
	    bookRepository.delete(book);
	}

	@Override
	public Book saveBook(Book book) {
		// TODO Auto-generated method stub
		log.info("save Book with title " + book.getTitle());
		Book savedBook = bookRepository.save(book);
		log.debug("Book saved with Id " + savedBook.getId() + "  and Title " + savedBook.getTitle());
		return savedBook;
	}

}
