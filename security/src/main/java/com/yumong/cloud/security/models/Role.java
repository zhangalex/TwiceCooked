package com.yumong.cloud.security.models;

import com.yumong.cloud.security.models.enums.Status;
import com.yumong.cloud.support.models.AppRecord;
import lombok.Data;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role extends AppRecord {
    @Column(nullable = false)
    private String code;
    private String name;
    private Status roleStatus = Status.ACTIVE;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "roles_resources", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
    Set<Resource> resources = new HashSet<>();

    @Transient
    public String getHumanRoleStatus() {
        return roleStatus == Status.ACTIVE ? "可用" : "冻结";
    }
}