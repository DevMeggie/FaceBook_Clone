package com.example.week_7_task.services;

import com.example.week_7_task.dto.UserLoginDto;
import com.example.week_7_task.dto.UserResponseDto;
import com.example.week_7_task.dto.UserSignUpDto;
import com.example.week_7_task.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User userSignUp(UserSignUpDto userSignUpDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUser(Long userId);

    void deleteUser(Long userId);

   Optional<User> userLogin (UserLoginDto userLoginDto);
}
