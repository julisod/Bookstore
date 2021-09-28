package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) { 
		return (args) -> {
			log.info("add some departments");
			Category category1 = new Category("scifi");
			crepository.save(category1);
			Category category2 = new Category("fantasy");
			crepository.save(category2);
			Category category3 = new Category("nonfiction");
			crepository.save(category3);
			
			log.info("we will add a couple of example books");
			brepository.save(new Book("Utopia for Realists", "Ruther Bregman", 2014, 9781408893210L, 15.20, category3));
			brepository.save(new Book("Atomic Habits", "James Clear", 2018, 9781847941831L, 23.40, category3));
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};

	}
}
