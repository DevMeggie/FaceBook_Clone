package com.example.week_7_task.controllers;


import com.example.week_7_task.dto.PostRequestDto;
import com.example.week_7_task.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create-post")
    public String createPost(@ModelAttribute PostRequestDto postRequestDto) {
        postService.createPost(postRequestDto);
        //return new ResponseEntity<>("post created successful", HttpStatus.CREATED);

        return "redirect:/api/v1/user/portal";
    }


    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost (@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("post deleted", HttpStatus.OK);
    }

    }

