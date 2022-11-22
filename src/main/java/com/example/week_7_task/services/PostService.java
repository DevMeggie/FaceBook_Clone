package com.example.week_7_task.services;

import com.example.week_7_task.dto.PostRequestDto;
import com.example.week_7_task.dto.PostResponseDto;
import com.example.week_7_task.models.Post;

import java.util.List;

public interface PostService {

    String createPost(PostRequestDto postRequestDto);

   List<PostResponseDto> fetchAllUserPost(Long userId);

   String deletePost(Long postId);

    List<Post> getAllPosts();
}
