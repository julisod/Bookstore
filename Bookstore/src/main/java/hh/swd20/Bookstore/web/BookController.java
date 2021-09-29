package hh.swd20.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String listBooks(Model model) {
		List<Book> books = (List<Book>) brepository.findAll();
		model.addAttribute("books", books); //välitetään kirjalista 
		return "booklist";
	}
	
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public String listCategories(Model model) {
		//List<Category> categories = (List<Category>) crepository.findAll();
		model.addAttribute("categories", crepository.findAll()); //välitetään kategorialista 
		return "categorylist";
	}
	
	@RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        return "addbook";
    } 
	
	@RequestMapping(value = "/addcategory")
    public String addCategory(Model model){
    	model.addAttribute("category", new Category());
        return "addcategory";
    } 
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model){
		model.addAttribute("book", brepository.findById(bookId).get());
        return "editbook";
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        brepository.save(book);
        return "redirect:booklist";
    }  
	
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String saveCategory(Category category){
        crepository.save(category);
        return "redirect:categorylist";
    } 
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	brepository.deleteById(bookId);
        return "redirect:../booklist";
    }   
}
