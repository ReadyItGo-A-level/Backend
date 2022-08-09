package com.alevel.backend.domain.likealcohol;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeAlcohol is a Querydsl query type for LikeAlcohol
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeAlcohol extends EntityPathBase<LikeAlcohol> {

    private static final long serialVersionUID = 1153922510L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeAlcohol likeAlcohol = new QLikeAlcohol("likeAlcohol");

    public final com.alevel.backend.domain.alcohol.QAlcohol alcoholId;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.alevel.backend.domain.user.QUser userId;

    public QLikeAlcohol(String variable) {
        this(LikeAlcohol.class, forVariable(variable), INITS);
    }

    public QLikeAlcohol(Path<? extends LikeAlcohol> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeAlcohol(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeAlcohol(PathMetadata metadata, PathInits inits) {
        this(LikeAlcohol.class, metadata, inits);
    }

    public QLikeAlcohol(Class<? extends LikeAlcohol> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.alcoholId = inits.isInitialized("alcoholId") ? new com.alevel.backend.domain.alcohol.QAlcohol(forProperty("alcoholId")) : null;
        this.userId = inits.isInitialized("userId") ? new com.alevel.backend.domain.user.QUser(forProperty("userId")) : null;
    }

}

