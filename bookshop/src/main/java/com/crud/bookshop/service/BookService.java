package com.crud.bookshop.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.crud.bookshop.domain.Book;
import com.crud.bookshop.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
    public List<Book> findAll(int pageNumber,int rowPerPage) {
        List<Book> books = new ArrayList<>();
        Pageable sortedByIdAsc=PageRequest.of(pageNumber-1,rowPerPage,Sort.by("id").ascending());
        bookRepository.findAll(sortedByIdAsc).forEach(books::add);
        return books;
    }
    
    public Book findById(int bookId) {
    	return bookRepository.findById(bookId).orElse(null);
    }
    
    public Book add(Book book) {
    	return bookRepository.save(book);
    }
    
    public void deleteById(int bookId) {
    	bookRepository.deleteById(bookId);
    }
    
    public Book update(Book book)
    {
    	return bookRepository.save(book);
    }
    
    public int count() {
    	return (int)bookRepository.count();
    }
}
