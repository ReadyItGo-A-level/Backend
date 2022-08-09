package com.alevel.backend.domain.post;

import com.alevel.backend.domain.BaseTimeEntity;
import com.alevel.backend.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@DynamicInsert
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")

    private User user;

    @Column(length = 50, nullable = false)
    private String title;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 50)
    private String image;

    private Long hit;

    @Column(name = "alcohol_name", length = 20)
    private String alcoholName;

    @Column(name = "alcohol_type", length = 10)
    private String alcoholType;

    @Column(length = 20)
    private String flavor;

    @Column(precision = 5, scale = 2)
    private BigDecimal volume;

    @Column(length = 20)
    private String price;

    private Long body;

    private Long sugar;

    @Formula("(SELECT COUNT(*) FROM comment c WHERE c.post_id = id)")
    private int commentCount;

    @Builder
    public Post(User user, String title, String content, String image, Long hit, String alcoholName, String alcoholType,
                String flavor, BigDecimal volume, String price, Long body, Long sugar, int commentCount) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.image = image;
        this.hit = hit;
        this.alcoholName = alcoholName;
        this.alcoholType = alcoholType;
        this.flavor = flavor;
        this.volume = volume;
        this.price = price;
        this.body = body;
        this.sugar = sugar;
        this.commentCount = commentCount;
    }

}
