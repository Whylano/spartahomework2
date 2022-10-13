package com.example.spartahomework2.domain.service;

import com.example.spartahomework2.domain.entity.Comment;
import com.example.spartahomework2.web.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void create(Long postId, CommentDto.Req reqDto);

    List<CommentDto.Res> findAllByPostId(Long postId);

    void update(Long postId, Long commentId, CommentDto.Req reqDto);

    void delete(Long postId, Long commentId);

}
