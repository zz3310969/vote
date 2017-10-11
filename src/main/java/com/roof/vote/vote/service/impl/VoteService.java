package com.roof.vote.vote.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.roof.vote.activity.entity.ActivityVo;
import com.roof.vote.activity.service.api.IActivityService;
import com.roof.vote.common.ActivityStatusEnum;
import com.roof.vote.exception.VoteException;
import com.roof.vote.vote.dao.api.IVoteDao;
import com.roof.vote.vote.entity.Vote;
import com.roof.vote.vote.entity.VoteVo;
import com.roof.vote.vote.service.api.IVoteService;

@Service
public class VoteService implements IVoteService {
	private IVoteDao voteDao;

	@Autowired
	private IActivityService activityService;

	/** 是否可以投票 */
	public String canVote(String openid, String acode) throws VoteException {
		ActivityVo avo = activityService.selelctActivityByCode(acode);
		if (!avo.getStatus().equals(ActivityStatusEnum.inProgress.getCode())) {
			throw new VoteException("活动不能投票");
		}
//		Long 
		
		return null;
	}

	/** 可投票数 */
	public Long voteNum(String openid, String acode) throws VoteException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("openid", openid);
		map.put("acode", acode);
		
		voteDao.selectForObject("",map);
		
		
		return null;
	}

	/** 投票 */
	public void vote(List<VoteVo> votes) throws VoteException {

	}

	/** 投票统计 */
	public void voteReport(String acode) throws VoteException {
	}

	public Serializable save(Vote vote) {
		return voteDao.save(vote);
	}

	public void delete(Vote vote) {
		voteDao.delete(vote);
	}

	public void deleteByExample(Vote vote) {
		voteDao.deleteByExample(vote);
	}

	public void update(Vote vote) {
		voteDao.update(vote);
	}

	public void updateIgnoreNull(Vote vote) {
		voteDao.updateIgnoreNull(vote);
	}

	public void updateByExample(Vote vote) {
		voteDao.update("updateByExampleVote", vote);
	}

	public VoteVo load(Vote vote) {
		return (VoteVo) voteDao.reload(vote);
	}

	public VoteVo selectForObject(Vote vote) {
		return (VoteVo) voteDao.selectForObject("selectVote", vote);
	}

	public List<VoteVo> selectForList(Vote vote) {
		return (List<VoteVo>) voteDao.selectForList("selectVote", vote);
	}

	public Page page(Page page, Vote vote) {
		return voteDao.page(page, vote);
	}

	@Autowired
	public void setIVoteDao(@Qualifier("voteDao") IVoteDao voteDao) {
		this.voteDao = voteDao;
	}

}
