package com.roof.vote.production.service.impl;

import java.util.List;

import org.junit.Test;
import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.roof.vote.common.ProductionStatusEnum;
import com.roof.vote.production.entity.Production;
import com.roof.vote.production.entity.ProductionVo;
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
		production.setStatus(ProductionStatusEnum.processed.getCode());
		// production.setVote_code("");
		productionService.page(page, production);
		System.out.println(JSON.toJSONString(page));
	}

	@Test
	public void testgetPro() {
		ProductionVo vo = productionService.getPro(3L);
		System.out.println(JSON.toJSONString(vo));
	}

	@Test
	public void processPro() {
		List<ProductionVo> vos = productionService.selectForList(new Production());
		for (ProductionVo productionVo : vos) {
			productionService.processPro(productionVo.getId(), true);
		}

	}

}
