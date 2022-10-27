package com.alevel.backend.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private Long userid;
    private String content;

    public CommentRequestDto(Long userid, String content){
        this.userid=userid;
        this.content=content;
    }
}
