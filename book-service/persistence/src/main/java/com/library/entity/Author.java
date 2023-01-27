package com.library.entity;

import com.library.utils.DBConstants;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = DBConstants.TABLE_AUTHOR)
public class Author {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = DBConstants.ID, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = DBConstants.FIRSTNAME, nullable = false)
    private String firstName;

    @Column(name = DBConstants.LASTNAME, nullable = false)
    private String lastName;

    @OneToMany(mappedBy = DBConstants.TABLE_AUTHOR, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<AuthorsBooks> authorsBooks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return new EqualsBuilder()
                .append(id, author.id)
                .append(firstName, author.firstName)
                .append(lastName, author.lastName)
                .append(authorsBooks, author.authorsBooks)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id).append(firstName)
                .append(lastName)
                .append(authorsBooks)
                .toHashCode();
    }
}