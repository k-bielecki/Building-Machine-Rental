package com.myshop.user.infrastructure;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String nickname;
    private String password;
}
