package com.xiaoshu.service;

import java.util.List;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.ContentCategoryMapper;
import com.xiaoshu.dao.ContentMapper;
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.ContentCategory;
import com.xiaoshu.entity.ContentExample;
import com.xiaoshu.entity.ContentVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
public class ContentService {

	@Autowired
	JedisPool jedisPool;
	@Autowired
	private ContentMapper cm;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	private Destination queueTextDestination; 
	

	// 新增
	public void addContent(Content t) throws Exception {
		cm.insert(t);
		Content one = cm.selectOne(t);
		Jedis jedis = jedisPool.getResource();
		jedis.hset("conten",one.getContentid()+"", one.toString());
		
		jmsTemplate.convertAndSend(queueTextDestination, JSONObject.toJSONString(one));
	};

	// 修改
	public void updateContent(Content t) throws Exception {
		cm.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteContent(Integer id) throws Exception {
		cm.deleteByPrimaryKey(id);
	};


	// 通过用户名判断是否存在，（新增时不能重名）
	public Content existContentWithContentName(String userName) throws Exception {
		ContentExample example = new ContentExample();
		com.xiaoshu.entity.ContentExample.Criteria criteria = example.createCriteria();
		criteria.andContenttitleEqualTo(userName);
		List<Content> userList = cm.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};


	public PageInfo<ContentVo> findContentPage(ContentVo cv, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ContentVo> userList =cm.findAllContent(cv);
		PageInfo<ContentVo> pageInfo = new PageInfo<ContentVo>(userList);
		return pageInfo;
	}

	@Autowired
	private ContentCategoryMapper ccm;
	public List<ContentCategory> findAll() {
		// TODO Auto-generated method stub
		return ccm.findAll();
	}

	public List<ContentVo> findLog(ContentVo cv) {
		// TODO Auto-generated method stub
		return cm.findAllContent(cv);
	}
	
	public List<ContentVo> findEcharts() {
		// TODO Auto-generated method stub
		return cm.findEcharts();
	}


}
