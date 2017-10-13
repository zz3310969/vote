package com.roof.core.wechat.bean;

/**
 * Created by zhenglt on 2017/8/17.
 */
public class WeJsapiTicket {
    private Integer expires_in;
    private String ticket;

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
