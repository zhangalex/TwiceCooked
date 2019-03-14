package com.yumong.cloud.support.models;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
@DynamicInsert
@DynamicUpdate
public class AppRecord {
    protected static final String NOT_DELETED = "deleted_at IS NULL";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    protected ZonedDateTime createdAt = ZonedDateTime.now();
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    protected ZonedDateTime updatedAt = ZonedDateTime.now();

    protected ZonedDateTime deletedAt;

    private int version;

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

    @PreRemove
    public void beforeRemove() {
        deleted();
    }

    @Transient
    public void deleted() {
        setDeletedAt(ZonedDateTime.now());
    }
}
