package com.bookstore.controller;

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.MyBookList;
import com.bookstore.service.BookService;
import com.bookstore.service.MyBookListService;


@Controller
public class BookController {

	@Autowired
	private MyBookListService myBookListService;
	
	
     @Autowired
    private BookService bookService; 
    
    
	
	
@GetMapping("/")
	public String home() {
		return "home";
		
	}
@GetMapping("book_register")
public String bookRegister() {

return "bookRegister";	
}
	
	@GetMapping("/available_book")
	public ModelAndView getAllBooks() {
		List<Book>list=bookService.getAllBooks();
		
//		ModelAndView m =new ModelAndView();
//		m.setViewName("booklist");
//		m.addObject("book",list)
		return new ModelAndView("bookList","book",list);
		
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		bookService.save(b);
		
		return "redirect:/available_book";
	}
	@GetMapping("/mybooks")
		public String getMyBooks(Model model) {
			List<MyBookList> list=myBookListService.getAllMyBooks();
		model.addAttribute("book",list);
		return"mybooks";
			
		}
	@GetMapping("/mylist/{id}")
	public String getMyList(@PathVariable int id) {
		Book book=bookService.getBookById(id);
		MyBookList mBookList= new MyBookList(book.getId(),book.getName(),book.getAuthor(),book.getPrice());
		myBookListService.saveMyBooks(mBookList);
		return "redirect:/mybooks";
	}
	
	
	}
	
	

