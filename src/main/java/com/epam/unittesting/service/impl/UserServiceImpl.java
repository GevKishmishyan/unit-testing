package com.epam.unittesting.service.impl;

import com.epam.unittesting.dto.request.UserRequestDto;
import com.epam.unittesting.dto.response.UserResponseDto;
import com.epam.unittesting.exception.EntityAlreadyExistException;
import com.epam.unittesting.exception.EntityNotFoundException;
import com.epam.unittesting.mapper.Mapper;
import com.epam.unittesting.model.User;
import com.epam.unittesting.repository.UserRepository;
import com.epam.unittesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Mapper<User, UserRequestDto> requestMapper;
    @Autowired
    private Mapper<User, UserResponseDto> responseMapper;

    /**
     * @see UserService#create(UserRequestDto)
     */
    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        // check if the user with the requested EMAIL already exists or not
        boolean isUserExist = userRepository.findUserByEmail(userRequestDto.getEmail()).isPresent();
        if (isUserExist) {
            throw new EntityAlreadyExistException();
        }

        // convert requestDto into User object
        User user = requestMapper.mapToOBJ(userRequestDto);

        // save the User object in the database
        User createdUser = userRepository.save(user);

        // convert User object into responseDto and return that DTO
        return responseMapper.mapToDTO(createdUser);
    }

    @Override
    public UserResponseDto get(Long id) {
        // find user from the database
        Optional<User> userById = userRepository.findById(id);

        // check if the user with the requested ID exists or not
        if (userById.isEmpty()) {
            throw new EntityNotFoundException();
        }

        // convert User object into responseDto and return that DTO
        return responseMapper.mapToDTO(userById.get());
    }

}
