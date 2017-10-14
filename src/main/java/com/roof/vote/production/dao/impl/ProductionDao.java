package com.roof.vote.production.dao.impl;

import java.util.Comparator;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.roof.dataaccess.PageQuery;
import org.roof.roof.dataaccess.api.AbstractDao;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.IPageQuery;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.roof.vote.production.dao.api.IProductionDao;
import com.roof.vote.production.entity.Production;
@Service
public class ProductionDao extends AbstractDao implements IProductionDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Production production) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProductionPage", "selectProductionCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProductionPage", null);
		return pageQuery.select(production);
	}

	public Page page_(Page page, Production production) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProductionPage_", "selectProductionCount");
		return pageQuery.select(production);
	}
	
	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}
	
	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}