package com.roof.vote.vote.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 模版生成 <br/>
 *         表名： v_vote <br/>
 *         描述：v_vote <br/>
 */
public class VoteVo extends Vote {

	private List<VoteVo> voteList;

	private String proName;

	private String proUserName;

	private String proUserTel;

	private String proUserOpenid;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date vote_start_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date vote_end_date;

	private String actName;
	
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

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProUserName() {
		return proUserName;
	}

	public void setProUserName(String proUserName) {
		this.proUserName = proUserName;
	}

	public String getProUserTel() {
		return proUserTel;
	}

	public void setProUserTel(String proUserTel) {
		this.proUserTel = proUserTel;
	}

	public String getProUserOpenid() {
		return proUserOpenid;
	}

	public void setProUserOpenid(String proUserOpenid) {
		this.proUserOpenid = proUserOpenid;
	}

	public Date getVote_start_date() {
		return vote_start_date;
	}

	public void setVote_start_date(Date vote_start_date) {
		this.vote_start_date = vote_start_date;
	}

	public Date getVote_end_date() {
		return vote_end_date;
	}

	public void setVote_end_date(Date vote_end_date) {
		this.vote_end_date = vote_end_date;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

}
