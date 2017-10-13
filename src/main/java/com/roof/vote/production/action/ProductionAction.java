package com.roof.vote.production.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.roof.vote.common.ProductionStatusEnum;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import com.roof.vote.production.entity.Production;
import com.roof.vote.production.entity.ProductionVo;
import com.roof.vote.production.service.api.IProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("vote/productionAction")
public class ProductionAction {
	private IProductionService productionService;
	private IDictionaryService dictionaryService;

	// 加载页面的通用数据
	private void loadCommon(Model model){
		List<Dictionary> dicList =  dictionaryService.findByType("TEST");
		model.addAttribute("dicList", dicList);
	}

	@RequestMapping("/index")
	public String index() {
		return "/selin/production/production_index.jsp";
	}

	/*
	@RequestMapping("/list")
	public String list(Production production, HttpServletRequest request, Model model) {
		Page page = PageUtils.createPage(request);
		page = productionService.page(page, production);
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		this.loadCommon(model);
		return "/selin/production/production_list.jsp";
	}
	*/

    @RequestMapping("/list")
    public @ResponseBody Result list(Production production, HttpServletRequest request, Model model) {
    Page page = PageUtils.createPage(request);
    page = productionService.page_(page, production);
    return new Result(Result.SUCCESS,page);
	}

	@RequestMapping("/base")
	public @ResponseBody Result base(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		map.put("status", ProductionStatusEnum.getProductionStatusEnums());
		return new Result(Result.SUCCESS,map);
	}
	
	
	@RequestMapping("/create_page")
	public String create_page(Model model) {
		Production production = new Production();
		model.addAttribute("production", production);
		this.loadCommon(model);
		return "/selin/production/production_create.jsp";
	}
	
	@RequestMapping("/update_page")
	public String update_page(Production production, Model model) {
		production = productionService.load(production);
		model.addAttribute("production", production);
		this.loadCommon(model);
		return "/selin/production/production_update.jsp";
	}

	@RequestMapping("/detail_page")
	public String detail_page(Production production, Model model) {
		production = productionService.load(production);
		model.addAttribute("production", production);
		this.loadCommon(model);
		return "/selin/production/production_detail.jsp";
	}

	@RequestMapping("/create")
	public @ResponseBody Result create(Production production) {
		if (production != null) {
			productionService.save(production);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/update")
	public @ResponseBody Result update(Production production) {
		if (production != null) {
			productionService.updateIgnoreNull(production);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Result delete(Production production) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		productionService.delete(production);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setProductionService(
			@Qualifier("productionService") IProductionService productionService) {
		this.productionService = productionService;
	}

	@Autowired(required = true)
	public void setDictionaryService(@Qualifier("dictionaryService") IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
}
