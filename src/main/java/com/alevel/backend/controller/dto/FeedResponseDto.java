package com.alevel.backend.controller.dto;

import com.alevel.backend.domain.post.Post;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class FeedResponseDto {
    private Long id;
    private String title;
    private String content;
    private String image;
    private String alcoholType;
    private String alcoholName;
    private String flavor;
    private BigDecimal volume;
    private String price;
    private Long body;
    private Long sugar;
    private LocalDateTime createdDate;

    public FeedResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.image = entity.getImage();
        this.alcoholType = entity.getAlcoholType();
        this.alcoholName = entity.getAlcoholName();
        this.flavor = entity.getFlavor();
        this.volume = entity.getVolume();
        this.price = entity.getPrice();
        this.body = entity.getBody();
        this.sugar = entity.getSugar();
        this.createdDate = entity.getCreatedDate();
    }

}
