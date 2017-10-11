package com.roof.vote.activity.service.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.roof.commons.RoofDateUtils;
import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.roof.vote.activity.dao.api.IActivityDao;
import com.roof.vote.activity.entity.Activity;
import com.roof.vote.activity.entity.ActivityVo;
import com.roof.vote.activity.service.api.IActivityService;
import com.roof.vote.activityuser.entity.ActivityUser;
import com.roof.vote.activityuser.entity.ActivityUserVo;
import com.roof.vote.activityuser.service.api.IActivityUserService;
import com.roof.vote.common.ActivityStatusEnum;
import com.roof.vote.common.ProductionStatusEnum;
import com.roof.vote.exception.VoteException;
import com.roof.vote.production.entity.Production;
import com.roof.vote.production.entity.ProductionVo;
import com.roof.vote.production.service.api.IProductionService;

@Service
public class ActivityService implements IActivityService {
	private IActivityDao activityDao;

	public final static String CODEPREFIX = "A";
	public final static String VOTECODEPREFIX = "VO";

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private IActivityUserService activityUserService;

	@Autowired
	private IProductionService productionService;

	@SuppressWarnings("unchecked")
	public String createCode(Date date) {
		String key = CODEPREFIX + "-" + RoofDateUtils.dateToString(date, "yyyyMMdd");
		BoundValueOperations<String, Long> operations = redisTemplate.boundValueOps(key);// .increment(1);
		Long l = operations.increment(1);
		operations.expire(2, TimeUnit.DAYS);
		String s = "00000" + l;
		s = s.substring(s.length() - 6, s.length());
		String str = key + "-" + s;
		return str;
	}

	@SuppressWarnings("unchecked")
	public String createVoteCode(Date date) {
		String key = VOTECODEPREFIX + "-" + RoofDateUtils.dateToString(date, "yyyyMMdd");
		BoundValueOperations<String, Long> operations = redisTemplate.boundValueOps(key);// .increment(1);
		Long l = operations.increment(1);
		operations.expire(2, TimeUnit.DAYS);
		String s = "00000" + l;
		s = s.substring(s.length() - 6, s.length());
		String str = key + "-" + s;
		return str;
	}

	/**
	 * 是否可以报名
	 * 
	 * @throws ParseException
	 */
	public Boolean canApply(String code) throws VoteException, ParseException {
		ActivityVo av = this.selelctActivityByCode(code);
		Date d = RoofDateUtils.getNowDate();
		if (!av.getStatus().equals(ActivityStatusEnum.inProgress.getCode())) {
			return false;
		}
		if (d.getTime() <= av.getApply_end_time().getTime() && d.getTime() >= av.getApply_start_time().getTime()) {
			return true;
		}
		return false;

	}

	/** 报名 */
	@SuppressWarnings("null")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Production apply(ProductionVo pvo) throws VoteException {
		if (pvo.getUser() == null) {
			throw new VoteException("投票用户不能为空");
		}
		if (pvo.getActivity_code() == null) {
			throw new VoteException("活动编号不能为空");
		}
		if (pvo.getImg_src() == null) {
			throw new VoteException("作品不能为空");
		}
		if (pvo.getUser().getOpenid() == null) {
			throw new VoteException("报名用户的信息不能为空");
		}
		// 查询是否有该活动已有openid的用户，没有新建,返回id
		ActivityUserVo uservo = activityUserService.selectUserByCode(pvo.getUser().getOpenid(), pvo.getActivity_code());
		if (uservo == null) {
			ActivityUser activityUser = new ActivityUser();
			activityUser.setActivity_code(pvo.getActivity_code());
			activityUser.setName(pvo.getUser().getName());
			activityUser.setOpenid(pvo.getUser().getOpenid());
			activityUser.setTel(pvo.getUser().getTel());
			activityUserService.save(activityUser);
			uservo.setId(activityUser.getId());
		}
		// 新增活动，状态为待审核
		Production p = new Production();
		BeanUtils.copyProperties(pvo, p);
		p.setCreate_date(new Date());
		p.setStatus(ProductionStatusEnum.waitProcess.getCode());
		p.setUpload_date(new Date());
		p.setUser_id(uservo.getId());
		p.setVote_code(this.createVoteCode(new Date()));
		productionService.save(p);
		return p;
	}

	/** 取消报名 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cancelApply(ProductionVo pvo) throws VoteException {
		if (pvo.getId() == null) {
			throw new VoteException("作品id不能为空");
		}
		Production p = new Production();
		p.setId(pvo.getId());
		productionService.updateIgnoreNull(p);
	}

	/** 更新报名 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Production updateApply(ProductionVo pvo) throws VoteException {
		if (pvo.getId() == null) {
			throw new VoteException("作品id不能为空");
		}
		// 更新作品信息
		Production p = new Production();
		BeanUtils.copyProperties(pvo, p);
		p.setStatus(ProductionStatusEnum.waitProcess.getCode());
		p.setUpdate_date(new Date());
		productionService.updateIgnoreNull(p);
		// 更新用户信息
		if (pvo.getUser() == null) {
			ProductionVo v_pvo = productionService.load(new Production(pvo.getId()));
			ActivityUser activityUser = new ActivityUser();
			BeanUtils.copyProperties(pvo.getUser(), activityUser);
			activityUser.setId(v_pvo.getUser_id());
			activityUserService.updateIgnoreNull(activityUser);
		}
		return p;
	}

	/** 报名信息查询 */
	public ActivityUserVo pageQueryApply(ActivityUserVo uvo) throws VoteException {
		if (uvo.getOpenid() == null) {
			throw new VoteException("用户openid不能为空");
		}
		ActivityUserVo uservo = activityUserService.loadByOpenid(uvo.getOpenid());
		List<ProductionVo> pvos = productionService.selectProductByuserid(uservo.getId());
		uservo.setProducts(pvos);
		return uservo;
	}

	public ActivityVo selelctActivityByCode(String code) {
		return (ActivityVo) activityDao.selectForObject("loadActivityByCode", code);
	}

	public Serializable save(Activity activity) {
		return activityDao.save(activity);
	}

	public void delete(Activity activity) {
		activityDao.delete(activity);
	}

	public void deleteByExample(Activity activity) {
		activityDao.deleteByExample(activity);
	}

	public void update(Activity activity) {
		activityDao.update(activity);
	}

	public void updateIgnoreNull(Activity activity) {
		activityDao.updateIgnoreNull(activity);
	}

	public void updateByExample(Activity activity) {
		activityDao.update("updateByExampleActivity", activity);
	}

	public ActivityVo load(Activity activity) {
		return (ActivityVo) activityDao.reload(activity);
	}

	public ActivityVo selectForObject(Activity activity) {
		return (ActivityVo) activityDao.selectForObject("selectActivity", activity);
	}

	@SuppressWarnings("unchecked")
	public List<ActivityVo> selectForList(Activity activity) {
		return (List<ActivityVo>) activityDao.selectForList("selectActivity", activity);
	}

	public Page page(Page page, Activity activity) {
		return activityDao.page(page, activity);
	}

	@Autowired
	public void setIActivityDao(@Qualifier("activityDao") IActivityDao activityDao) {
		this.activityDao = activityDao;
	}

}
