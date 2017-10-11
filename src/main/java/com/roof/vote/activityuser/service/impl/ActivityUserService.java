package com.roof.vote.activityuser.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.roof.vote.activity.entity.ActivityVo;
import com.roof.vote.activityuser.dao.api.IActivityUserDao;
import com.roof.vote.activityuser.entity.ActivityUser;
import com.roof.vote.activityuser.entity.ActivityUserVo;
import com.roof.vote.activityuser.service.api.IActivityUserService;

@Service
public class ActivityUserService implements IActivityUserService {
	private IActivityUserDao activityUserDao;

	public ActivityUserVo selectUserByCode(String openid, String activity_code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openid", openid);
		map.put("activity_code", activity_code);
		return (ActivityUserVo) activityUserDao.selectForObject("selectUserByCode", map);
	}

	public ActivityUserVo loadByOpenid(String openid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openid", openid);
		return (ActivityUserVo) activityUserDao.selectForObject("loadByOpenid", map);
	}

	public Serializable save(ActivityUser activityUser) {
		return activityUserDao.save(activityUser);
	}

	public void delete(ActivityUser activityUser) {
		activityUserDao.delete(activityUser);
	}

	public void deleteByExample(ActivityUser activityUser) {
		activityUserDao.deleteByExample(activityUser);
	}

	public void update(ActivityUser activityUser) {
		activityUserDao.update(activityUser);
	}

	public void updateIgnoreNull(ActivityUser activityUser) {
		activityUserDao.updateIgnoreNull(activityUser);
	}

	public void updateByExample(ActivityUser activityUser) {
		activityUserDao.update("updateByExampleActivityUser", activityUser);
	}

	public ActivityUserVo load(ActivityUser activityUser) {
		return (ActivityUserVo) activityUserDao.reload(activityUser);
	}

	public ActivityUserVo selectForObject(ActivityUser activityUser) {
		return (ActivityUserVo) activityUserDao.selectForObject("selectActivityUser", activityUser);
	}

	public List<ActivityUserVo> selectForList(ActivityUser activityUser) {
		return (List<ActivityUserVo>) activityUserDao.selectForList("selectActivityUser", activityUser);
	}

	public Page page(Page page, ActivityUser activityUser) {
		return activityUserDao.page(page, activityUser);
	}

	@Autowired
	public void setIActivityUserDao(@Qualifier("activityUserDao") IActivityUserDao activityUserDao) {
		this.activityUserDao = activityUserDao;
	}

}
