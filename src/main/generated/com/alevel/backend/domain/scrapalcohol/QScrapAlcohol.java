package com.alevel.backend.domain.scrapalcohol;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScrapAlcohol is a Querydsl query type for ScrapAlcohol
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScrapAlcohol extends EntityPathBase<ScrapAlcohol> {

    private static final long serialVersionUID = -1418183396L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScrapAlcohol scrapAlcohol = new QScrapAlcohol("scrapAlcohol");

    public final com.alevel.backend.domain.alcohol.QAlcohol alcohol;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> myScrapAlcoholCount = createNumber("myScrapAlcoholCount", Integer.class);

    public final com.alevel.backend.domain.user.QUser user;

    public QScrapAlcohol(String variable) {
        this(ScrapAlcohol.class, forVariable(variable), INITS);
    }

    public QScrapAlcohol(Path<? extends ScrapAlcohol> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScrapAlcohol(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScrapAlcohol(PathMetadata metadata, PathInits inits) {
        this(ScrapAlcohol.class, metadata, inits);
    }

    public QScrapAlcohol(Class<? extends ScrapAlcohol> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.alcohol = inits.isInitialized("alcohol") ? new com.alevel.backend.domain.alcohol.QAlcohol(forProperty("alcohol")) : null;
        this.user = inits.isInitialized("user") ? new com.alevel.backend.domain.user.QUser(forProperty("user")) : null;
    }

}

