package com.systemsinmotion.orgchart.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDepartment is a Querydsl query type for Department
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDepartment extends EntityPathBase<Department> {

    private static final long serialVersionUID = -1984368251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDepartment department = new QDepartment("department");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Integer> id = _super.id;

    //inherited
    public final BooleanPath isActive = _super.isActive;

    public final StringPath name = createString("name");

    public final QDepartment parentDepartment;

    public QDepartment(String variable) {
        this(Department.class, forVariable(variable), INITS);
    }

    public QDepartment(Path<? extends Department> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDepartment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDepartment(PathMetadata<?> metadata, PathInits inits) {
        this(Department.class, metadata, inits);
    }

    public QDepartment(Class<? extends Department> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentDepartment = inits.isInitialized("parentDepartment") ? new QDepartment(forProperty("parentDepartment"), inits.get("parentDepartment")) : null;
    }

}

