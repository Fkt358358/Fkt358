package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

@Service
public class StuService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	private StuMapper sm;
	// 新增
	public void addStu(Stu t) throws Exception {
		sm.insert(t);
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
	public Stu existStuWithStuName(String userName) throws Exception {
		StuExample example = new StuExample();
		com.xiaoshu.entity.StuExample.Criteria criteria = example.createCriteria();
		criteria.andSdnameEqualTo(userName);
		List<Stu> userList = sm.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};


	public PageInfo<StuVo> findStuPage(StuVo sv, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StuVo> userList = sm.findAllStu(sv);
		PageInfo<StuVo> pageInfo = new PageInfo<StuVo>(userList);
		return pageInfo;
	}

	@Autowired
	private MajorMapper mm;
	public List<Major> findAll() {
		// TODO Auto-generated method stub
		return mm.selectAll();
	}


	public List<StuVo> findLog(StuVo sv) {
		// TODO Auto-generated method stub
		return sm.findAllStu(sv);
	}

	public List<StuVo> findEchatrs() {
		// TODO Auto-generated method stub
		return sm.findEcharts();
	}

	


}
