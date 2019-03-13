package com.yumong.cloud.authorize.service;

import com.yumong.cloud.authorize.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    @Autowired
    UserDao ud;

}
