package com.example.week_7_task.services.serviceImpl;

import com.example.week_7_task.dto.UserLoginDto;
import com.example.week_7_task.dto.UserResponseDto;
import com.example.week_7_task.dto.UserSignUpDto;
import com.example.week_7_task.exception.UserAlreadyExists;
import com.example.week_7_task.models.User;
import com.example.week_7_task.repositories.UserRepo;
import com.example.week_7_task.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;


    @Override
    public User userSignUp(UserSignUpDto userSignUpDto) {
        String userEmail = userSignUpDto.getEmail();
        boolean userExist = userRepo.existsByEmail(userEmail);

        if (userExist) {
            throw new UserAlreadyExists("User with " + userEmail + " already exist");
        }

       User user =new User();
        user.setEmail(userSignUpDto.getEmail());
        user.setFirstName(userSignUpDto.getFirstName());
        user.setLastName(userSignUpDto.getLastName());
        user.setGender(userSignUpDto.getGender());
        user.setPassword(userSignUpDto.getPassword());

        return userRepo.save(user);
    }
 
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = (List<User>) userRepo.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
//        for(User user: users){
//            UserResponseDto userResponseDto = new UserResponseDto();
//            userResponseDto.setId(user.getId());
//            userResponseDto.setEmail(user.getEmail());
//            userResponseDto.setFirstName(user.getFirstName());
//            userResponseDto.setLastName(user.getLastName());
//            userResponseDto.setGender(user.getGender());
//            userResponseDtoList.add(userResponseDto);
//
//        }
//        return userResponseDtoList;


        users.forEach(user -> {

            UserResponseDto userResponseDto = new UserResponseDto();

            userResponseDto.setId(user.getUserId());
            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setFirstName(user.getFirstName());
            userResponseDto.setLastName(user.getLastName());
            userResponseDto.setGender(user.getGender());
            userResponseDtoList.add(userResponseDto);

        });
        return userResponseDtoList;
   }

    @Override
    public UserResponseDto getUser(Long userId) {
        User user = userRepo.findById(userId).get();
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getUserId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setGender(user.getGender());

        return userResponseDto;
    }

    @Override
    public void deleteUser(Long userId) {

        userRepo.deleteById(userId);
    }

    @Override
    public Optional<User> userLogin(UserLoginDto userLoginDto) {
        String userEmail = userLoginDto.getEmail();
        String userPassword = userLoginDto.getPassword();

       return userRepo.findByEmailAndPassword(userEmail, userPassword);

    }
}