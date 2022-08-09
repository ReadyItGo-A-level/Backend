package com.alevel.backend.controller.dto;

import com.alevel.backend.domain.post.Post;
import com.alevel.backend.domain.user.User;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class FeedResponseDtoTest {
    @Test
    public void testLombok() {
        Post entity = Post.builder()
                .user(User.builder()
                        .email("email@email.com")
                        .password("password")
                        .username("username")
                        .status(1)
                        .build())
                .title("title")
                .content("content")
                .image("image")
                .hit(Long.valueOf(0))
                .alcoholName("name")
                .alcoholType("type")
                .flavor("flavor")
                .volume(new BigDecimal(0.01))
                .price("price")
                .body(Long.valueOf(0))
                .sugar(Long.valueOf(0))
                .commentCount(0)
                .build();

        FeedResponseDto dto = new FeedResponseDto(entity);

        assertThat(dto.getId()).isEqualTo(entity.getId());
        assertThat(dto.getTitle()).isEqualTo(entity.getTitle());
    }
}
