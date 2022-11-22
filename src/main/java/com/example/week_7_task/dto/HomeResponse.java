package com.example.week_7_task.dto;

import com.example.week_7_task.models.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class HomeResponse {
    private Long postId;
    private Long userId;
    private String username;
    private String postContent;
    private Integer linkes;
    private List<Comment> comments;
}
