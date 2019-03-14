package com.yumong.cloud.security.models;

import com.yumong.cloud.support.models.AppRecord;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "resources")
@Data
public class Resource extends AppRecord {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String code;
    private String path;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @OrderBy("code ASC ")
    private Set<Resource> resources = new TreeSet<>();

    @ManyToOne
    private Resource parent;
}
