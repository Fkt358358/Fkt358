package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.DeptMapper;
import com.xiaoshu.dao.EmpMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Dept;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.entity.EmpExample;
import com.xiaoshu.entity.EmpVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class EmpService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	private EmpMapper em;
	// 新增
	public void addEmp(Emp t) throws Exception {
		em.insert(t);
	};

	// 修改
	public void updateEmp(Emp t) throws Exception {
		em.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteEmp(Integer id) throws Exception {
		em.deleteByPrimaryKey(id);
	};


	// 通过用户名判断是否存在，（新增时不能重名）
	public Emp existUserWithUserName(String userName) throws Exception {
		EmpExample example = new EmpExample();
		com.xiaoshu.entity.EmpExample.Criteria criteria = example.createCriteria();
		criteria.andENameEqualTo(userName);
		List<Emp> userList = em.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};


	public PageInfo<EmpVo> findEmpPage(EmpVo emp, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<EmpVo> userList = em.findAll(emp);
		PageInfo<EmpVo> pageInfo = new PageInfo<EmpVo>(userList);
		return pageInfo;
	}

	@Autowired
	private DeptMapper dm;
	public List<Dept> findDept() {
		// TODO Auto-generated method stub
		return dm.selectAll();
	}


}
