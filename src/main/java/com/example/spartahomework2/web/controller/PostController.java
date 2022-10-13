package com.example.spartahomework2.web.controller;

import com.example.spartahomework2.domain.service.PostService;
import com.example.spartahomework2.web.dto.PostDto;
import com.example.spartahomework2.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseDto<?> create(@RequestBody PostDto.Req reqDto) {

        postService.create(reqDto);

        return new ResponseDto<>(true, null);
    }

    @GetMapping
    public ResponseDto<?> readAll() {

        List<PostDto.Res> resList = postService.readAll();

        return new ResponseDto<>(true, resList);
    }

    @GetMapping("/{id}")
    public ResponseDto<?> readOneById(@PathVariable("id") Long id) {

        PostDto.DetailRes res = postService.readOneById(id);

        return new ResponseDto<>(true, res);
    }

    @PutMapping("/{id}")
    public ResponseDto<?> update(@PathVariable("id") Long id,
                                 @RequestBody PostDto.Req reqDto) {

        postService.update(id, reqDto);

        return new ResponseDto<>(true, null);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<?> delete(@PathVariable("id") Long id) {

        postService.delete(id);

        return new ResponseDto<>(true, null);
    }

}
