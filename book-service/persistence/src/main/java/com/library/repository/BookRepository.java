package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 22/11/2022
 */
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("from Book b where b.title = :#{#title}")
    Optional<List<Book>> findByCriteria(String title);

    List<Book> findBookByTitle(String title);

    List<Book> findBooksByPublishYear(Integer year);

    @Query("from Book b left join fetch b.authorsBooks a where a.author.firstName=:#{#firstName} and a.author.lastName=:#{#lastName}")
    List<Book> findBooksByAuthorFirstNameAndLastName(String firstName, String lastName);

    @Query("from Book b left join fetch b.booksPublishers p where p.publisher.name=:#{#publisherName}")
    List<Book> findBooksByPublisher(String publisherName);

    @Query("from Book b left join fetch b.genresBooks g where g.genre.name=:#{#genreName}")
    List<Book> findBooksByGenre(String genreName);
}