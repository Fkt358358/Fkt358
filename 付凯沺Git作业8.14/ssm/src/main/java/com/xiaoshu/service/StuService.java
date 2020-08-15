package com.xiaoshu.service;

import java.util.List;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.MajorMapper;
import com.xiaoshu.dao.StuMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Log;
import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Stu;
import com.xiaoshu.entity.StuExample;
import com.xiaoshu.entity.StuVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

import redis.clients.jedis.Jedis;

@Service
public class StuService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	private StuMapper sm;

	@Autowired
	private JmsTemplate jt;
	
	@Autowired
	private Destination queueTextDestination;

	// 新增
	public void addStu(Stu t) throws Exception {
		sm.insert(t);
		//redis
		Jedis jedis = new Jedis("127.0.0.1",6379);
		jedis.set(t.getsName(), t.getsSex());
		//MQ
		jt.convertAndSend(queueTextDestination, JSONObject.toJSONString(t));
	};

	// 修改
	public void updateStu(Stu t) throws Exception {
		sm.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteStu(Integer id) throws Exception {
		sm.deleteByPrimaryKey(id);
	};


	// 通过用户名判断是否存在，（新增时不能重名）
	public Stu existUserWithUserName(String userName) throws Exception {
		StuExample example = new StuExample();
		com.xiaoshu.entity.StuExample.Criteria criteria = example.createCriteria();
		criteria.andSNameEqualTo(userName);
		List<Stu> userList = sm.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};

	public PageInfo<StuVo> findStuPage(StuVo stu, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StuVo> userList = sm.findStuPage(stu);
		PageInfo<StuVo> pageInfo = new PageInfo<StuVo>(userList);
		return pageInfo;
	}

	@Autowired
	private MajorMapper mm;
	public List<Major> findAll() {
		// TODO Auto-generated method stub
		return mm.selectAll();
	}

	public List<StuVo> findLog(StuVo log) {
		// TODO Auto-generated method stub
		return sm.findStuPage(log);
	}


}
