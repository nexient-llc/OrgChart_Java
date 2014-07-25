package com.systemsinmotion.orgchart.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = -646347359L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployee employee = new QEmployee("employee");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QDepartment department;

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final BooleanPath is_manager = createBoolean("is_manager");

    //inherited
    public final BooleanPath isActive = _super.isActive;

    public final QJobTitle jobTitle;

    public final StringPath lastName = createString("lastName");

    public final QEmployee manager;

    public final ComparablePath<Character> middleInitial = createComparable("middleInitial", Character.class);

    public final StringPath skypeName = createString("skypeName");

    public QEmployee(String variable) {
        this(Employee.class, forVariable(variable), INITS);
    }

    public QEmployee(Path<? extends Employee> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmployee(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmployee(PathMetadata<?> metadata, PathInits inits) {
        this(Employee.class, metadata, inits);
    }

    public QEmployee(Class<? extends Employee> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new QDepartment(forProperty("department"), inits.get("department")) : null;
        this.jobTitle = inits.isInitialized("jobTitle") ? new QJobTitle(forProperty("jobTitle")) : null;
        this.manager = inits.isInitialized("manager") ? new QEmployee(forProperty("manager"), inits.get("manager")) : null;
    }

}

