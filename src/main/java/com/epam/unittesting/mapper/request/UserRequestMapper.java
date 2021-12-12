package com.epam.unittesting.mapper.request;

import com.epam.unittesting.dto.request.UserRequestDto;
import com.epam.unittesting.mapper.Mapper;
import com.epam.unittesting.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper implements Mapper<User, UserRequestDto> {

    @Override
    public UserRequestDto mapToDTO(User user) {
        return UserRequestDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public User mapToOBJ(UserRequestDto userRequestDto) {
        return User.builder()
                .name(userRequestDto.getName())
                .surname(userRequestDto.getSurname())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .build();
    }
}
