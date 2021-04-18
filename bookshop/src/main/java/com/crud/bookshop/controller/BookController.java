package com.crud.bookshop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.crud.bookshop.domain.Book;
import com.crud.bookshop.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Value("${msg.rows_per_page}")
	private String ROW_PER_PAGE;
	
	@GetMapping(value="/books")
	public String getBooks(Model model,@RequestParam(value="page",defaultValue="1") int pageNumber) {
		int rows=Integer.parseInt(ROW_PER_PAGE);
		List<Book> books =bookService.findAll(pageNumber,rows);
		int count=bookService.count();
		boolean hasPrev=pageNumber>1;
		boolean hasNext=(pageNumber*rows)<count;
		model.addAttribute("books",books);
		model.addAttribute("hasPrev",hasPrev);
		model.addAttribute("hasNext",hasNext);
		model.addAttribute("prev",pageNumber-1);
		model.addAttribute("next",pageNumber+1);
		return "book-list";
	}
	
	@GetMapping(value="/books/{bookId}")
	public String getBook(Model model,@PathVariable int bookId) {
		Book book=bookService.findById(bookId);
		model.addAttribute("book",book);
		return "book";
	}
	
	@GetMapping(value="/books/edit/add")
	public String showAddBook(Model model) {
		Book book=new Book();
		model.addAttribute("book",book);
		model.addAttribute("add",true);
		return "book-edit";
	}
	
	@PostMapping(value="/books/edit/add")
	public String addBook(Model model,@ModelAttribute("book") Book book) {
		bookService.add(book);
		return "redirect:/books/"+String.valueOf(book.getId());
	}
	
	@GetMapping(value="/books/{bookId}/delete")
	public String showDeleteBookById(Model model,@PathVariable int bookId) {
		Book book=bookService.findById(bookId);
		model.addAttribute("allowDelete",true);
		model.addAttribute("book",book);
		return "book";
	}
	
	@PostMapping(value="/books/{bookId}/delete")
	public String deleteBookById(Model model,@PathVariable int bookId) {
		bookService.deleteById(bookId);
		return "redirect:/books";
	}
	
	@GetMapping(value="/books/{bookId}/edit")
	public String showEditBook(Model model,@PathVariable int bookId) {
		Book book=bookService.findById(bookId);
		model.addAttribute("book",book);
		model.addAttribute("add",false);
		return "book-edit";
	}
	
	@PostMapping(value="/books/{bookId}/edit")
	public String updateBook(Model model,@PathVariable int bookId,@ModelAttribute("book") Book book) {
		book.setId(bookId);
		bookService.update(book);
		return "redirect:/books/"+String.valueOf(bookId);
	}
	
}
