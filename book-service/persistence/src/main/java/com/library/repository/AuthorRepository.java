package com.library.repository;

import com.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 28/11/2022
 */
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}