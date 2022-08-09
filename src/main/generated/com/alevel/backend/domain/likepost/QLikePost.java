package com.alevel.backend.domain.likepost;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikePost is a Querydsl query type for LikePost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikePost extends EntityPathBase<LikePost> {

    private static final long serialVersionUID = 1618666780L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikePost likePost = new QLikePost("likePost");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.alevel.backend.domain.post.QPost postId;

    public final com.alevel.backend.domain.user.QUser userId;

    public QLikePost(String variable) {
        this(LikePost.class, forVariable(variable), INITS);
    }

    public QLikePost(Path<? extends LikePost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikePost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikePost(PathMetadata metadata, PathInits inits) {
        this(LikePost.class, metadata, inits);
    }

    public QLikePost(Class<? extends LikePost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.postId = inits.isInitialized("postId") ? new com.alevel.backend.domain.post.QPost(forProperty("postId"), inits.get("postId")) : null;
        this.userId = inits.isInitialized("userId") ? new com.alevel.backend.domain.user.QUser(forProperty("userId")) : null;
    }

}

