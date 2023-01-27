package com.library.entity;

import com.library.utils.DBConstants;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 27.11.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = DBConstants.TABLE_BOOKS_PUBLISHERS)
public class BooksPublishers {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = DBConstants.ID, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DBConstants.BOOK_ID, nullable = false, referencedColumnName = DBConstants.ID)
    @ToString.Exclude
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DBConstants.PUBLISHER_ID, nullable = false, referencedColumnName = DBConstants.ID)
    @ToString.Exclude
    private Publisher publisher;
}