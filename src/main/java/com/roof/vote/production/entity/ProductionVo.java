package com.roof.vote.production.entity;

import java.util.List;

import com.roof.vote.activityuser.entity.ActivityUserVo;

/**
 * @author 模版生成 <br/>
 *         表名： v_production <br/>
 *         描述：v_production <br/>
 */
public class ProductionVo extends Production {

	private List<ProductionVo> productionList;

	private ActivityUserVo user;

	private String proStatusName;

	private String actName;

	private String actRemark;

	private String actStatus;

	private String actStatusName;

	private Long vote_limit;

	private String username;// 参赛用户名

	private Long num;// 票数

	public ProductionVo() {
		super();
	}

	public ProductionVo(Long id) {
		super();
		this.id = id;
	}

	public List<ProductionVo> getProductionList() {
		return productionList;
	}

	public void setProductionList(List<ProductionVo> productionList) {
		this.productionList = productionList;
	}

	public ActivityUserVo getUser() {
		return user;
	}

	public void setUser(ActivityUserVo user) {
		this.user = user;
	}

	public String getProStatusName() {
		return proStatusName;
	}

	public void setProStatusName(String proStatusName) {
		this.proStatusName = proStatusName;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActRemark() {
		return actRemark;
	}

	public void setActRemark(String actRemark) {
		this.actRemark = actRemark;
	}

	public String getActStatus() {
		return actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public String getActStatusName() {
		return actStatusName;
	}

	public void setActStatusName(String actStatusName) {
		this.actStatusName = actStatusName;
	}

	public Long getVote_limit() {
		return vote_limit;
	}

	public void setVote_limit(Long vote_limit) {
		this.vote_limit = vote_limit;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

}
