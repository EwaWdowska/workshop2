package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.domain.Author;
import pl.coderslab.domain.Book;
import pl.coderslab.domain.Category;
import pl.coderslab.domain.Publisher;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> getBooksByCategory(Category category);

    List<Book> readByCategory_Id(long id);

    List<Book> getBooksByAuthorsContains(Author author);

    List<Book> getBooksByPublisher(Publisher publisher);

    List<Book> readBooksByRating(int rating);

    Book getFirstByCategoryOrderByTitle(Category category);
}
