package com.roof.vote.vote.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： v_vote <br/>
 *         描述：v_vote <br/>
 */
public class Vote implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	protected Long id;// id
	protected String activity_code;// 活动编号
	protected String vote_user_openid;// 投票人openid
	protected Long vote_num;// 投票数
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date vote_date;// 投票日期
	protected String vote_code;// 投票编号

	public Vote() {
		super();
	}

	public Vote(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getActivity_code() {
		return activity_code;
	}
	public void setActivity_code(String activity_code) {
		this.activity_code = activity_code;
	}
	
	public String getVote_user_openid() {
		return vote_user_openid;
	}
	public void setVote_user_openid(String vote_user_openid) {
		this.vote_user_openid = vote_user_openid;
	}
	
	public Long getVote_num() {
		return vote_num;
	}
	public void setVote_num(Long vote_num) {
		this.vote_num = vote_num;
	}
	
	public Date getVote_date() {
		return vote_date;
	}
	public void setVote_date(Date vote_date) {
		this.vote_date = vote_date;
	}
	
	public String getVote_code() {
		return vote_code;
	}
	public void setVote_code(String vote_code) {
		this.vote_code = vote_code;
	}
}
