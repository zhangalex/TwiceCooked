package com.yumong.cloud.models;

import com.yumong.cloud.models.enums.Gender;
import com.yumong.cloud.models.enums.OnlineStatus;
import com.yumong.cloud.models.enums.UserStatus;
import com.yumong.cloud.models.enums.UserType;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_username", columnList = "username", unique = true),
        @Index(name = "idx_email", columnList = "email", unique = true),
        @Index(name = "idx_mobile", columnList = "mobile", unique = true)
})
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class User extends AppRecord {

    private String displayName;
    private String avatar;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String mobile;
    @Column(unique = true)
    private String email;
    @Transient
    private String password;
    private String encryptedPassword;
    private String resetPasswordToken;
    private String resetPasswordSentAt;
    private String rememberCreatedAt;
    private String clientIp;
    private String currentSignInIp;
    private ZonedDateTime lastSignInAt;
    private ZonedDateTime currentSignInAt;
    private Integer signInCount;

    private String englishDisplayName;
    private String nationality;
    private String province;
    private String city;
    private String district;
    private ZonedDateTime birthday;
    private Gender gender = Gender.MALE;
    private String addr;
    private String postCode;
    @Enumerated(EnumType.ORDINAL)
    private UserStatus userStatus = UserStatus.ACTIVE;
    private String timezone;
    @Enumerated(EnumType.ORDINAL)
    private OnlineStatus onlineStatus = OnlineStatus.OFFLINE;

    private UserType userType = UserType.USER;

    @Lob
    private String remark;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Fetch(FetchMode.JOIN)
    private Set<Role> roles = new HashSet<>();

//    @Transient
//    public String getAvatarUrl() {
//        return getAvatarUrl(getAvatar());
//    }

//    @Transient
//    public boolean checkPassword(String currentPassword) {
//        BCryptPasswordEncoder pwdEncoder = bean(BCryptPasswordEncoder.class);
//        return pwdEncoder.matches(currentPassword, this.getEncryptedPassword());
//    }
//
//    public void setPassword(String password) {
//        SecurityService ss = bean(SecurityService.class);
//        this.password = password;
//        this.encryptedPassword = ss.encodePassword(password);
//    }

}
