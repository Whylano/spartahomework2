package com.example.spartahomework2.domain.service;

import com.example.spartahomework2.web.dto.PostDto;

import java.util.List;

public interface PostService {

    void create(PostDto.Req reqDto);

    List<PostDto.Res> readAll();

    PostDto.DetailRes readOneById(Long id);

    void update(Long id, PostDto.Req reqDto);

    void delete(Long id);

}
