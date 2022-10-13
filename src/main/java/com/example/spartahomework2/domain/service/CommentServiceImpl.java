package com.example.spartahomework2.domain.service;

import com.example.spartahomework2.Exception.NotExistExeption;
import com.example.spartahomework2.domain.entity.Comment;
import com.example.spartahomework2.domain.entity.Member;
import com.example.spartahomework2.domain.entity.Post;
import com.example.spartahomework2.domain.repository.CommentRepository;
import com.example.spartahomework2.domain.repository.MemberRepository;
import com.example.spartahomework2.domain.repository.PostRepository;
import com.example.spartahomework2.security.util.SecurityUtil;
import com.example.spartahomework2.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentServiceImpl implements CommentService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void create(Long postId, CommentDto.Req reqDto) {

        Long memberId = SecurityUtil.getCurrentMemberId();

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotExistExeption("해당 아이디를 가진 회원이 존재하지 않습니다.")
        );

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NotExistExeption("해당 아이디를 가진 게시글이 존재하지 않습니다.")
        );

        Comment comment = reqDto.toEntity(member, post);

        commentRepository.save(comment);

    }

    @Override
    public List<CommentDto.Res> findAllByPostId(Long postId) {

        String author = postRepository.findAuthorById(postId).orElseThrow(
                () -> new NotExistExeption("해당 아이디를 가진 게시글이 존재하지 않습니다.")
        );

        return commentRepository.findAllByPostId(postId).stream()
                .map(comment -> new CommentDto.Res(comment, author))
                .toList();
    }

    @Override
    @Transactional
    public void update(Long postId, Long commentId, CommentDto.Req reqDto) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NotExistExeption("해당 아이디를 가진 댓글이 존재하지 않습니다.")
        );

        checkMemberByMemberId(comment, SecurityUtil.getCurrentMemberId());

        checkPostByPostId(comment, postId);

        comment.update(reqDto.getContent());

    }

    @Override
    @Transactional
    public void delete(Long postId, Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NotExistExeption("해당 아이디를 가진 댓글이 존재하지 않습니다.")
        );

        checkMemberByMemberId(comment, SecurityUtil.getCurrentMemberId());

        checkPostByPostId(comment, postId);

        commentRepository.deleteById(commentId);

    }

    private void checkPostByPostId(Comment comment, Long postId){

        if(!comment.checkPostByPostId(postId)){
            throw new IllegalArgumentException("해당 게시글의 댓글이 아닙니다.");
        }
    }

    private void checkMemberByMemberId(Comment comment, Long memberId){

        if (!comment.checkMemberByMemberId(memberId)){
            throw new AccessDeniedException("회원님이 작성한 댓글이 아닙니다.");
        }
    }

}
