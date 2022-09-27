package com.alevel.backend.service;

import com.alevel.backend.controller.dto.MyPagePostResponseDto;
import com.alevel.backend.controller.dto.PostResponseDto;
import com.alevel.backend.domain.post.Post;
import com.alevel.backend.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;


    @Transactional
    public MyPagePostResponseDto findByUserId(Long id){
        Post entity= postRepository.findByUserId(id).orElseThrow(()
        -> new IllegalArgumentException("작성한 게시글이 없습니다. id="+id));
        return new MyPagePostResponseDto(entity);
    }

    public List<PostResponseDto> findByAlcoholName(String name){
        List<Post> post = postRepository.findByAlcoholNameContaining(name);

        if (post.isEmpty()) {
            //throw new InvalidatePostException();
            return null;
        }

        List<PostResponseDto> dto = new ArrayList();

        for (Post value : post) {
            Long id = value.getId();
            String title = value.getTitle();
            String content = value.getContent();
            String image = value.getImage();
            Integer commentCount = value.getCommentCount();
            Integer scrapCount = value.getScrapCount();
            Integer likeCount = value.getLikeCount();

            dto.add(new PostResponseDto(id, title, content, image, commentCount, scrapCount, likeCount));
        }

        return dto;
    }

    public List<PostResponseDto> findRecommendationTopPost(){

        Sort sort = Sort.by(
                Sort.Order.desc("hit"),
                Sort.Order.desc("likeCount"),
                Sort.Order.desc("scrapCount")
        );

        List<Post> post = postRepository.findAll(sort);

        if (post.isEmpty()) {
            //throw new InvalidatePostException();
            return null;
        }

        List<PostResponseDto> dto = new ArrayList();

        for (Post value : post) {
            Long id = value.getId();
            String title = value.getTitle();
            String content = value.getContent();
            String image = value.getImage();
            Integer commentCount = value.getCommentCount();
            Integer scrapCount = value.getScrapCount();
            Integer likeCount = value.getLikeCount();

            dto.add(new PostResponseDto(id, title, content, image, commentCount, scrapCount, likeCount));
        }

        return dto;
    }
}
