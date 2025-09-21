package com.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
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
public class GetBookControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@Test
	void test() throws Exception {

		Mockito.when(bookService.getAllBooks()).thenReturn(
	            Collections.singletonList(new Book("Test Book", "John Doe", 560, "Spring Boot 3"))
	        );
		
		mockMvc.perform(get("/api/books/allBooks")
				.contentType(MediaType.APPLICATION_JSON) )
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title").value("Test Book"));
			
		;
	}
}
