package com.comic.users.domain;

import com.comic.core.domain.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
    private String firstName;
    private String lastName;
    private UserStatus status;
}
