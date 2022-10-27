package com.alevel.backend.domain.comment;

import com.alevel.backend.domain.BaseTimeEntity;
import com.alevel.backend.domain.post.Post;
import com.alevel.backend.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@DynamicInsert
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Comment(User user, Post post,String content){
        this.user=user;
        this.post=post;
        this.content=content;
    }
}
