package com.roof.core.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.roof.core.http.HttpClientUtil;
import com.roof.core.wechat.bean.WeChatToken;
import com.roof.core.wechat.bean.WxUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.roof.commons.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2017/8/17.
 */
@Service
public class WeChatHander {
    private Logger LOGGER = LoggerFactory.getLogger(WeChatHander.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private String appid =PropertiesUtil.getPorpertyString("selin.wx.appid");
    private String secret =PropertiesUtil.getPorpertyString("selin.wx.secret");
    private String token_url =PropertiesUtil.getPorpertyString("selin.wx.access_token_url");
    private String html_token_url =PropertiesUtil.getPorpertyString("selin.wx.html_access_token_url");
    private String grant_type = PropertiesUtil.getPorpertyString("selin.wx.grant_type");
    private String user_info_url = PropertiesUtil.getPorpertyString("wx.user.info.url");

    private String redis_key = "wechat:token";

    public String getAccess_tokenByHttp(){
       String s = HttpClientUtil.get(token_url);
        WeChatToken token = JSON.parseObject(s,WeChatToken.class);
        if(token == null){
            LOGGER.error("获取微信token失败");
            //throw new SelinException("获取微信token失败");
        }else {
            BoundValueOperations redis = redisTemplate.boundValueOps(redis_key);
            redis.set(token.getToken());
            redis.expire(60, TimeUnit.SECONDS);
        }
        return token.getToken();
    };

    public String getAccess_token(){
        BoundValueOperations<String,String> redis = redisTemplate.boundValueOps(redis_key);
        String s = redis.get();
        if (StringUtils.isNotBlank(s)){
            return s;
        }else {
            return getAccess_tokenByHttp();
        }
    };


    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public String post(String url, String code) throws IOException {
        Map<String, Object> postData = new HashMap<String, Object>();
        postData.put("appid", appid);
        postData.put("secret", secret);
        postData.put("code", code);
        postData.put("grant_type", grant_type);
        return HttpClientUtil.post(url, postData);
    }

    /**
     * 获得微信用户的openid
     *
     * @param code
     * @return
     */
    public String getOpenid(String code) throws IOException {
        String body = this.post(html_token_url, code);
        JSONObject obj = JSON.parseObject(body);
        String openid = null;
        if (obj.containsKey("openid")) {
            openid = obj.getString("openid");
        }
        return openid;
    }

    public WxUserInfo getUserInfo(String openid) throws IOException {
        String url = user_info_url;
        url = url.replace("ACCESS_TOKEN",getAccess_token());
        url = url.replace("OPENID",openid);
        String s = HttpClientUtil.get(url);
        WxUserInfo userInfo = JSON.parseObject(s,WxUserInfo.class);
        return userInfo;
    }



}
