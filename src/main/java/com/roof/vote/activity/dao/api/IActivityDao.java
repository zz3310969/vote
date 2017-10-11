package com.roof.vote.activity.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.vote.activity.entity.Activity;

public interface IActivityDao extends IDaoSupport {
	Page page(Page page, Activity activity);
}