package com.example.spartahomework2.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends TimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //제목
    @Column(nullable = false)
    private String title;

    //내용
    @Column(nullable = false)
    private String content;

    //작성자 이름
    @Column(nullable = false)
    private String author;

    // N : 1 의 관계일 때 N인 부분의 객체 내부에 1인 객체를 멤버 변수로 선언한다.
    // ManyToOne -> 현재 Post의 입장에서 member와의 관계는 N : 1 -> ManyToOne!
    // JoinColumn -> https://boomrabbit.tistory.com/217 참고
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    //생성자 - 게시글을 생성하기 위해서는 작성자(member)가 반드시 필요하다!
    public Post(Member member, String title, String content) {

        this.title = title;
        this.author = member.getNickname(); //작성자 이름은 작성자에서 꺼내 설정한다.
        this.content = content;
        this.member = member; //작성자 설정
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public boolean checkMemberByMemberId(Long memberId) {

        return this.member.getId().equals(memberId);
    }
}
