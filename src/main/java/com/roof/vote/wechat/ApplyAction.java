package com.roof.vote.wechat;

import javax.servlet.http.HttpServletRequest;

import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roof.vote.activity.service.api.IActivityService;
import com.roof.vote.activityuser.entity.ActivityUserVo;
import com.roof.vote.production.entity.Production;
import com.roof.vote.production.entity.ProductionVo;

@Controller
@RequestMapping("vote/applyAction")
public class ApplyAction {
	@Autowired
	private IActivityService activityService;

	/**
	 * 报名信息查询#根据openid查询用户和作品信息
	 * 
	 * @param user
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/loadUserInfo")
	public @ResponseBody Result loadUserInfo(ActivityUserVo user, HttpServletRequest request, Model model) {
		try {
			ActivityUserVo uservo = activityService.pageQueryApply(user);
			return new Result(Result.SUCCESS, uservo);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * 是否可以报名
	 * 
	 * @param code
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/canApply")
	public @ResponseBody Result canApply(String code, HttpServletRequest request, Model model) {
		try {
			Boolean b = activityService.canApply(code);
			return new Result(Result.SUCCESS, b);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}

	}

	/**
	 * 报名
	 * 
	 * @param production
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/apply")
	public @ResponseBody Result apply(ProductionVo production, HttpServletRequest request, Model model) {
		try {
			Production p = activityService.apply(production);
			return new Result(Result.SUCCESS, p);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * 取消报名
	 * 
	 * @param production
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cancelApply")
	public @ResponseBody Result cancelApply(ProductionVo production, HttpServletRequest request, Model model) {
		try {
			activityService.cancelApply(production);
			return new Result(Result.SUCCESS);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}

	}

	/**
	 * 更新报名
	 * 
	 * @param production
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateApply")
	public @ResponseBody Result updateApply(ProductionVo production, HttpServletRequest request, Model model) {
		try {
			Production p = activityService.updateApply(production);
			return new Result(Result.SUCCESS, p);
		} catch (Exception e) {
			return new Result(Result.FAIL, e.getMessage());
		}
	}

}
