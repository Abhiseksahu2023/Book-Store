package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.entity.MyBookList;
import com.bookstore.repository.BookRepository;


@Service
public class BookService {
	@Autowired
private BookRepository repository;

	public void save(Book b) {
	this.repository.save(b);
	}

	public List<Book> getAllBooks(){
		return repository.findAll();
	}
	
public Book getBookById(int id) {
	return repository.findById(id).get();
}
	
	
	
}