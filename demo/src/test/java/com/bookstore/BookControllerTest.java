package com.bookstore;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bookstore.controller.BookController;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;

@WebMvcTest(BookController.class)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@Test
	void test() throws Exception {

		Book book = new Book("Test Book", "John Doe", 560, "Spring Boot 3");
		Mockito.when(bookService.saveBook(Mockito.any(Book.class))).thenReturn(book);
		
		mockMvc.perform(post("/api/books/saveBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"title\": \"Test Book\", \"author\": \"John Doe\", \"price\": 560, \"description\": \"Spring Boot 3\" } ") )
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").doesNotExist())
				.andExpect(jsonPath("$.title").value("Test Book"));
			
		;
	}

}
