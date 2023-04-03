package com.library.entity;

import com.library.utils.DBConstants;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = DBConstants.TABLE_BOOK)
public class Book {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = DBConstants.ID, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = DBConstants.TITLE, nullable = false)
    private String title;

    @Column(name = DBConstants.PAGE, nullable = false)
    private Integer page;

    @Column(name = DBConstants.PUBLISH_YEAR, nullable = false)
    private Integer publishYear;

    @Column(name = DBConstants.EDITION, nullable = false, length = 25)
    private String edition;

    @Lob
    @Column(name = DBConstants.IMAGE)
    private byte[] image;

    @Column(name = DBConstants.DESCRIPTION, nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = DBConstants.TABLE_BOOK, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<AuthorsBooks> authorsBooks;

    @OneToMany(mappedBy = DBConstants.TABLE_BOOK, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<BooksPublishers> booksPublishers;

    @OneToMany(mappedBy = DBConstants.TABLE_BOOK, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<GenresBooks> genresBooks;
}