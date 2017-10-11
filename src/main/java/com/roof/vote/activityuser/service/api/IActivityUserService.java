package com.roof.vote.activityuser.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;

import com.roof.vote.activity.entity.ActivityVo;
import com.roof.vote.activityuser.entity.ActivityUser;
import com.roof.vote.activityuser.entity.ActivityUserVo;

public interface IActivityUserService {
	public ActivityUserVo loadByOpenid(String openid);

	public ActivityUserVo selectUserByCode(String openid, String activity_code);

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ActivityUser activityUser);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ActivityUser activityUser);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(ActivityUser activityUser);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ActivityUser activityUser);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(ActivityUser activityUser);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(ActivityUser activityUser);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ActivityUserVo load(ActivityUser activityUser);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ActivityUserVo selectForObject(ActivityUser activityUser);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ActivityUserVo> selectForList(ActivityUser activityUser);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ActivityUser activityUser);

}