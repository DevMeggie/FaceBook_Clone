package com.example.week_7_task.services.serviceImpl;

import com.example.week_7_task.dto.CommentResponseDto;
import com.example.week_7_task.models.Comment;
import com.example.week_7_task.models.Post;
import com.example.week_7_task.models.User;
import com.example.week_7_task.repositories.CommentRepo;
import com.example.week_7_task.repositories.PostRepo;
import com.example.week_7_task.repositories.UserRepo;
import com.example.week_7_task.services.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;

    public CommentServiceImpl(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }


    @Override
    public String createComment(CommentResponseDto commentCreateDto, Long userId, Long postId) {
        User user = userRepo.findById(userId).get();
        Post post = postRepo.findById(postId).get();
        Comment comment = Comment.builder()
                .comment(commentCreateDto.getComment())
                .user(user)
                .post(post)
                .build();
        commentRepo.save(comment);

        return "comment created";
    }

    @Override
    public String deleteComment(Long commentId) {
         commentRepo.deleteById(commentId);
        return "Comment deleted successful";
    }
}

