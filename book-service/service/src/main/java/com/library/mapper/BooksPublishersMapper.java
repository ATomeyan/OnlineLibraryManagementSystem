package com.library.mapper;

import com.library.entity.Book;
import com.library.entity.BooksPublishers;
import com.library.entity.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 14/12/2022
 */
public final class BooksPublishersMapper {

    private BooksPublishersMapper() {
    }

    public static List<BooksPublishers> requestDtoToEntity(List<UUID> publisherId, Book book) {

        BooksPublishers booksPublishers;
        List<BooksPublishers> booksPublishersList = new ArrayList<>();

        for (UUID id : publisherId) {
            booksPublishers = new BooksPublishers();
            booksPublishers.setPublisher(Publisher.builder().id(id).build());
            booksPublishers.setBook(book);

            booksPublishersList.add(booksPublishers);
        }

        return booksPublishersList;
    }
}