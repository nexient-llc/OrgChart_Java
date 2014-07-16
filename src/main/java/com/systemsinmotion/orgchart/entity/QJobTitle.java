package com.systemsinmotion.orgchart.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QJobTitle is a Querydsl query type for JobTitle
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QJobTitle extends EntityPathBase<JobTitle> {

    private static final long serialVersionUID = 829621134L;

    public static final QJobTitle jobTitle = new QJobTitle("jobTitle");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Integer> id = _super.id;

    //inherited
    public final BooleanPath isActive = _super.isActive;

    public final StringPath name = createString("name");

    public QJobTitle(String variable) {
        super(JobTitle.class, forVariable(variable));
    }

    public QJobTitle(Path<? extends JobTitle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJobTitle(PathMetadata<?> metadata) {
        super(JobTitle.class, metadata);
    }

}

