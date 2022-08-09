package com.alevel.backend.controller;

import com.alevel.backend.controller.dto.MypageWrittenPostResponseDto;
import com.alevel.backend.service.MypageWrittenPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MypageWrittenPostController {

    private final MypageWrittenPostService mypageWrittenPostService;

    @GetMapping("/users/{user-id}/posts")
    public List<MypageWrittenPostResponseDto> findByUserId (@PathVariable Long id){
        return mypageWrittenPostService.findByUserId(id);
    }
}
