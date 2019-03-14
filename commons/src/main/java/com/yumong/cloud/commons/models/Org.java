package com.yumong.cloud.commons.models;

import com.yumong.cloud.support.models.AppRecord;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "orgs", indexes = {
        @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_short_name", columnList = "shortName")
})
@Data
public class Org extends AppRecord {

    private String name;
    private String shortName;
    private Integer seqNum;
    private String remark;
    @ManyToOne
    private Org parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Org> children = new TreeSet<>();
}
