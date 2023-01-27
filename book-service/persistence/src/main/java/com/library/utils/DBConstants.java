package com.library.utils;

/**
 * @author Artur Tomeyan
 * @date 18/11/2022
 */
public final class DBConstants {

    public static final String ID = "id";
    public static final String BOOK_ID = "book_id";
    public static final String FIRSTNAME = "first_name";
    public static final String LASTNAME = "last_name";
    public static final String NAME = "name";

    public static final String TABLE_AUTHOR = "author";

    public static final String TABLE_AUTHORS_BOOKS = "authors_books";
    public static final String AUTHOR_ID = "author_id";

    public static final String TABLE_BOOK = "book";
    public static final String TITLE = "title";
    public static final String PAGE = "page";
    public static final String PUBLISH_YEAR = "publish_year";
    public static final String EDITION = "edition";
    public static final String IMAGE = "image";
    public static final String DESCRIPTION = "description";

    public static final String TABLE_BOOKS_PUBLISHERS = "books_publishers";
    public static final String PUBLISHER_ID = "publisher_id";

    public static final String TABLE_GENRE = "genre";

    public static final String TABLE_GENRES_BOOKS = "genres_books";
    public static final String GENRE_ID = "genre_id";

    public static final String TABLE_PUBLISHER = "publisher";

    private DBConstants(){}
}