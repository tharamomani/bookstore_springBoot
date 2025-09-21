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
import org.springframework.test.web.servlet.MockMvc;

import com.bookstore.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookNotFoundControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void testDeleteBook() throws Exception {
    	
        Mockito.when(bookRepository.findById(994L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/994") )
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.details").value("Book not found with ID: 994"))
                .andExpect(jsonPath("$.status").value(404));
    }
}

