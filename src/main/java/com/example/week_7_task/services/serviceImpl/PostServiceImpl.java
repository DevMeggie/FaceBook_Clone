package com.example.week_7_task.services.serviceImpl;

import com.example.week_7_task.dto.PostRequestDto;
import com.example.week_7_task.dto.PostResponseDto;
import com.example.week_7_task.exception.NotFoundException;
import com.example.week_7_task.models.Post;
import com.example.week_7_task.models.User;
import com.example.week_7_task.repositories.CommentRepo;
import com.example.week_7_task.repositories.PostRepo;
import com.example.week_7_task.repositories.UserRepo;
import com.example.week_7_task.services.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service


public class PostServiceImpl implements PostService {

    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;

    public PostServiceImpl(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public String createPost(PostRequestDto postRequestDto) {

        User user = userRepo.findById(postRequestDto.getUserId()).get();
        Post post = new Post();
        //BeanUtils.copyProperties(postRequestDto, post);
        post.setUser(user);
        post.setContent(postRequestDto.getContent());
        postRepo.save(post);

        return "post created successful";
    }

    @Override
    public List<PostResponseDto> fetchAllUserPost(Long userId) {
        List<Post> posts = postRepo.findPostsByUserId(userId);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        posts.forEach(post -> {
            PostResponseDto postResponseDto = new PostResponseDto();
            postResponseDto.setPostId(post.getPostId());
            postResponseDto.setContent(post.getContent());
        });

        return postResponseDtoList;
    }

    @Override
    public String deletePost(Long postId) {

        Post post = postRepo.findById(postId).orElseThrow(()-> new NotFoundException("post not found"));
        postRepo.delete(post);

       return "post deleted";

    }

    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAllByOrderByPostIdDesc();
    }


}





