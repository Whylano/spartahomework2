package com.example.spartahomework2.web.controller;

import com.example.spartahomework2.domain.service.CommentService;
import com.example.spartahomework2.web.dto.CommentDto;
import com.example.spartahomework2.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts/{post_id}")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseDto<?> create(@PathVariable("post_id") Long postId,
                                 @RequestBody CommentDto.Req reqDto) {

        commentService.create(postId, reqDto);

        return new ResponseDto<>(true, null);
    }

    @GetMapping("/comments")
    public ResponseDto<?> getByPostId(@PathVariable("post_id") Long postId) {

        List<CommentDto.Res> resList = commentService.findAllByPostId(postId);

        return new ResponseDto<>(true, resList);
    }

    @PutMapping("/comments/{comment_id}")
    public ResponseDto<?> update(@PathVariable("post_id") Long postId,
                                 @PathVariable("comment_id") Long commentId,
                                 @RequestBody CommentDto.Req reqDto) {

        commentService.update(postId, commentId, reqDto);

        return new ResponseDto<>(true, null);
    }

    @DeleteMapping("/comments/{comment_id}")
    public ResponseDto<?> delete(@PathVariable("post_id") Long postId,
                                 @PathVariable("comment_id") Long commentId) {

        commentService.delete(postId, commentId);

        return new ResponseDto<>(true, null);
    }

}
