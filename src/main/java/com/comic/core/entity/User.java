package com.comic.core.entity;

import com.comic.core.domain.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", allocationSize = 10)
    public Long id;

    @Column(name = "email", unique = true)
    public String email;

    @Column(name = "username", unique = true)
    public String username;

    @Column(name = "avatar_url")
    public String avatarUrl;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public UserStatus status = UserStatus.ACTIVE;
}
