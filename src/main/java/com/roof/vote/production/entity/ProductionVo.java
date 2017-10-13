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

	private String usertel;// 参赛用户号码
	
	private String useropenid;

	private Double num;// 当前票数

	private Long index;// 当前排名

	private Double perNum;// 上一个票数

	private Double marginNum;// 票差

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

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public Double getPerNum() {
		return perNum;
	}

	public void setPerNum(Double perNum) {
		this.perNum = perNum;
	}

	public Double getMarginNum() {
		return marginNum;
	}

	public void setMarginNum(Double marginNum) {
		this.marginNum = marginNum;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUseropenid() {
		return useropenid;
	}

	public void setUseropenid(String useropenid) {
		this.useropenid = useropenid;
	}

}
