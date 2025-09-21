package com.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class GetBookByIdControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookRepository bookRepository;
	
	@Test
	void test() throws Exception {

		Book book = new Book("Test Book", "John Doe", 560, "Spring Boot 3");
		book.setId(1l);
        // Mock repository response
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		
		mockMvc.perform(get("/api/books/1")
				.contentType(MediaType.APPLICATION_JSON) )
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Test Book"))
				.andExpect(jsonPath("$.author").value("John Doe"))
				.andExpect(jsonPath("$.price").value(560))
				.andExpect(jsonPath("$.description").value("Spring Boot 3"));
			
		;
	}
}
