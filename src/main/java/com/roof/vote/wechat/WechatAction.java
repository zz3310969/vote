package com.roof.vote.wechat;

import com.roof.core.wechat.WeChatHander;
import com.roof.core.wechat.bean.WxUserInfo;
import com.roof.vote.production.entity.ProductionVo;
import org.apache.commons.lang3.StringUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zhenglt on 2017/10/12.
 */
@RequestMapping("/wechat")
@Controller
public class WechatAction {

    @Autowired
    private WeChatHander weChatHander;


    @RequestMapping(value = "/base",method = {RequestMethod.GET})
    public @ResponseBody
    Result base(String code, HttpServletRequest request, Model model) throws IOException {
        if(StringUtils.isEmpty(code)){
            return new Result(Result.ERROR,"code不能为空");
        }
        String openid = weChatHander.getOpenid(code);
        return new Result(Result.SUCCESS,"",openid);
    }

    @RequestMapping(value = "/headimage",method = {RequestMethod.GET})
    public @ResponseBody
    Result headImage(String openid, HttpServletRequest request, Model model) throws IOException {
        if(StringUtils.isEmpty(openid)){
            return new Result(Result.ERROR,"openid不能为空");
        }
        WxUserInfo userInfo = weChatHander.getUserInfo(openid);
        return new Result(Result.SUCCESS,"",userInfo.getHeadimgurl());
    }
}
