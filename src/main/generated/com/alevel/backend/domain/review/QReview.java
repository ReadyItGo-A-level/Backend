package com.alevel.backend.domain.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -540249444L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review1 = new QReview("review1");

    public final com.alevel.backend.domain.alcohol.QAlcohol alcoholId;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath review = createString("review");

    public final com.alevel.backend.domain.user.QUser userId;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.alcoholId = inits.isInitialized("alcoholId") ? new com.alevel.backend.domain.alcohol.QAlcohol(forProperty("alcoholId")) : null;
        this.userId = inits.isInitialized("userId") ? new com.alevel.backend.domain.user.QUser(forProperty("userId")) : null;
    }

}

