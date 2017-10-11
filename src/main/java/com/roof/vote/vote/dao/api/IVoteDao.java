package com.roof.vote.vote.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.vote.vote.entity.Vote;

public interface IVoteDao extends IDaoSupport {
	Page page(Page page, Vote vote);
}