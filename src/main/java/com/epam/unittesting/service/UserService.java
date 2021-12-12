package com.epam.unittesting.service;

import com.epam.unittesting.dto.request.UserRequestDto;
import com.epam.unittesting.dto.response.UserResponseDto;

public interface UserService {

    /**
     * Create new User
     * @param userRequestDto User details.
     * @return recently created User
     */
    UserResponseDto create(UserRequestDto userRequestDto);

    /**
     * Get already created User
     * @param id searched user's id
     * @return find User
     */
    UserResponseDto get(Long id);

}
