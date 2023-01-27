package com.library.mapper;

import com.library.entity.Book;
import com.library.entity.Genre;
import com.library.entity.GenresBooks;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 14/12/2022
 */
public final class GenresBooksMapper {

    private GenresBooksMapper() {
    }

    public static List<GenresBooks> requestDtoToEntity(List<UUID> genreId, Book book) {

        GenresBooks genresBooks;
        List<GenresBooks> genresBooksList = new LinkedList<>();
        for (UUID id : genreId) {
            genresBooks = new GenresBooks();

            genresBooks.setBook(book);
            genresBooks.setGenre(Genre.builder().id(id).build());

            genresBooksList.add(genresBooks);
        }
        return genresBooksList;
    }
}