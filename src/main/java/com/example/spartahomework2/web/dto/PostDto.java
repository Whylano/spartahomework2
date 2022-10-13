package com.example.spartahomework2.web.dto;

import com.example.spartahomework2.domain.entity.Member;
import com.example.spartahomework2.domain.entity.Post;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

public class PostDto {

    @Getter
    public static class Req {

        private final String title;
        private final String content;

        @JsonCreator
        public Req(@JsonProperty("title") String title,
                   @JsonProperty("content") String content) {
            this.title = title;
            this.content = content;
        }

        public Post toEntity(Member member){

            return new Post(member, this.title, this.content);
        }
    }

    @Getter
    public static class Res{

        private final Long id;
        private final String title;
        private final String author;
        private final LocalDateTime createdAt;

        public Res(Post post) {
            this.id = post.getId();;
            this.title = post.getTitle();
            this.author = post.getAuthor();
            this.createdAt = post.getCreatedAt().toLocalDateTime();
        }
    }

    @Getter
    public static class DetailRes{

        private final Long id;
        private final String title;
        private final String author;
        private final String content;
        private final LocalDateTime createdAt;

        public DetailRes(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.author = post.getAuthor();
            this.content = post.getContent();
            this.createdAt = post.getCreatedAt().toLocalDateTime();
        }
    }

}
