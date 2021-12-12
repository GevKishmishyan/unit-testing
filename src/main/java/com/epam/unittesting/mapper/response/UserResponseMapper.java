package com.epam.unittesting.mapper.response;

import com.epam.unittesting.dto.response.UserResponseDto;
import com.epam.unittesting.mapper.Mapper;
import com.epam.unittesting.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements Mapper<User, UserResponseDto> {

    @Override
    public UserResponseDto mapToDTO(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public User mapToOBJ(UserResponseDto userResponseDto) {
        return User.builder()
                .id(userResponseDto.getId())
                .name(userResponseDto.getName())
                .surname(userResponseDto.getSurname())
                .email(userResponseDto.getEmail())
                .password(userResponseDto.getPassword())
                .build();
    }
}
