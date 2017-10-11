package com.roof.vote.activityuser.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import com.roof.vote.activityuser.entity.ActivityUser;
import com.roof.vote.activityuser.entity.ActivityUserVo;
import com.roof.vote.activityuser.service.api.IActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("vote/activityuserAction")
public class ActivityUserAction {
	private IActivityUserService activityUserService;
	private IDictionaryService dictionaryService;

	// 加载页面的通用数据
	private void loadCommon(Model model){
		List<Dictionary> dicList =  dictionaryService.findByType("TEST");
		model.addAttribute("dicList", dicList);
	}

	@RequestMapping("/index")
	public String index() {
		return "/selin/activityUser/activityUser_index.jsp";
	}

	/*
	@RequestMapping("/list")
	public String list(ActivityUser activityUser, HttpServletRequest request, Model model) {
		Page page = PageUtils.createPage(request);
		page = activityUserService.page(page, activityUser);
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		this.loadCommon(model);
		return "/selin/activityUser/activityUser_list.jsp";
	}
	*/

    @RequestMapping("/list")
    public @ResponseBody Result list(ActivityUser activityUser, HttpServletRequest request, Model model) {
    Page page = PageUtils.createPage(request);
    page = activityUserService.page(page, activityUser);
    return new Result(Result.SUCCESS,page);
	}
	
	
	@RequestMapping("/create_page")
	public String create_page(Model model) {
		ActivityUser activityUser = new ActivityUser();
		model.addAttribute("activityUser", activityUser);
		this.loadCommon(model);
		return "/selin/activityUser/activityUser_create.jsp";
	}
	
	@RequestMapping("/update_page")
	public String update_page(ActivityUser activityUser, Model model) {
		activityUser = activityUserService.load(activityUser);
		model.addAttribute("activityUser", activityUser);
		this.loadCommon(model);
		return "/selin/activityUser/activityUser_update.jsp";
	}

	@RequestMapping("/detail_page")
	public String detail_page(ActivityUser activityUser, Model model) {
		activityUser = activityUserService.load(activityUser);
		model.addAttribute("activityUser", activityUser);
		this.loadCommon(model);
		return "/selin/activityUser/activityUser_detail.jsp";
	}

	@RequestMapping("/create")
	public @ResponseBody Result create(ActivityUser activityUser) {
		if (activityUser != null) {
			activityUserService.save(activityUser);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/update")
	public @ResponseBody Result update(ActivityUser activityUser) {
		if (activityUser != null) {
			activityUserService.updateIgnoreNull(activityUser);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Result delete(ActivityUser activityUser) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		activityUserService.delete(activityUser);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setActivityUserService(
			@Qualifier("activityUserService") IActivityUserService activityUserService) {
		this.activityUserService = activityUserService;
	}

	@Autowired(required = true)
	public void setDictionaryService(@Qualifier("dictionaryService") IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
}
