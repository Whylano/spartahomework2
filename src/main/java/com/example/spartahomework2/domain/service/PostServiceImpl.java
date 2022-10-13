package com.example.spartahomework2.domain.service;

import com.example.spartahomework2.Exception.NotExistExeption;
import com.example.spartahomework2.domain.entity.Member;
import com.example.spartahomework2.domain.entity.Post;
import com.example.spartahomework2.domain.repository.CommentRepository;
import com.example.spartahomework2.domain.repository.MemberRepository;
import com.example.spartahomework2.domain.repository.PostRepository;
import com.example.spartahomework2.security.util.SecurityUtil;
import com.example.spartahomework2.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostServiceImpl implements PostService{

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public void create(PostDto.Req reqDto) {

        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new NotExistExeption("해당 아이디를 가진 회원이 없습니다."));

        Post createdPost = reqDto.toEntity(member);

        postRepository.save(createdPost);
    }

    @Override
    public List<PostDto.Res> readAll() {

        return postRepository.findAll().stream()
                .map(PostDto.Res::new)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto.DetailRes readOneById(Long id) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new NotExistExeption("해당 아이디를 가진 회원이 존재하지 않습니다.")
        );

        return new PostDto.DetailRes(post);
    }

    @Transactional
    @Override
    public void update(Long id, PostDto.Req reqDto) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new NotExistExeption("해당 아이디를 가진 게시글이 없습니다.")
        );

        checkOwner(post, SecurityUtil.getCurrentMemberId());

        post.update(reqDto.getTitle(), reqDto.getContent());

    }

    @Transactional
    @Override
    public void delete(Long postId) {

        //댓글을 먼저 삭제시킵니다
        commentRepository.deleteAllByPostId(postId);

        postRepository.deleteById(postId);

    }

    private void checkOwner(Post post, Long memberId){

        if(!post.checkMemberByMemberId(memberId)){
            throw new AccessDeniedException("회원님이 작성한 글이 아닙니다.");
        }

    }

}
