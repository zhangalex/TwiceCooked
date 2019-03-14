package com.yumong.cloud.security.dao;

import com.yumong.cloud.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
