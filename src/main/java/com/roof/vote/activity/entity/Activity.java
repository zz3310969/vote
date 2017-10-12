package com.roof.vote.activity.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 模版生成 <br/>
 *         表名： v_activity <br/>
 *         描述：活动 <br/>
 */
public class Activity implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	protected Long id;// id
	protected String name;// 活动名称
	protected String code;// 活动编号
	protected String remark;// 活动描述
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	protected Date apply_start_time;// 报名开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	protected Date apply_end_time;// 报名结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	protected Date vote_start_time;// 投票开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	protected Date vote_end_time;// 投票结束时间
	protected String status;// 活动状态
	protected String vote_rule;// 投票规则
	protected Long vote_limit;// 每人每天可投票数
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	protected Date create_date;// 创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	protected Date update_date;// 更新时间

	public Activity() {
		super();
	}

	public Activity(Long id) {
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getApply_start_time() {
		return apply_start_time;
	}
	public void setApply_start_time(Date apply_start_time) {
		this.apply_start_time = apply_start_time;
	}
	
	public Date getApply_end_time() {
		return apply_end_time;
	}
	public void setApply_end_time(Date apply_end_time) {
		this.apply_end_time = apply_end_time;
	}
	
	public Date getVote_start_time() {
		return vote_start_time;
	}
	public void setVote_start_time(Date vote_start_time) {
		this.vote_start_time = vote_start_time;
	}
	
	public Date getVote_end_time() {
		return vote_end_time;
	}
	public void setVote_end_time(Date vote_end_time) {
		this.vote_end_time = vote_end_time;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getVote_rule() {
		return vote_rule;
	}
	public void setVote_rule(String vote_rule) {
		this.vote_rule = vote_rule;
	}
	
	public Long getVote_limit() {
		return vote_limit;
	}
	public void setVote_limit(Long vote_limit) {
		this.vote_limit = vote_limit;
	}
	
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
}
