package com.roof.vote.activityuser.entity;

import java.util.List;

import com.roof.vote.production.entity.ProductionVo;

/**
 * @author 模版生成 <br/>
 *         表名： v_activity_user <br/>
 *         描述：v_activity_user <br/>
 */
public class ActivityUserVo extends ActivityUser {

	private List<ActivityUserVo> activityUserList;

	private List<ProductionVo> products;

	private String actName;

	public ActivityUserVo() {
		super();
	}

	public ActivityUserVo(Long id) {
		super();
		this.id = id;
	}

	public List<ActivityUserVo> getActivityUserList() {
		return activityUserList;
	}

	public void setActivityUserList(List<ActivityUserVo> activityUserList) {
		this.activityUserList = activityUserList;
	}

	public List<ProductionVo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductionVo> products) {
		this.products = products;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}
}
