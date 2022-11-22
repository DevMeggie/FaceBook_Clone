package com.example.week_7_task.services;

import com.example.week_7_task.dto.CommentResponseDto;

public interface CommentService {
    String createComment(CommentResponseDto commentCreateDto, Long userId, Long postId);

    String deleteComment (Long commentId);
}
