package com.alevel.backend.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSaveDto {
    private Long userid;
    private Long postid;
    private String content;

    public CommentSaveDto(Long userid, Long postid, String content){
        this.userid=userid;
        this.postid=postid;
        this.content=content;
    }

}
