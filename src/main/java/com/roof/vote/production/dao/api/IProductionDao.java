package com.roof.vote.production.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.vote.production.entity.Production;

public interface IProductionDao extends IDaoSupport {
	Page page(Page page, Production production);
	Page page_(Page page, Production production);
}