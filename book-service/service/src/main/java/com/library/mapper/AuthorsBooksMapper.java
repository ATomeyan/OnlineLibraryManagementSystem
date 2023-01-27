package com.library.mapper;

import com.library.entity.Author;
import com.library.entity.AuthorsBooks;
import com.library.entity.Book;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 14/12/2022
 */
public final class AuthorsBooksMapper {

    private AuthorsBooksMapper() {
    }

    public static List<AuthorsBooks> requestDtoToEntity(List<UUID> authorsIds, Book book) {

        AuthorsBooks authorsBooks;
        List<AuthorsBooks> authorsBooksList = new LinkedList<>();
        for (UUID id : authorsIds) {
            authorsBooks = new AuthorsBooks();

            authorsBooks.setAuthor(Author.builder().id(id).build());
            authorsBooks.setBook(book);

            authorsBooksList.add(authorsBooks);
        }
        return authorsBooksList;
    }
}
