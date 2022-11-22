package com.example.week_7_task.repositories;

import com.example.week_7_task.models.Comment;
import com.example.week_7_task.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
}
