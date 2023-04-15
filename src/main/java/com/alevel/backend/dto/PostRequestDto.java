package com.alevel.backend.dto;

import com.alevel.backend.domain.post.Post;
import com.alevel.backend.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
    private String alcoholName;
    private String alcoholType;

    private String flavor;
    private String volume;
    private String price;
    private Long body;
    private Long sugar;

    private Long hit;

    @Builder
    public PostRequestDto(
            String title,
            String content,
            String alcoholName,
            String alcoholType,
            String flavor,
            String volume,
            String price,
            Long body,
            Long sugar
    ) {
        this.title = title;
        this.content = content;
        this.alcoholName = alcoholName;
        this.alcoholType = alcoholType;
        this.flavor = flavor;
        this.volume = volume;
        this.price = price;
        this.body = body;
        this.sugar = sugar;
    }

    public Post toEntity(User user, String filePath) {
        return Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .image(filePath)
                .alcoholName(alcoholName)
                .alcoholType(alcoholType)
                .flavor(flavor)
                .volume(volume)
                .price(price)
                .body(body)
                .sugar(sugar)
                .hit(0L)
                .build();
    }
}
