package com.example.week_7_task.dto;

import lombok.Data;

@Data
public class CommentCreateDto {
    private Long postId;
    private Long userId;
    private String comment;
}
