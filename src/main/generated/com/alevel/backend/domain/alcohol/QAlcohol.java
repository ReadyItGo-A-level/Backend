package com.alevel.backend.domain.alcohol;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAlcohol is a Querydsl query type for Alcohol
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlcohol extends EntityPathBase<Alcohol> {

    private static final long serialVersionUID = -1242349476L;

    public static final QAlcohol alcohol = new QAlcohol("alcohol");

    public final NumberPath<Integer> acidity = createNumber("acidity", Integer.class);

    public final NumberPath<Integer> body = createNumber("body", Integer.class);

    public final StringPath category = createString("category");

    public final StringPath flavor = createString("flavor");

    public final StringPath food = createString("food");

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final StringPath nation = createString("nation");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath size = createString("size");

    public final NumberPath<Integer> sugar = createNumber("sugar", Integer.class);

    public final NumberPath<Integer> tannins = createNumber("tannins", Integer.class);

    public final StringPath type = createString("type");

    public final NumberPath<java.math.BigDecimal> volume = createNumber("volume", java.math.BigDecimal.class);

    public QAlcohol(String variable) {
        super(Alcohol.class, forVariable(variable));
    }

    public QAlcohol(Path<? extends Alcohol> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlcohol(PathMetadata metadata) {
        super(Alcohol.class, metadata);
    }

}

