package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private final BookService bookService;
	
	public BookController(BookService bookService) {		
        this.bookService = bookService;
    }
	
	@Operation(summary = "Get all books", description = "Retrieve a list of all books in the store")
	@GetMapping("/allBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		log.info("get all existing books ");
		List<Book> books = bookService.getAllBooks();
		log.debug("Books return with size " + books.size());
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	@Operation(summary = "Get book by ID", description = "Retrieve a book from the store based on its ID")
	@GetMapping("/{id}")
	public ResponseEntity<Book> findBookById(@PathVariable Long id) {
		log.info("fetching Book with id {} : " + id);
		Book book = bookService.findBookById(id);
		if(book != null) {
			log.debug("found book with id " + id + " and title " + book.getTitle());
		}	
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@Operation(summary = "Create a book", description = "Create a book and save its details in the store")
	@PostMapping("/createBook")
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        log.info("📥 Received Book: " + book);
        Book saved = bookService.createBook(book);
        log.debug("Book create successfully with Id " + saved.getId());
        return ResponseEntity.ok(saved);
    }

	@Operation(summary = "Update a book", description = "update book's and save its new details in the store")
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
		log.info("Update Book with details " + book);
	    book.setId(id);
		Book updatedBook = bookService.updateBook(book, id);
		log.debug("Book updated successfully with Id " + id);
		return ResponseEntity.ok(updatedBook);
	}
	
	@Operation(summary = "Delete a book", description = "Delete a book based on its id from the store")
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws Exception {
		log.warn("delete Book called with Id " + id);
		bookService.deleteBook(id);
		log.info("Book with id " + id + " deleted successfully.");
		return ResponseEntity.ok().build();
	}
	
	@Operation(summary = "Save a book details", description = "save book's and its new details in the store")
	@PostMapping("/saveBook")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
		log.info("save new Book with details " + book);
		Book savedBook = bookService.saveBook(book);
		log.debug("Book saved with Id " + savedBook.getId());
	    return ResponseEntity.ok(savedBook);
	}

}
