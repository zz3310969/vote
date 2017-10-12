package com.roof.vote.token;

import org.apache.log4j.Logger;
import org.roof.oauth2.service.TokenService;
import org.roof.roof.dataaccess.api.DaoException;
import org.roof.spring.Result;
import org.roof.web.menu.entity.Menu;
import org.roof.web.menu.service.api.IMenuFilter;
import org.roof.web.user.dao.api.IUserDao;
import org.roof.web.user.entity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetailsSource;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author liuxin
 * @see TokenEndpoint
 */
@RequestMapping("tokenAction")
public class TokenAction implements InitializingBean {

    private static final Logger logger = Logger.getLogger(TokenService.class);

    private Map<String, TokenGranter> tokenGranters;

    private ClientDetailsService clientDetailsService;

    private AuthorizationRequestManager authorizationRequestManager;

    private AuthorizationRequestManager defaultAuthorizationRequestManager;
    private ClientRegistrationService clientRegistrationService;

    private String grantType = "password";
    @Autowired
    @Qualifier("oAuth2AuthenticationManager")
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserDao userDao;

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new OAuth2AuthenticationDetailsSource();


    @Autowired
    private IMenuFilter menuFilter;

    /**
     * 获取token
     *
     * @param grant_type
     *            授权类型<br />
     *            password : 用户名密码授权
     * @param username
     *            用户名
     * @param password
     *            密码
     * @see TokenEndpoint#getAccessToken(Principal, String, Map);
     * @return
     */
    @RequestMapping(value = "/accessToken",method = RequestMethod.GET)
    public @ResponseBody
    Object accessToken(String username,String password, HttpServletRequest httpServletRequest) {
        OAuth2AccessToken token = null;
        String clientId = username;
        try {
            Map<String, String> parameters = pasMap(httpServletRequest);
                parameters.put("client_id", username);
            if (!StringUtils.hasText(grantType)) {
                throw new InvalidRequestException("Missing grant type");
            }

            try {
                getAuthorizationRequestManager().validateParameters(parameters,
                        getClientDetailsService().loadClientByClientId(clientId));
            } catch (NoSuchClientException e) {// 提取单独注册
                ClientDetails clientDetails = new BaseClientDetails(clientId, null, null, grantType, null);
                clientRegistrationService.addClientDetails(clientDetails);
                getAuthorizationRequestManager().validateParameters(parameters,
                        getClientDetailsService().loadClientByClientId(clientId));
            }

            DefaultAuthorizationRequest authorizationRequest = new DefaultAuthorizationRequest(
                    getAuthorizationRequestManager().createAuthorizationRequest(parameters));
            if (isAuthCodeRequest(parameters) || isRefreshTokenRequest(parameters)) {
                // The scope was requested or determined during the
                // authorization
                // step
                if (!authorizationRequest.getScope().isEmpty()) {
                    logger.debug("Clearing scope of incoming auth code request");
                    authorizationRequest.setScope(Collections.<String> emptySet());
                }
            }
            if (isRefreshTokenRequest(parameters)) {
                // A refresh token has its own default scopes, so we should
                // ignore
                // any added by the factory here.
                authorizationRequest.setScope(OAuth2Utils.parseParameterList(parameters.get("scope")));
            }
            token = getTokenGranter(grantType).grant(grantType, authorizationRequest);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 系统编码或者授权密码错误或者授权类型不合法
            Result r = new Result(Result.ERROR, e.getMessage(), null);
            r.setExceptionCode("21008");
            return r;
        }



        String tokenValue  =token.getValue();
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
                tokenValue, "");
        httpServletRequest.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE, tokenValue);
        authentication.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));

        Authentication authResult = authenticationManager.authenticate(authentication);


        Menu menu = menuFilter.doFilter(1L, new UsernamePasswordAuthenticationToken(authResult.getPrincipal(),
                authResult.getCredentials(), authResult.getAuthorities()));
        User user = null;
        try {
            user = userDao.loadUserByUsername(username);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        LoginInfo info = new LoginInfo();
        info.setMenus(menu.getChildren());
        info.setToken(token);
        info.setUser(user);

        return new Result(Result.SUCCESS, info);
    }

    private Map<String, String> pasMap(HttpServletRequest httpRequest) {
        Map<String, String> result = new HashMap<>();
        Enumeration<String> parameterNames = httpRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            result.put(name, httpRequest.getParameter(name));
        }
        return result;
    }

    private TokenGranter getTokenGranter(String grantType) {
        return tokenGranters.get(grantType);
    }

    /**
     * @param principal
     *            the currently authentication principal
     * @return a client id if there is one in the principal
     */
    protected String getClientId(Principal principal) {
        Authentication client = (Authentication) principal;
        if (!client.isAuthenticated()) {
            throw new InsufficientAuthenticationException("The client is not authenticated.");
        }
        String clientId = client.getName();
        if (client instanceof OAuth2Authentication) {
            // Might be a client and user combined authentication
            clientId = ((OAuth2Authentication) client).getAuthorizationRequest().getClientId();
        }
        return clientId;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(tokenGranters != null, "TokenGranter must be provided");
        Assert.state(clientDetailsService != null, "ClientDetailsService must be provided");
        defaultAuthorizationRequestManager = new DefaultAuthorizationRequestManager(getClientDetailsService());
        if (authorizationRequestManager == null) {
            authorizationRequestManager = defaultAuthorizationRequestManager;
        }
    }

    private boolean isRefreshTokenRequest(Map<String, String> parameters) {
        return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
    }

    private boolean isAuthCodeRequest(Map<String, String> parameters) {
        return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
    }

    public ClientDetailsService getClientDetailsService() {
        return clientDetailsService;
    }

    public AuthorizationRequestManager getDefaultAuthorizationRequestManager() {
        return defaultAuthorizationRequestManager;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    public void setDefaultAuthorizationRequestManager(AuthorizationRequestManager defaultAuthorizationRequestManager) {
        this.defaultAuthorizationRequestManager = defaultAuthorizationRequestManager;
    }

    public AuthorizationRequestManager getAuthorizationRequestManager() {
        return authorizationRequestManager;
    }

    public void setAuthorizationRequestManager(AuthorizationRequestManager authorizationRequestManager) {
        this.authorizationRequestManager = authorizationRequestManager;
    }

    public Map<String, TokenGranter> getTokenGranters() {
        return tokenGranters;
    }

    public void setTokenGranters(Map<String, TokenGranter> tokenGranters) {
        this.tokenGranters = tokenGranters;
    }

    public ClientRegistrationService getClientRegistrationService() {
        return clientRegistrationService;
    }

    public void setClientRegistrationService(ClientRegistrationService clientRegistrationService) {
        this.clientRegistrationService = clientRegistrationService;
    }

}
