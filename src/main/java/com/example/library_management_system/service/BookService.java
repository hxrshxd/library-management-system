package com.example.library_management_system.service;

import com.example.library_management_system.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    public String addBook(Book book) throws Exception;
}
