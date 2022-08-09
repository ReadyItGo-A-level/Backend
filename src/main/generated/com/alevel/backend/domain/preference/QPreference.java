package com.alevel.backend.domain.preference;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPreference is a Querydsl query type for Preference
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPreference extends EntityPathBase<Preference> {

    private static final long serialVersionUID = -1745315396L;

    public static final QPreference preference = new QPreference("preference");

    public final StringPath flavor = createString("flavor");

    public final StringPath price = createString("price");

    public final StringPath recommendation = createString("recommendation");

    public final NumberPath<Integer> sugar = createNumber("sugar", Integer.class);

    public final StringPath type = createString("type");

    public final NumberPath<Long> user_id = createNumber("user_id", Long.class);

    public final NumberPath<Integer> volume = createNumber("volume", Integer.class);

    public QPreference(String variable) {
        super(Preference.class, forVariable(variable));
    }

    public QPreference(Path<? extends Preference> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPreference(PathMetadata metadata) {
        super(Preference.class, metadata);
    }

}

