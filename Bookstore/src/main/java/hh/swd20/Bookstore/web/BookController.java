package hh.swd20.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	//tätä ei varmaan enää tarvitse
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String bookPage() {
		return("bookstore");
	}
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String listBooks(Model model) {
		List<Book> books = (List<Book>) repository.findAll();
		model.addAttribute("books", books); //välitetään tyhjä kirjalista 
		return "booklist";
	}
}
