package com.library.userservice.entity;

import com.library.userservice.utils.DBConstants;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 26/12/2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = DBConstants.TABLE_USER)
public class User {

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

    @Column(name = DBConstants.EMAIL, nullable = false)
    private String email;

    @Column(name = DBConstants.USERNAME, nullable = false, unique = true)
    private String username;

    @Column(name = DBConstants.PASSWORD, nullable = false)
    private String password;

    @Column(name = DBConstants.ENABLED, nullable = false)
    private boolean enabled;

    @Column(name = DBConstants.CREATION_DATE, nullable = false)
    private LocalDate createDate;

    @Column(name = DBConstants.UPDATE_DATE)
    private LocalDate updateDate;

    @Column(name = DBConstants.DELETED_DATE)
    private LocalDate deleteDate;

    @OneToMany(mappedBy = DBConstants.TABLE_ROLE, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<UserRoles> userRoles;
}