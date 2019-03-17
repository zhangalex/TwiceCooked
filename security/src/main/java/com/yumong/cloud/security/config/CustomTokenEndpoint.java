package com.yumong.cloud.security.config;

import com.yumong.cloud.security.models.User;
import com.yumong.cloud.security.service.SecurityService;
import com.yumong.cloud.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@FrameworkEndpoint
public class CustomTokenEndpoint {

    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;
    @Autowired
    private SecurityService securitySrv;
    @Autowired
    private UserService userSrv;

    @RequestMapping(method = {RequestMethod.DELETE}, value = "/oauth/token/revoke")
    @ResponseBody
    public void revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            User user = securitySrv.currentUser(tokenId);
            tokenServices.revokeToken(tokenId);

            if (user != null) {
                userSrv.offlineUser(user.getId());
            }

        }
    }

}