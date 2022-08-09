package com.alevel.backend.service;

import com.alevel.backend.controller.dto.FeedResponseDto;
import com.alevel.backend.domain.post.Post;
import com.alevel.backend.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final PostRepository postRepository;

    @Transactional
    public List<FeedResponseDto> findAllByOrderByCreatedDateDesc() {
        List<Post> postList = postRepository.findAllByOrderByCreatedDateDesc();
        return postList.stream().map(FeedResponseDto::new).collect(Collectors.toList());
    }
}
