package com.alevel.backend.service;

import com.alevel.backend.controller.dto.CommentSaveDto;
import com.alevel.backend.domain.alcohol.AlcoholRepository;
import com.alevel.backend.domain.comment.Comment;
import com.alevel.backend.domain.comment.CommentRepository;
import com.alevel.backend.domain.post.Post;
import com.alevel.backend.domain.post.PostRepository;
import com.alevel.backend.domain.review.ReviewRepository;
import com.alevel.backend.domain.user.User;
import com.alevel.backend.domain.user.UserRepository;
import com.alevel.backend.exception.ExceededNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    private final PostRepository postRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository,PostRepository postRepository) {
        this.postRepository=postRepository;
        this.userRepository = userRepository;
        this.commentRepository=commentRepository;
    }
    public void saveComment(CommentSaveDto dto) {
        User user = userRepository.getReferenceById(dto.getUserid());
        Post post= postRepository.getReferenceById(dto.getPostid());

        if (dto.getContent().length()>100) {
            throw new ExceededNumberException();
        }
        else{
            commentRepository.save(new Comment(user, post, dto.getContent()));
        }
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 댓글입니다. id=" + id));
        commentRepository.delete(comment);
    }
}
