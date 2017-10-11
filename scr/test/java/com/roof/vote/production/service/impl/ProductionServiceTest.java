package com.roof.vote.production.service.impl;

import org.junit.Test;
import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.roof.vote.production.entity.Production;
import com.roof.vote.production.service.api.IProductionService;

@ContextConfiguration(locations = { "classpath:spring.xml" })
public class ProductionServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private IProductionService productionService;

	@Test
	public void testPage() {
		Page page = new Page();
		page.setLimit(10L);
		Production production = new Production();
		production.setActivity_code("A-20171011-000002");
		// production.setVote_code("");
		productionService.page(page, production); 
		System.out.println(JSON.toJSONString(page));
	}

}
