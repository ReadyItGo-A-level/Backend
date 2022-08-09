package com.alevel.backend.service;

import com.alevel.backend.controller.dto.PostResponseDto;
import com.alevel.backend.domain.post.Post;
import com.alevel.backend.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MypageWrittenPostService {
    private final PostRepository postRepository;


    @Transactional
    public List<PostResponseDto> findByUserId(Long id){
        List<Post> entity= postRepository.findByUserId(id);
        return entity.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }
}
