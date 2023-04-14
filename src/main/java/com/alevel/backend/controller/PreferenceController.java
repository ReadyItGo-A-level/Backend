package com.alevel.backend.controller;

import com.alevel.backend.dto.PostResponseDto;
import com.alevel.backend.domain.preference.Preference;
import com.alevel.backend.domain.response.ResponseMessage;
import com.alevel.backend.domain.response.ResultResponse;
import com.alevel.backend.domain.response.StatusCode;
import com.alevel.backend.dto.PreferenceRequestDto;
import com.alevel.backend.dto.RecommendAlcoholDto;
import com.alevel.backend.jwt.CustomUserDetails;
import com.alevel.backend.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PreferenceController {

    private final PreferenceService preferenceService;

    @Autowired
    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    /**
     * 술 취향 등록
     */
    @PostMapping(value = "/recommendations/preference")
    public ResultResponse savePreference(
            @RequestBody PreferenceRequestDto preference,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        if (preference.getVolume() < 1 || preference.getVolume() > 5 || preference.getSugar() < 1 | preference.getSugar() > 5)
            return ResultResponse.fail(StatusCode.BAD_REQUEST, ResponseMessage.BAD_REQUEST_INPUT_RANGE);

        try {
            String recommendation = preferenceService.getRecommendationId(preference);
            preferenceService.insert(
                    new Preference(
                            user.getId(),
                            preference.getType(),
                            preference.getVolume(),
                            preference.getSugar(),
                            preference.getFlavor(),
                            preference.getPrice(),
                            recommendation
                    )
            );
            return ResultResponse.success();
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.BAD_REQUEST, ResponseMessage.FAIL);
        }
    }

    /**
     *
     * 술 추천 페이지 - 술 추천, 추천게시글, 인기게시글
     */
    @GetMapping(value = "/recommendations/preference")
    public ResultResponse getRecommendationPage(String type, @AuthenticationPrincipal CustomUserDetails user) {
        try {
            Map<String, List<RecommendAlcoholDto>> alcohol = preferenceService.findRecommendationAlcohol(user.getId());
            List<PostResponseDto> post = preferenceService.findRecommendationPost(user.getId());
            List<PostResponseDto> topPost = preferenceService.findRecommendationTopPost();

            Map<String, Object> data = new HashMap<>();
            data.put("alcohol", alcohol);
            data.put("post", post);
            data.put("topPost", topPost);

            return ResultResponse.success(data);
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.NOT_FOUND, ResponseMessage.INVALIDATED_ALCOHOL);
        }
    }

    /**
     * 술 추천
     * 사용: PreferenceController.getRecommendationPage
     */
    @GetMapping(value = "/recommendations/alcohol")
    public ResultResponse getRecommendationAlcohol(@AuthenticationPrincipal CustomUserDetails user) {
        try {
            return ResultResponse.success(preferenceService.findRecommendationAlcohol(user.getId()));
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.NOT_FOUND, ResponseMessage.INVALIDATED_ALCOHOL);
        }
    }

    /**
     *
     * 유저 취향과 비슷한 게시글 추천
     * 사용: AlcoholController.getAlcoholDetailPage
     *      PreferenceController.getRecommendationPage
     */
    @GetMapping(value = "/recommendations/post")
    public ResultResponse getRecommendationPost(@AuthenticationPrincipal CustomUserDetails user) {
        try {
            List<PostResponseDto> recommendationPost = preferenceService.findRecommendationPost(user.getId());
            return ResultResponse.success(recommendationPost);
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.NOT_FOUND, ResponseMessage.INVALIDATED_POST);
        }
    }

    /**
     *
     * 지금 인기있는 게시글
     * 사용: PreferenceController.getRecommendationPage
     */
    @GetMapping(value = "/recommendations/top-post")
    public ResultResponse getRecommendationTopPost() {
        try {
            List<PostResponseDto> recommendationTopPost = preferenceService.findRecommendationTopPost();
            return ResultResponse.success(recommendationTopPost);
        } catch (Exception e) {
            return ResultResponse.fail(StatusCode.NOT_FOUND, ResponseMessage.INVALIDATED_POST);
        }
    }
}
