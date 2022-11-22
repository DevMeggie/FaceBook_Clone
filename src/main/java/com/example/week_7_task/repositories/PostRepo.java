package com.example.week_7_task.repositories;

import com.example.week_7_task.models.Post;
import com.example.week_7_task.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM posts WHERE user_user_id=?", nativeQuery = true)
    List<Post> findPostsByUserId(Long userId);

    Post findPostByPostId(Long postId);

    List<Post> findPostsByUser(User user);

    List<Post> findAllByOrderByPostIdDesc();


}
