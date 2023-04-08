package com.alevel.backend.controller;

import com.alevel.backend.dto.AlcoholDetailResponseDto;
import com.alevel.backend.dto.AlcoholResponseDto;
import com.alevel.backend.dto.AlcoholReviewResponseDto;
import com.alevel.backend.dto.PostResponseDto;
import com.alevel.backend.domain.response.ResponseMessage;
import com.alevel.backend.domain.response.ResultResponse;
import com.alevel.backend.domain.response.StatusCode;
import com.alevel.backend.jwt.CustomUserDetails;
import com.alevel.backend.service.AlcoholService;
import com.alevel.backend.service.PostService;
import com.alevel.backend.service.PreferenceService;
import com.alevel.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AlcoholController {

    @Autowired
    private final AlcoholService alcoholService;
    @Autowired
    private final ReviewService reviewService;
    private final PostService postService;
    private final PreferenceService preferenceService;

    @Autowired
    public AlcoholController(AlcoholService alcoholService, ReviewService reviewService, PostService postService, PreferenceService preferenceService) {
        this.alcoholService = alcoholService;
        this.reviewService = reviewService;
        this.postService = postService;
        this.preferenceService = preferenceService;
    }

    /**
     * 술 전체 조회
     */
    @GetMapping(value = "/alcohols")
    public ResultResponse getAlcohol(
            String type, String category,
            @PageableDefault(size = 6, sort = "hit", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal CustomUserDetails user) {
        try {
            Page<AlcoholResponseDto> alcohol = alcoholService.findAllAlcohol(type, category, pageable, user.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("alcohol", alcohol.getContent());
            data.put("total", alcohol.getTotalElements());
            return ResultResponse.success(data);
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.BAD_REQUEST, ResponseMessage.FAIL);
        }
    }


    /**
     *
     * 술 상세 페이지 - 술 상세정보, 술 스크랩여부, 한줄리뷰, 게시글, 추천게시글
     */
    @GetMapping(value = "/alcohols/{id}")
    public ResultResponse getAlcoholDetailPage(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails user) {
        try {
            AlcoholDetailResponseDto alcohol = alcoholService.findAlcoholDetail(id);
            Boolean scrap = alcoholService.CheckScrap(user.getId(), id);
            List<AlcoholReviewResponseDto> review = reviewService.getReview(id);
            List<PostResponseDto> post = postService.findByAlcoholName(alcohol.getName());
            List<PostResponseDto> recommendationPost = preferenceService.findRecommendationPost(user.getId());

            Map<String, Object> data = new HashMap<>();
            data.put("alcohol", alcohol);
            data.put("scrap", scrap);
            data.put("review", review);
            data.put("post", post);
            data.put("recommendationPost", recommendationPost);

            return ResultResponse.success(data);
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.BAD_REQUEST, ResponseMessage.FAIL);
        }
    }

    /**
     *
     * 술 상세 조회
     * 사용: AlcoholController.getAlcoholDetailPage
     */
    @GetMapping(value = "/alcohols/{id}/detail")
    public ResultResponse getAlcoholDetail(@PathVariable("id") Long id) {
        try {
            AlcoholDetailResponseDto alcohol = alcoholService.findAlcoholDetail(id);
            return ResultResponse.success(alcohol);
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.BAD_REQUEST, ResponseMessage.FAIL);
        }
    }

    /**
     *
     * 술 스크랩
     */
    @PostMapping(value = "/alcohols/{id}/scrap")
    public ResultResponse scrapAlcohol(@PathVariable("id") Long alcoholid, @AuthenticationPrincipal CustomUserDetails user) {
        alcoholService.scrapAlcohol(user.getId(), alcoholid);
        return ResultResponse.success();
    }

    /**
     *
     * 술 스크랩 여부
     */
    @GetMapping(value = "/alcohols/{id}/scrap")
    public ResultResponse scrapAlcoholCheck(@PathVariable("id") Long alcoholid, @AuthenticationPrincipal CustomUserDetails user) {
        return ResultResponse.success(alcoholService.CheckScrap(user.getId(), alcoholid));
    }
}
