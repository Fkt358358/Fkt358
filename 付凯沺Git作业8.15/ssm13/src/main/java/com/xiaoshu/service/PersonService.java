package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.BankMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Bank;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonExample;
import com.xiaoshu.entity.PersonVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class PersonService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	private PersonMapper pm;
	
	

	// 新增
	public void addPerson(Person t) throws Exception {
		
		pm.insert(t);
	};

	// 修改
	public void updatePerson(Person t) throws Exception {
		pm.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deletePerson(Integer id) throws Exception {
		pm.deleteByPrimaryKey(id);
	};


	// 通过用户名判断是否存在，（新增时不能重名）
	public Person existPersonWithPersonName(String userName) throws Exception {
		PersonExample example = new PersonExample();
		com.xiaoshu.entity.PersonExample.Criteria criteria = example.createCriteria();
		criteria.andPNameEqualTo(userName);
		List<Person> userList = pm.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};


	public PageInfo<PersonVo> findPersonPage(PersonVo pv, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<PersonVo> userList = pm.findPerson(pv);
		PageInfo<PersonVo> pageInfo = new PageInfo<PersonVo>(userList);
		return pageInfo;
	}

	@Autowired
	private BankMapper bm;
	
	public List<Bank> findAllBank() {
		// TODO Auto-generated method stub
		return bm.selectAll();
	}
	@Autowired
	JedisPool jedisPool;
	public void addBank(Bank bank) {
		// TODO Auto-generated method stub
		bm.insert(bank);
		Bank bank2 = bm.selectByPrimaryKey(bank);

		Jedis jedis = jedisPool.getResource();
		jedis.hset("bank",bank2.getbId()+"",bank2.getbName());
	}


}
