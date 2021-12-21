package com.epam.unittesting.service;

import com.epam.unittesting.BaseTest;
import com.epam.unittesting.dto.request.UserRequestDto;
import com.epam.unittesting.dto.response.UserResponseDto;
import com.epam.unittesting.exception.EntityAlreadyExistException;
import com.epam.unittesting.exception.EntityNotFoundException;
import com.epam.unittesting.mapper.Mapper;
import com.epam.unittesting.model.User;
import com.epam.unittesting.repository.UserRepository;
import com.epam.unittesting.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest extends BaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Mapper<User, UserRequestDto> requestMapper;
    @Mock
    private Mapper<User, UserResponseDto> responseMapper;

    @InjectMocks
    private final UserService userService = Mockito.spy(new UserServiceImpl());

    private UserRequestDto requestDto;
    private User user;
    private UserResponseDto responseDto;

    @BeforeEach
    void setUp() {
        requestDto = generateUserRequestDto();
        responseDto = generateUserResponseDto();
        user = generateUser();
    }

    @AfterEach
    void tearDown() { }


    /**
     * @see UserService#create(UserRequestDto)
     */
    @Test
    void create_success() {
        // mock your methods
        when(userRepository.findUserByEmail(requestDto.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(requestMapper.mapToOBJ(any(UserRequestDto.class))).thenReturn(user);
        when(responseMapper.mapToDTO(any(User.class))).thenReturn(responseDto);

        // assumptions
        assumeTrue(user.getEmail().equals(requestDto.getEmail()));

        // test method
        UserResponseDto createdUserResponse = userService.create(requestDto);

        // assertions

        // assertEquals(createdUserResponse, responseDto);

        assertEquals(createdUserResponse.getName(), responseDto.getName());
        assertEquals(createdUserResponse.getSurname(), responseDto.getSurname());
        assertEquals(createdUserResponse.getEmail(), responseDto.getEmail());
        assertEquals(createdUserResponse.getPassword(), responseDto.getPassword());

        // verifies
        verify(userRepository, times(1)).save(any(User.class));
    }

    /**
     * @see UserService#create(UserRequestDto)
     */
    @Test
    void create_throws_entity_already_exist_exception() {
        // mock your methods
        when(userRepository.findUserByEmail(requestDto.getEmail())).thenReturn(Optional.of(user));

        // test method
        Assertions.assertThrows(EntityAlreadyExistException.class, () -> userService.create(requestDto));
    }

    /**
     * @see UserService#get(Long)
     */
    @Test
    void get_success() {
        // mock your methods
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(responseMapper.mapToDTO(any(User.class))).thenReturn(responseDto);

        // test method
        UserResponseDto userById = userService.get(1L);

        // assertions
        assertEquals(userById.getId(), responseDto.getId());
        assertEquals(userById.getName(), responseDto.getName());
        assertEquals(userById.getSurname(), responseDto.getSurname());
        assertEquals(userById.getEmail(), responseDto.getEmail());
        assertEquals(userById.getPassword(), responseDto.getPassword());

        // verifies
        verify(userRepository, times(1)).findById(any(Long.class));
    }

    /**
     * @see UserService#get(Long) (UserRequestDto)
     */
    @Test
    void get_throws_entity_not_found_exception() {
        // mock your methods
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        // test method
        Assertions.assertThrows(EntityAlreadyExistException.class, () -> userService.get(10L));
    }

}
