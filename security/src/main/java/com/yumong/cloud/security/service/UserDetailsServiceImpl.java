package com.yumong.cloud.security.service;

import com.yumong.cloud.security.models.Role;
import com.yumong.cloud.security.models.User;
import com.yumong.cloud.security.models.enums.Status;
import com.yumong.cloud.security.models.enums.UserStatus;
import com.yumong.cloud.security.dao.UserDao;
import com.yumong.cloud.support.utils.I18nUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.yumong.cloud.support.utils.ErrorUtil.raise;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userRepo;
    @Autowired
    private I18nUtil i18nUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(i18nUtil.getMessage("用户不存在"));
        }

        if (user.getUserStatus() == UserStatus.INACTIVE) {
            raise(400, i18nUtil.getMessage("该用户已被禁用，请联系管理员。"));
        }

        if (user.getRoles().size() == 0) {
            raise(400, i18nUtil.getMessage("当前用户没有权限，请联系管理员"));
        }

        if (user.getRoles().size() == 1) {
            for (Role role : user.getRoles()) {
                if (role.getRoleStatus() == Status.INACTIVE) {
                    raise(400, i18nUtil.getMessage("该用户权限已被冻结，请联系管理员"));
                }
            }
        }

        if (user.getDeletedAt() != null) {
            throw new UsernameNotFoundException(i18nUtil.getMessage("{users.account.notexists}"));
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(
                role -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()))
        );
        return new org.springframework.security.core.userdetails.User(username, user.getEncryptedPassword(), grantedAuthorities);
    }

}
