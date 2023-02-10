package com.library.userservice.entity;

import com.library.userservice.utils.DBConstants;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
@Table(name = DBConstants.TABLE_USER_ROLE)
public class UserRoles {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = DBConstants.ID, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DBConstants.USER_ID, referencedColumnName = DBConstants.ID, nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DBConstants.ROLE_ID, referencedColumnName = DBConstants.ID, nullable = false)
    @ToString.Exclude
    private Role role;
}