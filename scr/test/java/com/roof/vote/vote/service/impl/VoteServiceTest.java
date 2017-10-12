package com.roof.vote.vote.service.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.roof.vote.vote.entity.VoteVo;
import com.roof.vote.vote.service.api.IVoteService;

@ContextConfiguration(locations = { "classpath:spring.xml" })
public class VoteServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private IVoteService voteService;

	@Test
	public void testCanVote() {
		Boolean b = voteService.canVote("toupiao1", "A-20171011-000002");
		System.out.println(b);
	}

	@Test
	public void testVoteNum() {
		Long l = voteService.voteNum("123456789", "A-20171011-000002");
		System.out.println(l);
	}

	@Test
	public void testVote() {
		VoteVo vote = new VoteVo();
		vote.setActivity_code("A-20171011-000002");
		vote.setVote_user_openid("toupiao1");
		List<VoteVo> voteList = new ArrayList<VoteVo>();
		VoteVo v1 = new VoteVo();
		v1.setVote_code("17");
		v1.setVote_num(5L);
		voteList.add(v1);
		vote.setVoteList(voteList);
		System.out.println(JSON.toJSONString(vote));
		voteService.vote(vote);
	}

	@Test
	public void testVoteReport() {
		fail("Not yet implemented");
	}

}
