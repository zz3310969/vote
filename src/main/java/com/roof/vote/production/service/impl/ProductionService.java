package com.roof.vote.production.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;

import com.roof.vote.activity.service.impl.ActivityService;
import com.roof.vote.common.ActivityStatusEnum;
import com.roof.vote.common.ProductionStatusEnum;
import com.roof.vote.production.dao.api.IProductionDao;
import com.roof.vote.production.entity.Production;
import com.roof.vote.production.entity.ProductionVo;
import com.roof.vote.production.service.api.IProductionService;
import com.roof.vote.vote.entity.Vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductionService implements IProductionService {
	private IProductionDao productionDao;

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	public List<ProductionVo> selectProductByuserid(Long user_id) {
		List<ProductionVo> list = (List<ProductionVo>) productionDao.selectForList("selectProductByuserid", user_id);
		for (ProductionVo productionVo : list) {
			productionVo.setProStatusName(ProductionStatusEnum.getStatusEnumName(productionVo.getStatus()));
			productionVo.setActStatusName(ActivityStatusEnum.getStatusEnumName(productionVo.getActStatus()));
		}
		return list;
	}

	public Serializable save(Production production) {
		return productionDao.save(production);
	}

	public void delete(Production production) {
		productionDao.delete(production);
	}

	public void deleteByExample(Production production) {
		productionDao.deleteByExample(production);
	}

	public void update(Production production) {
		productionDao.update(production);
	}

	public void updateIgnoreNull(Production production) {
		productionDao.updateIgnoreNull(production);
	}

	public void updateByExample(Production production) {
		productionDao.update("updateByExampleProduction", production);
	}

	public ProductionVo load(Production production) {
		ProductionVo pvo = (ProductionVo) productionDao.reload(production);
		pvo.setActStatusName(ActivityStatusEnum.getStatusEnumName(pvo.getActStatus()));
		pvo.setProStatusName(ProductionStatusEnum.getStatusEnumName(pvo.getStatus()));
		return pvo;
	}

	public ProductionVo selectForObject(Production production) {
		return (ProductionVo) productionDao.selectForObject("selectProduction", production);
	}

	public List<ProductionVo> selectForList(Production production) {
		return (List<ProductionVo>) productionDao.selectForList("selectProduction", production);
	}

	public Page page(Page page, Production production) {
		productionDao.page(page, production);
		List<ProductionVo> list = (List<ProductionVo>) page.getDataList();
		for (ProductionVo productionVo : list) {
			String key = Vote.createProductVoteKey(productionVo.getActivity_code(), productionVo.getVote_code());
			BoundValueOperations<String, Long> operations = redisTemplate.boundValueOps(key);
			productionVo.setNum(operations.get());
		}
		page.setDataList(list);
		return page;
	}

	@Autowired
	public void setIProductionDao(@Qualifier("productionDao") IProductionDao productionDao) {
		this.productionDao = productionDao;
	}

}
