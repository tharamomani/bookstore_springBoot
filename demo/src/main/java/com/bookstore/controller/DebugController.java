package com.bookstore.controller;

import com.bookstore.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @PostMapping("/book")
    public ResponseEntity<String> debugBook(@RequestBody Book book) {
        System.out.println("📥 RAW Book: " + book);
        return ResponseEntity.ok(book.toString());
    }
}
