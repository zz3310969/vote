package com.roof.vote.activity.service.api;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.roof.roof.dataaccess.api.Page;

import com.roof.vote.activity.entity.Activity;
import com.roof.vote.activity.entity.ActivityVo;
import com.roof.vote.activityuser.entity.ActivityUserVo;
import com.roof.vote.exception.VoteException;
import com.roof.vote.production.entity.Production;
import com.roof.vote.production.entity.ProductionVo;

public interface IActivityService {
	public ActivityVo selelctActivityByCode(String code);

	public String createCode(Date date);

	/** 是否可以报名 */
	public Boolean canApply(String code) throws VoteException, ParseException;

	/** 报名 */
	public Production apply(ProductionVo pvo) throws VoteException;

	/** 取消报名 */
	public void cancelApply(ProductionVo pvo) throws VoteException;

	/** 更新报名 */
	public Production updateApply(ProductionVo pvo) throws VoteException;

	/** 报名信息查询 */
	public ActivityUserVo pageQueryApply(ActivityUserVo uvo) throws VoteException;

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Activity activity);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Activity activity);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Activity activity);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Activity activity);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Activity activity);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Activity activity);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ActivityVo load(Activity activity);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ActivityVo selectForObject(Activity activity);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ActivityVo> selectForList(Activity activity);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Activity activity);

}