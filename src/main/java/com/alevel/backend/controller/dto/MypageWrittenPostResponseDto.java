package com.alevel.backend.controller.dto;

import com.alevel.backend.domain.post.Post;
import lombok.Getter;

@Getter
public class MypageWrittenPostResponseDto {

    private String title;
    private String content;
    private Long id;
    private String image;
    //댓글개수
    private Integer commentCount;

    public MypageWrittenPostResponseDto(Post entity){
        this.id= entity.getId();
        this.content=entity.getContent();
        this.title=entity.getTitle();
        this.commentCount=entity.getCommentCount();
        this.image=entity.getImage();
    }
}
