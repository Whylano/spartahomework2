package com.example.spartahomework2.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends TimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    //생성자 - 댓글을 생성하기 위해서는 게시글과 작성자가 필요하다.
    public Comment(Member member, Post post, String content) {

        this.member = member;
        this.post = post;
        this.content = content;

    }

    public void update(String content){
        this.content = content;
    }

    public boolean checkMemberByMemberId(Long memberId){

        return this.member.getId().equals(memberId);
    }

    public boolean checkPostByPostId(Long postId){

        return this.post.getId().equals(postId);
    }

}
