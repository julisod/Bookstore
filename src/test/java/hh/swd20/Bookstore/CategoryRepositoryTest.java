package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@DataJpaTest
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;
	
	@Test
	void createNewCategory() {
		Category category = new Category("newspaper");
		repository.save(category);
		assertThat(category.getId()).isNotNull();
	}

}