package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;

@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@DataJpaTest
class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	
	@Test  // testataan BookRepositoryn findByTitle()-metodin toimivuutta
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("Atomic Habits");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("James Clear");
    }
	
	@Test
	void createNewBook() {
		Book book = new Book("Mein Kampf", "Adolf Hitler", 1925, 9788897691372L, 20.50, null);
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		Book book = new Book("Mein Kampf", "Adolf Hitler", 1925, 9788897691372L, 20.50, null);
		repository.save(book);
		Iterable<Book> books = repository.findAll();
		assertThat(books).hasSize(3);
		repository.delete(repository.findByTitle("Mein Kampf").get(0));
		books = repository.findAll();
		assertThat(books).hasSize(2);
	}

}
