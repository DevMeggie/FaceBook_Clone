package com.example.week_7_task.controllers;

import com.example.week_7_task.dto.CommentResponseDto;
import com.example.week_7_task.dto.UserLoginDto;
import com.example.week_7_task.dto.UserResponseDto;
import com.example.week_7_task.dto.UserSignUpDto;
import com.example.week_7_task.models.Post;
import com.example.week_7_task.models.User;
import com.example.week_7_task.services.PostService;
import com.example.week_7_task.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final PostService postService;



    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute UserSignUpDto userSignUpDto) {
        User user = userService.userSignUp(userSignUpDto);
        System.out.println("USER: " + user);
        if (user != null) return "redirect:/api/v1/user/log-in";
        else return "signup";
    }

    @GetMapping("/sign-up")
    public String signUpPage (Model model) {
        UserSignUpDto userSignUpDto = new UserSignUpDto();
        model.addAttribute("userSignupDto", userSignUpDto);
        return "signup";
    }


    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        List<UserResponseDto> userResponseDtoList = userService.getAllUsers();
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.getUser(userId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("user deleted successful", HttpStatus.OK);
    }

    @PostMapping("/log-in")
    public String loginUser (@ModelAttribute UserLoginDto userLoginDto, HttpSession session) {
       Optional<User> validatedUser =  userService.userLogin(userLoginDto);

       if (validatedUser.isPresent()){
           User curUser = validatedUser.get();
           System.out.println("USER: " + curUser);
//           model.addAttribute("authUser", curUser);
           session.setAttribute("authUser", curUser);

           return "redirect:/api/v1/user/portal";
       }
       else  {

           return "/login";
       }
    }

    @GetMapping("/log-out")
    public String logOut(HttpSession session) {
        session.invalidate();
        return  "redirect:/";
    }


    @GetMapping("/log-in")
    public String loginPage () {
        return "login";
    }

    @GetMapping("/portal")
    public String portalHome (Model model) {
        List<Post> posts = postService.getAllPosts();
//        System.out.println("POSTS: " + posts.get(0).getComments());
        model.addAttribute("posts", posts);
        CommentResponseDto commentResponseDto = new CommentResponseDto();
        model.addAttribute("commentResponseDto", commentResponseDto);
        return "portal";
    }


}