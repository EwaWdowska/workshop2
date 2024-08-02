package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT b FROM Book b WHERE b.title =?1")
    List <Book> findBookByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.category= :cat")
    List<Book> findByCategory (@Param("cat") Category category);


    @Query("SELECT b FROM Book b WHERE b.rating BETWEEN ?1 AND ?2")
    List<Book> getByRatingBetween(int min, int max);

    @Query("SELECT b FROM Book b WHERE b.publisher = ?1")
    List<Book> findBooksByPublisher(Publisher publisher);

    @Query(value = "SELECT * FROM books WHERE category_id = ?1 ORDER BY title LIMIT 1", nativeQuery = true)
    Book findFirstBookFromCategory(long categoryId);

}
