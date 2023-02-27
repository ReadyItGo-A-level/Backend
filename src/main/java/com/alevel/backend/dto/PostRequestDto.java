package com.alevel.backend.dto;

import com.alevel.backend.domain.post.Post;
import com.alevel.backend.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
    private String image;
    private String alcoholName;
    private String alcoholType;

    private String flavor;
    private BigDecimal volume;
    private String price;
    private Long body;
    private Long sugar;

    @Builder
    public PostRequestDto(
            String title,
            String content,
            String image,
            String alcoholName,
            String alcoholType,
            String flavor,
            BigDecimal volume,
            String price,
            Long body,
            Long sugar
    ) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.alcoholName = alcoholName;
        this.alcoholType = alcoholType;
        this.flavor = flavor;
        this.volume = volume;
        this.price = price;
        this.body = body;
        this.sugar = sugar;
    }

    public Post toEntity(User user) {
        return Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .image(image)
                .alcoholName(alcoholName)
                .alcoholType(alcoholType)
                .flavor(flavor)
                .volume(volume)
                .price(price)
                .body(body)
                .sugar(sugar)
                .build();
    }
}
