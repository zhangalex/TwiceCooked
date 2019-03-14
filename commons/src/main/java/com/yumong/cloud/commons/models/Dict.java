package com.yumong.cloud.commons.models;

import com.yumong.cloud.commons.models.enums.DictType;
import com.yumong.cloud.support.models.AppRecord;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "dicts", indexes = {
        @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_code", columnList = "code", unique = true)
})
@Data
public class Dict extends AppRecord {
    String name;
    @Column(unique = true)
    String code;
    Integer levels;
    Integer seqNum = 0;
    String value;
    DictType dicType = DictType.ITEM;
    String remark;

    @ManyToOne
    Dict parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @OrderBy("seqNum asc ")
    Set<Dict> children = new TreeSet<>();

}
