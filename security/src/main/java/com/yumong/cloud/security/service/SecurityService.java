package com.yumong.cloud.security.service;

import com.yumong.cloud.security.dao.UserDao;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class SecurityService {
    @Value("${security.jwt.client-id}")
    private String clientId;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    TokenStore ts;
    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }

    public com.yumong.cloud.security.models.User currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String){
            return userDao.findByUsername((String) principal);
        }
        return userDao.findByUsername(((User) principal).getUsername());
    }

    public com.yumong.cloud.security.models.User currentUser(String token) {
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(token);
        if (oAuth2Authentication == null) {
            return null;
        }

        Object principal = oAuth2Authentication.getPrincipal();
        if (principal instanceof String){
            return userDao.findByUsername((String) principal);
        }
        return userDao.findByUsername(((User) principal).getUsername());
    }

    public void revoke(String username) {
        Collection<OAuth2AccessToken> tokens = ts.findTokensByClientIdAndUserName(clientId, username);
        tokens.forEach(e -> tokenServices.revokeToken(e.getValue()));
    }

    public String encodePassword(@NonNull String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public Boolean isValidPassword(@NonNull String rawPwd, @NonNull String encryptedPwd) {
        return passwordEncoder.matches(rawPwd, encryptedPwd);
    }

}