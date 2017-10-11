package com.roof.vote.vote.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.roof.vote.vote.service.api.IVoteService;

@ContextConfiguration(locations = { "classpath:spring.xml" })
public class VoteServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private IVoteService voteService;

	@Test
	public void testCanVote() {
		fail("Not yet implemented");
	}

	@Test
	public void testVoteNum() {
		fail("Not yet implemented");
	}

	@Test
	public void testVote() {
		fail("Not yet implemented");
	}

	@Test
	public void testVoteReport() {
		fail("Not yet implemented");
	}

}
