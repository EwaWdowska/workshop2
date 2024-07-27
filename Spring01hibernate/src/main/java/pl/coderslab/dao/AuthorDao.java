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
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveAuthor(Author author) {
        entityManager.persist(author);
    }

    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }

    public void update(Author author ) {
        entityManager.merge(author);
    }

    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

}