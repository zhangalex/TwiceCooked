package com.yumong.cloud.security.service;

import com.yumong.cloud.security.dao.UserDao;
import com.yumong.cloud.security.models.enums.OnlineStatus;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    public void offlineUser(Long id) {
        val u = userDao.findById(id).get();
        u.setOnlineStatus(OnlineStatus.OFFLINE);
        userDao.save(u);
    }

}
