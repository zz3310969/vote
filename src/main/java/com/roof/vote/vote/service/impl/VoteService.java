package com.roof.vote.vote.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	/** 今天是否可以投票 */
	public Boolean canVote(String openid, String acode) throws VoteException {
		Long l = this.voteNum(openid, acode);
		if (l > 0) {
			return true;
		}
		return false;
	}

	/** 今天可投票数 */
	public Long voteNum(String openid, String acode) throws VoteException {
		ActivityVo avo = activityService.selelctActivityByCode(acode);
		if (!avo.getStatus().equals(ActivityStatusEnum.inProgress.getCode())) {
			throw new VoteException("活动不能投票");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vote_user_openid", openid);
		map.put("activity_code", acode);
		Long l = (Long) voteDao.selectForObject("selectvoteNum", map);
		long v = avo.getVote_limit().longValue() - (l != null ? l : 0);
		return new Long(v);
	}

	/** 投票 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void vote(VoteVo vote) throws VoteException {
		if (vote.getActivity_code() == null) {
			throw new VoteException("活动编号不能为空");
		}
		if (vote.getVote_user_openid() == null) {
			throw new VoteException("参与用户openid不能为空");
		}
		if (vote.getVote_code() == null) {
			throw new VoteException("所投作品不能为空");
		}
		if (vote.getVote_num() == null) {
			throw new VoteException("所投票数不能为空");
		}
		ActivityVo avo = activityService.selelctActivityByCode(vote.getActivity_code());
		if (!avo.getStatus().equals(ActivityStatusEnum.inProgress.getCode())) {
			throw new VoteException("活动不能投票");
		}
		Vote v = new Vote();
		BeanUtils.copyProperties(vote, v);
		v.setVote_date(new Date());
		v.setActivity_code(vote.getActivity_code());
		v.setVote_user_openid(vote.getVote_user_openid());
		this.save(v);
		// zset+1
		this.redisIncrement(vote.getActivity_code(), vote.getVote_code(), vote.getVote_num());

	}

	public void redisIncrement(String acode, String vcode, Long num) {
		String zsetKey = Vote.createVoteZsetKey(acode);
		BoundZSetOperations operations = redisTemplate.boundZSetOps(zsetKey);
		operations.incrementScore(Vote.createVoteZsetValueKey(vcode), num);
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
