package com.roof.vote.production.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.roof.vote.common.ActivityStatusEnum;
import com.roof.vote.common.ProductionStatusEnum;
import com.roof.vote.production.dao.api.IProductionDao;
import com.roof.vote.production.entity.Production;
import com.roof.vote.production.entity.ProductionVo;
import com.roof.vote.production.service.api.IProductionService;
import com.roof.vote.vote.entity.Vote;

@Service
public class ProductionService implements IProductionService {
	private IProductionDao productionDao;

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void processPro(Long id, Boolean processval) {
		Production p = new Production(id);
		if (processval) {
			p.setStatus(ProductionStatusEnum.processed.getCode());
		} else {
			p.setStatus(ProductionStatusEnum.managecancel.getCode());
		}
		this.updateIgnoreNull(p);
		ProductionVo pvo = this.load(new Production(id));
		String key = Vote.createVoteZsetKey(pvo.getActivity_code());
		BoundZSetOperations operations = redisTemplate.boundZSetOps(key);
		operations.add(Vote.createVoteZsetValueKey(pvo.getVote_code()), 0);
	}

	public ProductionVo getPro(Long id) {
		ProductionVo pvo = this.load(new Production(id));

		String key = Vote.createVoteZsetKey(pvo.getActivity_code());
		BoundZSetOperations operations = redisTemplate.boundZSetOps(key);
		Long index = operations.reverseRank(Vote.createVoteZsetValueKey(pvo.getVote_code()));
		pvo.setIndex(index + 1);
		System.out.println(index);
		if (index.longValue() == 0) {// 第一名直接返回
			pvo.setMarginNum(0D);
			return pvo;
		}
		Set<TypedTuple<String>> zset = operations.reverseRangeByScoreWithScores(index - 1, index);
		Set<TypedTuple<String>> zset1 = operations.reverseRangeByScoreWithScores(index, index + 1);
		for (TypedTuple<String> typedTuple : zset) {
			System.out.println(JSON.toJSONString(typedTuple));
			// if
			// (!typedTuple.getValue().equals(Vote.createVoteZsetValueKey(pvo.getVote_code())))
			// {
			// pvo.setPerNum(typedTuple.getScore());
			// }
		}

		for (TypedTuple<String> typedTuple : zset1) {
			System.out.println(JSON.toJSONString(typedTuple));
		}
		// 票差额
		if (pvo.getPerNum() != null) {
			pvo.setMarginNum(0D);
		} else {
			pvo.setMarginNum(new BigDecimal(pvo.getPerNum()).subtract(new BigDecimal(pvo.getNum())).doubleValue());
		}
		return pvo;

	}

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
			String key = Vote.createVoteZsetKey(productionVo.getActivity_code());
			BoundZSetOperations operations = redisTemplate.boundZSetOps(key);
			Double d = operations.score(Vote.createVoteZsetValueKey(productionVo.getVote_code()));
			productionVo.setNum(d != null ? d : 0D);
		}
		page.setDataList(list);
		return page;
	}

	@Autowired
	public void setIProductionDao(@Qualifier("productionDao") IProductionDao productionDao) {
		this.productionDao = productionDao;
	}

}
