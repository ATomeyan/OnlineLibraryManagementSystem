package com.library.entity;

import com.library.utils.DBConstants;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 25/11/2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = DBConstants.TABLE_PUBLISHER)
public class Publisher {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = DBConstants.ID, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = DBConstants.NAME, nullable = false)
    private String name;

    @OneToMany(mappedBy = DBConstants.TABLE_PUBLISHER, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<BooksPublishers> booksPublishers;
}