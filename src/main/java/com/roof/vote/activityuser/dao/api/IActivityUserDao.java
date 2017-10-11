package com.roof.vote.activityuser.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.vote.activityuser.entity.ActivityUser;

public interface IActivityUserDao extends IDaoSupport {
	Page page(Page page, ActivityUser activityUser);
}