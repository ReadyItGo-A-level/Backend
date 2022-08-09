package com.alevel.backend.controller;

import com.alevel.backend.controller.dto.FeedResponseDto;
import com.alevel.backend.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FeedController {
    private final FeedService feedService;

    @GetMapping("/posts")
    public List<FeedResponseDto> findAllByOrderByCreatedDateDesc() {
        return feedService.findAllByOrderByCreatedDateDesc();
    }
}
