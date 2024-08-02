package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.domain.Author;
import pl.coderslab.domain.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);

    Author findByPesel(String pesel);

    List<Author> findByLastName(String lastname);

    @Query(value = "SELECT a FROM Author a  WHERE a.email LIKE ?1%")
    List<Author> findAuthorsByEmailLike(String email);

    @Query(value = "SELECT a FROM Author a WHERE a.pesel LIKE ?1%")
    List<Author> findAuthorsByPeselLike(String pesel);

}
