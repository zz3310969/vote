package com.roof.vote.activityuser.dao.impl;

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

import com.roof.vote.activityuser.dao.api.IActivityUserDao;
import com.roof.vote.activityuser.entity.ActivityUser;
@Service
public class ActivityUserDao extends AbstractDao implements IActivityUserDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, ActivityUser activityUser) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectActivityUserPage", "selectActivityUserCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectActivityUserPage", null);
		return pageQuery.select(activityUser);
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