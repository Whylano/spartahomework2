package com.example.spartahomework2.web.dto;

import com.example.spartahomework2.domain.entity.Comment;
import com.example.spartahomework2.domain.entity.Member;
import com.example.spartahomework2.domain.entity.Post;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    public static class Req{

        private final String content;

        @JsonCreator
        public Req(@JsonProperty("content") String content) {
            this.content = content;
        }

        public Comment toEntity(Member member, Post post){

            return new Comment(member, post, content);

        }
    }

    @Getter
    public static class Res{

        private final Long id;
        private final String content;
        private final String author;
        private final LocalDateTime createdAt;

        public Res(Comment comment, String author) {
            this.id = comment.getId();
            this.content = comment.getContent();
            this.author = author;
            this.createdAt = comment.getCreatedAt().toLocalDateTime();
        }
    }
}
