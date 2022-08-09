package com.alevel.backend.domain.scrappost;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScrapPost is a Querydsl query type for ScrapPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScrapPost extends EntityPathBase<ScrapPost> {

    private static final long serialVersionUID = -440053886L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScrapPost scrapPost = new QScrapPost("scrapPost");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.alevel.backend.domain.post.QPost postId;

    public final com.alevel.backend.domain.user.QUser userId;

    public QScrapPost(String variable) {
        this(ScrapPost.class, forVariable(variable), INITS);
    }

    public QScrapPost(Path<? extends ScrapPost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScrapPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScrapPost(PathMetadata metadata, PathInits inits) {
        this(ScrapPost.class, metadata, inits);
    }

    public QScrapPost(Class<? extends ScrapPost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.postId = inits.isInitialized("postId") ? new com.alevel.backend.domain.post.QPost(forProperty("postId"), inits.get("postId")) : null;
        this.userId = inits.isInitialized("userId") ? new com.alevel.backend.domain.user.QUser(forProperty("userId")) : null;
    }

}

