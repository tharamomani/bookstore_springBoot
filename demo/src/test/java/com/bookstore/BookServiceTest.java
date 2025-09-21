package com.bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.impl.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testSaveBook() {
        Book book = new Book("Test Book", "Spring Boot 3", 560, "John Doe");

        Mockito.when(bookRepository.save(Mockito.any(Book.class)))
               .thenAnswer(invocation -> {
                   Book b = invocation.getArgument(0);
                   b.setId(1L); // Simulate DB assigning ID
                   return b;
               });

        Book savedBook = bookService.saveBook(book);

        Assertions.assertEquals(1L, savedBook.getId());
        
    }

}
