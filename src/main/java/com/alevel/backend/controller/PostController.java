package com.alevel.backend.controller;

import com.alevel.backend.domain.response.ResponseMessage;
import com.alevel.backend.domain.response.ResultResponse;
import com.alevel.backend.domain.response.StatusCode;
import com.alevel.backend.dto.MyPagePostResponseDto;
import com.alevel.backend.dto.PostCommentsDetailResponseDto;
import com.alevel.backend.dto.PostRequestDto;
import com.alevel.backend.jwt.CustomUserDetails;
import com.alevel.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class PostController {

    private final PostService postService;

    @GetMapping("/users/{id}/post")
    public MyPagePostResponseDto findByUserId (@PathVariable Long id){
        return postService.findByUserId(id);
    }


    /**
     *
     * 게시글 조회 (피드)
     */
    @GetMapping("/posts")
    public ResultResponse findPosts (){
        try {
            return ResultResponse.success(postService.findPosts());
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.NOT_FOUND, ResponseMessage.INVALIDATED_POST);
        }
    }

    /**
     *
     * 게시글 상세 페이지 - 게시글 상세정보, 좋아요여부, 스크랩여부, 댓글
     */
    @GetMapping("/posts/{id}")
    public ResultResponse findPostById (@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user){
        try {
            PostCommentsDetailResponseDto post = postService.findPostAndCommentsById(id, user.getId());
            return ResultResponse.success(post);
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.NOT_FOUND, ResponseMessage.INVALIDATED_POST);
        }
    }

    /**
     *
     * 게시글 등록
     */
    @PostMapping("/posts")
    public ResultResponse savePost (
            @RequestPart PostRequestDto dto,
            @RequestPart MultipartFile file,
            @AuthenticationPrincipal CustomUserDetails user
    ){
        try {
            return ResultResponse.success(postService.savePost(dto, file, user.getUser()));
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * 게시글 수정
     */
    @PutMapping("/posts/{id}")
    public ResultResponse updatePost (
            @PathVariable Long id,
            @RequestPart PostRequestDto dto,
            @RequestPart MultipartFile file,
            @AuthenticationPrincipal CustomUserDetails user
    ){
        try {
            return ResultResponse.success(postService.updatePost(id, dto, file, user.getId()));
        } catch (IOException e) {
            return ResultResponse.fail(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * 게시글 삭제
     * status 0: 삭제
     */
    @DeleteMapping("/posts/{id}")
    public ResultResponse deletePostById (@PathVariable Long id){
        try {
            return ResultResponse.success(postService.deletePostById(id));
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.NOT_FOUND, ResponseMessage.INVALIDATED_POST);
        }
    }

    /**
     *
     * 게시글 좋아요 여부
     */
    @GetMapping(value = "/posts/{id}/like")
    public ResultResponse likePostCheck(@PathVariable("id") Long postid, @AuthenticationPrincipal CustomUserDetails user) {
        return ResultResponse.success(postService.CheckLike(user.getId(), postid));
    }

    /**
     *
     * 게시글 스크랩 여부
     */
    @GetMapping(value = "/posts/{id}/scrap")
    public ResultResponse scrapPostCheck(@PathVariable("id") Long postid, @AuthenticationPrincipal CustomUserDetails user) {
        return ResultResponse.success(postService.CheckScrap(user.getId(), postid));
    }

}
