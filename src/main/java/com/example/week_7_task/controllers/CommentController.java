package com.example.week_7_task.controllers;


import com.example.week_7_task.dto.CommentResponseDto;
import com.example.week_7_task.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/{postId}/{userId}")
    public String createComment( @PathVariable Long postId, @PathVariable Long userId, @ModelAttribute CommentResponseDto commentResponseDto ) {
        System.out.println("COMMENT RESPONSE: " + commentResponseDto);
        commentService.createComment(commentResponseDto, userId, postId);
//        return new ResponseEntity<>("comment created successfully", HttpStatus.CREATED);
        return "redirect:/api/v1/user/portal";
    }


    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("comment deleted successfully", HttpStatus.OK);
    }

}
