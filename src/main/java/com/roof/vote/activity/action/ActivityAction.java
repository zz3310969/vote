package com.roof.vote.activity.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roof.vote.activity.entity.Activity;
import com.roof.vote.activity.service.api.IActivityService;
import com.roof.vote.common.ActivityStatusEnum;

@Controller
@RequestMapping("vote/activityAction")
public class ActivityAction {
	private IActivityService activityService;
	private IDictionaryService dictionaryService;

	// 加载页面的通用数据
	private void loadCommon(Model model) {
		List<Dictionary> dicList = dictionaryService.findByType("TEST");
		model.addAttribute("dicList", dicList);
	}

	@RequestMapping("/index")
	public String index() {
		return "/selin/activity/activity_index.jsp";
	}

	@RequestMapping("/base")
	public @ResponseBody Result base(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", activityService.createCode(new Date()));
		map.put("vals", ActivityStatusEnum.getAll());
		return new Result(Result.SUCCESS, map);
	}

	/*
	 * @RequestMapping("/list") public String list(Activity activity,
	 * HttpServletRequest request, Model model) { Page page =
	 * PageUtils.createPage(request); page = activityService.page(page,
	 * activity); model.addAttribute("page", page);
	 * model.addAllAttributes(PageUtils.createPagePar(page));
	 * this.loadCommon(model); return "/selin/activity/activity_list.jsp"; }
	 */

	@RequestMapping("/list")
	public @ResponseBody Result list(Activity activity, HttpServletRequest request, Model model) {
		Page page = PageUtils.createPage(request);
		page = activityService.page(page, activity);
		return new Result(Result.SUCCESS, page);
	}

	@RequestMapping("/create_page")
	public String create_page(Model model) {
		Activity activity = new Activity();
		model.addAttribute("activity", activity);
		this.loadCommon(model);
		return "/selin/activity/activity_create.jsp";
	}

	@RequestMapping("/update_page")
	public String update_page(Activity activity, Model model) {
		activity = activityService.load(activity);
		model.addAttribute("activity", activity);
		this.loadCommon(model);
		return "/selin/activity/activity_update.jsp";
	}

	@RequestMapping("/detail_page")
	public String detail_page(Activity activity, Model model) {
		activity = activityService.load(activity);
		model.addAttribute("activity", activity);
		this.loadCommon(model);
		return "/selin/activity/activity_detail.jsp";
	}
	
	@RequestMapping("/load")
	public @ResponseBody Result load(Activity activity) {
		activity = activityService.load(activity);
		return new Result(Result.SUCCESS,activity);
	}

	@RequestMapping("/create")
	public @ResponseBody Result create(@RequestBody Activity activity) {
		if (activity != null) {
			activityService.save(activity);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/update")
	public @ResponseBody Result update(@RequestBody Activity activity) {
		if (activity != null) {
			activityService.updateIgnoreNull(activity);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/delete")
	public @ResponseBody Result delete(Activity activity) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		activityService.delete(activity);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setActivityService(@Qualifier("activityService") IActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired(required = true)
	public void setDictionaryService(@Qualifier("dictionaryService") IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
}
