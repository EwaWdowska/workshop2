package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.domain.Author;
import pl.coderslab.domain.Book;
import pl.coderslab.domain.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public List<Book> findAllBookWithPublisher() {
        return entityManager.createQuery("SELECT b from Book b JOIN b.publisher", Book.class)
                .getResultList();
    }

    public List<Book> findAllBookWithPublisherParameter(Publisher publisher) {
        return entityManager.createQuery("SELECT b from Book b WHERE b.publisher=:publisher", Book.class)
                .setParameter("publisher", publisher).getResultList();
    }

    public List<Book> findAllBookWithAuthorParameter(Author author) {
        return entityManager.createQuery("SELECT b from Book b WHERE :author member of b.authors ", Book.class)
                .setParameter("author", author)
                .getResultList();
    }

}