package com.roof.vote.vote.service.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.roof.roof.dataaccess.api.Page;

import com.roof.vote.exception.VoteException;
import com.roof.vote.vote.entity.Vote;
import com.roof.vote.vote.entity.VoteVo;

public interface IVoteService {
	/** 是否可以投票 */
	public Boolean canVote(String openid, String acode) throws VoteException;

	/** 可投票数 */
	public Long voteNum(String openid, String acode) throws VoteException;

	/** 投票 */
	public void vote(List<VoteVo> votes) throws VoteException;

	/** 投票统计 */
	public void voteReport(String acode) throws VoteException;

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Vote vote);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Vote vote);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Vote vote);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Vote vote);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Vote vote);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Vote vote);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract VoteVo load(Vote vote);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract VoteVo selectForObject(Vote vote);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<VoteVo> selectForList(Vote vote);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Vote vote);

}