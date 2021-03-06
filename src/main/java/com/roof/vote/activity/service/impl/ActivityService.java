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
import com.roof.vote.vote.entity.VoteVo;
import com.roof.vote.vote.service.api.IVoteService;

@Service
public class ActivityService implements IActivityService {
	private IActivityDao activityDao;

	public final static String CODEPREFIX = "A";
	/**
	 * 当前活动对应的作品序号
	 */
	public final static String VOTECODEPREFIX = "VN#";
	/**
	 * 当前活动对应作品票数
	 */
	public final static String VOTEPRODUCTIONPREFIX = "VP#";
	/**
	 * 当前活动对应的票数排名
	 */
	public final static String VOTEZSET = "VZSET#";

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private IActivityUserService activityUserService;

	@Autowired
	private IProductionService productionService;

	@Autowired
	private IVoteService voteService;


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
	public String createVoteCode(Date date, String acode) {
		String key = VOTECODEPREFIX + acode;
		BoundValueOperations<String, Long> operations = redisTemplate.boundValueOps(key);// .increment(1);
		Long l = operations.increment(1);
		// operations.expire(2, TimeUnit.DAYS);
		// String s = "00000" + l;
		// s = s.substring(s.length() - 6, s.length());
		// String str = key + "-" + s;
		return l + "";
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
			uservo = new ActivityUserVo();
			uservo.setId(activityUser.getId());
		}
		ActivityVo avo = this.selelctActivityByCode(pvo.getActivity_code());
		if (new Date().getTime() > avo.getApply_end_time().getTime() || new Date().getTime()< avo.getApply_start_time().getTime()) {
			throw new VoteException("不在报名时间内");
		}
		// 新增活动，状态为待审核
		Production p = new Production();
		BeanUtils.copyProperties(pvo, p);
		p.setCreate_date(new Date());
		p.setStatus(ProductionStatusEnum.waitProcess.getCode());
		p.setUpload_date(new Date());
		p.setUser_id(uservo.getId());
//		p.setVote_code(this.createVoteCode(new Date(), pvo.getActivity_code()));
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
		if (pvo.getUser() != null) {
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
		for (ProductionVo productionVo : pvos) {
			VoteVo votevo = voteService.groupVoteNumByAcodeVcode(productionVo.getActivity_code(),
					productionVo.getVote_code());
			// String key =
			// Vote.createVoteZsetKey(productionVo.getActivity_code());
			// BoundZSetOperations operations = redisTemplate.boundZSetOps(key);
			// Double d =
			// operations.score(Vote.createVoteZsetValueKey(productionVo.getVote_code()));
			// productionVo.setNum(d != null ? d : 0D);
			if (votevo != null) {
				productionVo.setNum(Double.valueOf(votevo.getVote_num()));
			} else {
				productionVo.setNum(0D);
			}

			productionVo.setProStatusName(ProductionStatusEnum.getStatusEnumName(productionVo.getStatus()));
		}
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
		activityDao.page(page, activity);
		List<ActivityVo> list = (List<ActivityVo>) page.getDataList();
		for (ActivityVo activityVo : list) {
			activityVo.setStatusname(ActivityStatusEnum.getStatusEnumName(activityVo.getStatus()));
		}
		page.setDataList(list);
		return page;
	}

	@Autowired
	public void setIActivityDao(@Qualifier("activityDao") IActivityDao activityDao) {
		this.activityDao = activityDao;
	}

}
