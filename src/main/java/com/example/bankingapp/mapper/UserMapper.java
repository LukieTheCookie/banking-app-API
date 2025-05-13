package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.UserDto;
import com.example.bankingapp.entity.User;

public class UserMapper {
    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getAccounts()
        );
    }

    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getAccounts()
        );
    }
}
