package com.buildingMachineRental.user.infrastructure.api;

import com.buildingMachineRental.user.domain.User;

public class UserMapper {

    static User mapUserFromDto(UserDto userDto, Long id){
        return User.builder()
                .userId(id)
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .password(userDto.getPassword())
                .build();
    }

    public static UserDto mapUserToDto(User user){
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
