package com.alevel.backend.service;

import com.alevel.backend.domain.likepost.LikePostRepository;
import com.alevel.backend.domain.post.Post;
import com.alevel.backend.domain.post.PostRepository;
import com.alevel.backend.domain.preference.Preference;
import com.alevel.backend.domain.scrappost.ScrapPostRepository;
import com.alevel.backend.domain.user.User;
import com.alevel.backend.dto.*;
import com.alevel.backend.exception.InvalidatePostException;
import com.alevel.backend.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentService commentService;
    private final UserService userService;
    private final LikePostRepository likePostRepository;
    private final ScrapPostRepository scrapPostRepository;

    private final FileService fileService;

    @Transactional
    public MyPagePostResponseDto findByUserId(Long id){
        Post entity= postRepository.findByUserId(id).orElseThrow(()
        -> new IllegalArgumentException("작성한 게시글이 없습니다. id="+id));
        return new MyPagePostResponseDto(entity);
    }

    public List<PostResponseDto> findByPreference(Preference preference) {
        List<Post> post = postRepository.findByVolumeOrSugar(preference.getVolume(), preference.getSugar());

        if (post.isEmpty()) return null;

        List<PostResponseDto> dto =new ArrayList<>();
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

    public List<PostResponseDto> findByAlcoholName(String name){
        List<Post> post = postRepository.findByAlcoholNameContaining(name);

        if (post.isEmpty()) {
            //throw new InvalidatePostException();
            return null;
        }

        List<PostResponseDto> dto = new ArrayList<>();

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

        List<PostResponseDto> dto = new ArrayList<>();

        for (int i=0; i<5; i++) {
            Long id = post.get(i).getId();
            String title = post.get(i).getTitle();
            String content = post.get(i).getContent();
            String image = post.get(i).getImage();
            Integer commentCount = post.get(i).getCommentCount();
            Integer scrapCount = post.get(i).getScrapCount();
            Integer likeCount = post.get(i).getLikeCount();

            dto.add(new PostResponseDto(id, title, content, image, commentCount, scrapCount, likeCount));
        }

        return dto;
    }

    public PostCommentsDetailResponseDto findPostAndCommentsById(Long id, Long userid) {
        Post post = postRepository.findByIdAndStatusTrue(id).orElseThrow(
                InvalidatePostException::new
        );
        List<CommentResponseDto> comments = commentService.findCommentseByPost(post);

        post.setHit(post.getHit() + 1);
        postRepository.save(post);

        Boolean like = CheckLike(userid, id);
        Boolean scrap = CheckScrap(userid, id);
        return new PostCommentsDetailResponseDto(post, like, scrap, comments);
    }

    public List<PostDetailResponseDto> findPosts() {
        return postRepository.findByStatusTrueOrderByIdDesc()
                .stream().map(PostDetailResponseDto::new).collect(Collectors.toList());
    }

    public Long savePost(PostRequestDto dto, MultipartFile file, User user) throws IOException {
        String filePath = fileService.uploadFile(file);
        return postRepository.save(dto.toEntity(user, filePath)).getId();
    }

    public Long updatePost(Long postId, PostRequestDto dto, MultipartFile file, Long userid) throws IOException {
        Post post = findById(postId);
        if (!post.getUser().getId().equals(userid)) {
            throw new UnauthorizedAccessException();
        }

        if (!file.isEmpty()) {
            String filePath = fileService.uploadFile(file);
            post.setImage(filePath);
        }

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAlcoholName(dto.getAlcoholName());
        post.setAlcoholType(dto.getAlcoholType());
        post.setFlavor(dto.getFlavor());
        post.setVolume(dto.getVolume());
        post.setPrice(dto.getPrice());
        post.setBody(dto.getBody());
        post.setSugar(dto.getSugar());
        return postRepository.save(post).getId();
    }

    public Long deletePostById(Long id) {
        Post post = postRepository.findByIdAndStatusTrue(id).orElseThrow(
                InvalidatePostException::new
        );
        post.setStatus(false);
        return postRepository.save(post).getId();
    }

    public boolean CheckLike(Long userId, Long postId) {
        User user = userService.findById(userId);
        Post post = findById(postId);
        return likePostRepository.findByUserAndPost(user, post).isPresent();
    }

    public boolean CheckScrap(Long userId, Long postId) {
        User user = userService.findById(userId);
        Post post = findById(postId);
        return scrapPostRepository.findByUserAndPost(user, post).isPresent();
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(InvalidatePostException::new);
    }
}
