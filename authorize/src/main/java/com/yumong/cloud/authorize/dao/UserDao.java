package com.yumong.cloud.authorize.dao;

import com.yumong.cloud.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
