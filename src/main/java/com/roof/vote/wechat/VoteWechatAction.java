package com.roof.vote.wechat;

import javax.servlet.http.HttpServletRequest;

import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roof.vote.common.ProductionStatusEnum;
import com.roof.vote.production.entity.Production;
import com.roof.vote.production.entity.ProductionVo;
import com.roof.vote.production.service.api.IProductionService;
import com.roof.vote.vote.entity.VoteVo;
import com.roof.vote.vote.service.api.IVoteService;

@Controller
@RequestMapping("vote/wechat/voteWechatAction")
public class VoteWechatAction {
	@Autowired
	private IVoteService voteService;

	@Autowired
	private IProductionService productionService;

	@RequestMapping("/getPro")
	public @ResponseBody Result getPro(Long id, HttpServletRequest request, Model model) {
		try {
			ProductionVo vo = productionService.getPro(id);
			return new Result(Result.SUCCESS, vo);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}
	}

	// 根据活动查询，所有审核通过的作品列表,以及对应的票数
	@RequestMapping("/pagePros")
	public @ResponseBody Result pagePros(Production product, HttpServletRequest request, Model model) {
		try {
			Page page = PageUtils.createPage(request);
			if (product == null) {
				product = new Production();
			}
			product.setStatus(ProductionStatusEnum.processed.getCode());
			page = productionService.page(page, product);
			return new Result(Result.SUCCESS, page);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}
	}

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
	public @ResponseBody Result vote(VoteVo vote, HttpServletRequest request, Model model) {
		try {
			voteService.vote(vote);
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
