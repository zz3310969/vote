package com.roof.vote.vote.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： v_vote <br/>
 *         描述：v_vote <br/>
 */
public class VoteVo extends Vote {

	private List<VoteVo> voteList;

	public VoteVo() {
		super();
	}

	public VoteVo(Long id) {
		super();
		this.id = id;
	}

	public List<VoteVo> getVoteList() {
		return voteList;
	}

	public void setVoteList(List<VoteVo> voteList) {
		this.voteList = voteList;
	}

}
