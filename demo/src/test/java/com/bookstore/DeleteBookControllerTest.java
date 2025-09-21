package com.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void testDeleteBook() throws Exception {
    	Book book = new Book("Test Book", "John Doe", 560, "Spring Boot 3");
		book.setId(1l);
        // Mock repository response
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Mockito.doNothing().when(bookRepository).delete(book);

        mockMvc.perform(delete("/api/books/deleteBook/1"))
                .andExpect(status().isOk());
        
        // Verify repository interaction
        Mockito.verify(bookRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(bookRepository, Mockito.times(1)).delete(book);
    }
}

