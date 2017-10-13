package com.roof.vote.vote.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roof.vote.production.entity.ProductionVo;
import com.roof.vote.production.service.api.IProductionService;
import com.roof.vote.vote.entity.Vote;
import com.roof.vote.vote.entity.VoteVo;
import com.roof.vote.vote.service.api.IVoteService;

@Controller
@RequestMapping("vote/voteAction")
public class VoteAction {
	private IVoteService voteService;
	private IDictionaryService dictionaryService;

	@Autowired
	private IProductionService productionService;

	// 加载页面的通用数据
	private void loadCommon(Model model) {
		List<Dictionary> dicList = dictionaryService.findByType("TEST");
		model.addAttribute("dicList", dicList);
	}

	@RequestMapping("/index")
	public String index() {
		return "/selin/vote/vote_index.jsp";
	}

	/*
	 * @RequestMapping("/list") public String list(Vote vote, HttpServletRequest
	 * request, Model model) { Page page = PageUtils.createPage(request); page =
	 * voteService.page(page, vote); model.addAttribute("page", page);
	 * model.addAllAttributes(PageUtils.createPagePar(page));
	 * this.loadCommon(model); return "/selin/vote/vote_list.jsp"; }
	 */

	@RequestMapping("/list")
	public @ResponseBody Result list(VoteVo vote, HttpServletRequest request, Model model) {
		Page page = PageUtils.createPage(request);
		page = voteService.page(page, vote);
		return new Result(Result.SUCCESS, page);
	}

	@RequestMapping("/reportByoneAct")
	public @ResponseBody Result reportByoneAct(VoteVo vote, String acode, HttpServletRequest request, Model model) {
		List<ProductionVo> pros = productionService.selectPros(acode);
		return new Result(Result.SUCCESS, pros);
	}

	@RequestMapping("/create_page")
	public String create_page(Model model) {
		Vote vote = new Vote();
		model.addAttribute("vote", vote);
		this.loadCommon(model);
		return "/selin/vote/vote_create.jsp";
	}

	@RequestMapping("/update_page")
	public String update_page(Vote vote, Model model) {
		vote = voteService.load(vote);
		model.addAttribute("vote", vote);
		this.loadCommon(model);
		return "/selin/vote/vote_update.jsp";
	}

	@RequestMapping("/detail_page")
	public String detail_page(Vote vote, Model model) {
		vote = voteService.load(vote);
		model.addAttribute("vote", vote);
		this.loadCommon(model);
		return "/selin/vote/vote_detail.jsp";
	}

	@RequestMapping("/create")
	public @ResponseBody Result create(Vote vote) {
		if (vote != null) {
			voteService.save(vote);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/update")
	public @ResponseBody Result update(Vote vote) {
		if (vote != null) {
			voteService.updateIgnoreNull(vote);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/delete")
	public @ResponseBody Result delete(Vote vote) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		voteService.delete(vote);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setVoteService(@Qualifier("voteService") IVoteService voteService) {
		this.voteService = voteService;
	}

	@Autowired(required = true)
	public void setDictionaryService(@Qualifier("dictionaryService") IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
}
