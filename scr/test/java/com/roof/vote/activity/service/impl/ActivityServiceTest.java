package com.roof.vote.activity.service.impl;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.roof.commons.RoofDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.roof.vote.activity.entity.Activity;
import com.roof.vote.activity.service.api.IActivityService;
import com.roof.vote.activityuser.entity.ActivityUserVo;
import com.roof.vote.common.ActivityStatusEnum;
import com.roof.vote.common.ProductionStatusEnum;
import com.roof.vote.common.VoteFileService;
import com.roof.vote.exception.VoteException;
import com.roof.vote.production.entity.ProductionVo;

@ContextConfiguration(locations = { "classpath:spring.xml" })
public class ActivityServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private IActivityService activityService;
	@Autowired
	private VoteFileService voteFileService;

	@Test
	public void testCanApply() throws VoteException, ParseException {
		Boolean b = activityService.canApply("A-20171011-000002");
		System.out.println(b);
	}

	@Test
	public void testApply() {
		for (int i = 0; i < 1; i++) {
			ProductionVo pvo = new ProductionVo();
			pvo.setActivity_code("A-20171012-000040");
			pvo.setImg_src("xxxxxxx");
			pvo.setName("郑量天来比赛" + i);
			pvo.setRemark("我是天天" + i);
			ActivityUserVo u = new ActivityUserVo();
			u.setActivity_code("A-20171011-000002");
			u.setName("整了两天1" + i);
			u.setOpenid("aaaa" + i);
			u.setTel("1332533321");
			pvo.setUser(u);
			activityService.apply(pvo);
		}
	}

	@Test
	public void testCancelApply() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateApply() {
		ProductionVo pvo = new ProductionVo();
		pvo.setId(2L);
		pvo.setStatus(ProductionStatusEnum.processed.getCode());
		ActivityUserVo u = new ActivityUserVo();
		u.setActivity_code("A-20171011-000002");
		u.setName("1111");
		u.setOpenid("ddsdfdsfdsfsdfsdfsdfsdfsd111111");
		u.setTel("1111");
		pvo.setUser(u);
		activityService.updateApply(pvo);
	}

	@Test
	public void testPageQueryApply() {
		ActivityUserVo uvo = new ActivityUserVo();
		uvo.setOpenid("ddsdfdsfdsfsdfsdfsdfsdfsd111111");
		ActivityUserVo vo = activityService.pageQueryApply(uvo);
		System.out.println(JSON.toJSONString(vo));
	}

	@Test
	public void testSave() {
		String code = activityService.createCode(new Date());
		Activity activity = new Activity();
		activity.setApply_end_time(RoofDateUtils.addDay(new Date(), -1));
		activity.setApply_start_time(RoofDateUtils.addDay(new Date(), 1));
		activity.setCode(code);
		activity.setCreate_date(new Date());
		activity.setRemark(code + "新建活动");
		activity.setName(code + "活动");
		activity.setStatus(ActivityStatusEnum.newact.getCode());
		activity.setVote_end_time(RoofDateUtils.addDay(new Date(), 10));
		activity.setVote_start_time(RoofDateUtils.addDay(new Date(), 2));
		activity.setVote_limit(10L);
		activityService.save(activity);
	}

	@Test
	public void testDelete() {
		Activity activity = new Activity();
		activity.setStatus(ActivityStatusEnum.inProgress.getCode());
		activity.setUpdate_date(new Date());
		activity.setId(1L);
		activityService.updateIgnoreNull(activity);
	}

	@Test
	public void testUpdateIgnoreNull() {
		Activity activity = new Activity();
		activity.setId(1L);
		activity.setVote_end_time(RoofDateUtils.addDay(new Date(), 10));
		activity.setVote_start_time(RoofDateUtils.addDay(new Date(), 2));
		activity.setUpdate_date(new Date());
		activityService.updateIgnoreNull(activity);
	}

	@Test
	public void testPage() {

	}

}
