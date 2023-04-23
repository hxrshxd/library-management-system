package com.example.library_management_system.service;

import com.example.library_management_system.entity.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {

    public String addAuthor(Author author);
}
