package com.epam.unittesting;

import com.epam.unittesting.dto.request.UserRequestDto;
import com.epam.unittesting.dto.response.UserResponseDto;
import com.epam.unittesting.model.User;

/**
 * Helper class for test instance(s)
 */
public class TestHelper {

    /**
     * Generated UserRequestDto
     */
    protected static UserRequestDto generateUserRequestDto() {
        return UserRequestDto.builder()
                .name("test_name")
                .surname("test_surname")
                .email("test_email@gmail.com")
                .password("test_password")
                .build();
    }

    /**
     * Generated UserResponse
     */
    protected static UserResponseDto generateUserResponseDto() {
        return UserResponseDto.builder()
                .id(1L)
                .name("test_name")
                .surname("test_surname")
                .email("test_email@gmail.com")
                .password("test_password")
                .build();
    }

    /**
     * Generated User
     */
    protected static User generateUser() {
        return User.builder()
                .id(1L)
                .name("test_name")
                .surname("test_surname")
                .email("test_email@gmail.com")
                .password("test_password")
                .build();
    }


}
