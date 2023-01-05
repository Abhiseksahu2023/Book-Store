package com.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepoTest {


	
	@Autowired
	private BookRepository bookRepository;
	
	//junit test for same employee
	@Test
	@Order(1)
	public void saveBookTest() {
		
		 Book book= new Book();
		 book.setAuthor("kkk");
		 book.setName("AAA");
		 book.setPrice("900");
		
		
		bookRepository.save(book);
	Assertions.assertThat(book.getId()).isGreaterThan(0);
		
	}
	@Test
	@Order(2)
	public void getBookTest() {
          Book   book=bookRepository.findById(1).get();
             Assertions.assertThat(book.getId()).isEqualTo(1);
	}
	
	@Test
	@Order(3)
	public void getListOfBookTest() {
		List<Book> books=bookRepository.findAll();
            
		Assertions.assertThat(books.size()).isGreaterThan(0);
	}
	@Test
	@Order(4)
	public void deleteBookTest() {
		bookRepository.deleteById(1);
	Assertions.assertThat(bookRepository.existsById(1)).isFalse();
	
	
	}
	
}
