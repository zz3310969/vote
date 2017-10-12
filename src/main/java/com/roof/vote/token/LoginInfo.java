package com.roof.vote.token;

import org.roof.web.menu.entity.Menu;
import org.roof.web.user.entity.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;

/**
 * Created by lenovo on 2017/8/30.
 */
public class LoginInfo {

    private OAuth2AccessToken token;
    private User user;
    private List<Menu> menus;

    public OAuth2AccessToken getToken() {
        return token;
    }

    public void setToken(OAuth2AccessToken token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
