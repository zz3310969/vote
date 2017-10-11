package com.roof.vote.wechat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roof.vote.vote.entity.VoteVo;
import com.roof.vote.vote.service.api.IVoteService;

@Controller
@RequestMapping("vote/wechat/voteWechatAction")
public class VoteWechatAction {
	@Autowired
	private IVoteService voteService;

	@RequestMapping("/canVote")
	public @ResponseBody Result canVote(String openid, String acode, HttpServletRequest request, Model model) {
		try {
			Boolean s = voteService.canVote(openid, acode);
			return new Result(Result.SUCCESS, s);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}
	}

	@RequestMapping("/voteNum")
	public @ResponseBody Result voteNum(String openid, String acode, HttpServletRequest request, Model model) {
		try {
			Long b = voteService.voteNum(openid, acode);
			return new Result(Result.SUCCESS, b);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}

	}

	@RequestMapping("/vote")
	public @ResponseBody Result vote(List<VoteVo> votes, HttpServletRequest request, Model model) {
		try {
			voteService.vote(votes);
			return new Result(Result.SUCCESS);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}
	}

	@RequestMapping("/voteReport")
	public @ResponseBody Result voteReport(String acode, HttpServletRequest request, Model model) {
		try {
			voteService.voteReport(acode);
			return new Result(Result.SUCCESS);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}

	}

}
