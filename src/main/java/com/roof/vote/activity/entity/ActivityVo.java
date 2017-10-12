package com.roof.vote.activity.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： v_activity <br/>
 *         描述：活动 <br/>
 */
public class ActivityVo extends Activity {

	private List<ActivityVo> activityList;

	private String statusname;

	public ActivityVo() {
		super();
	}

	public ActivityVo(Long id) {
		super();
		this.id = id;
	}

	public List<ActivityVo> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityVo> activityList) {
		this.activityList = activityList;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

}
