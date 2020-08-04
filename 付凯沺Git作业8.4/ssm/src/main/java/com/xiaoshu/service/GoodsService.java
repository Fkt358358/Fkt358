package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.GoodsMapper;
import com.xiaoshu.dao.TypeMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Goods;
import com.xiaoshu.entity.GoodsExample;
import com.xiaoshu.entity.GoodsVo;
import com.xiaoshu.entity.Type;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class GoodsService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	private GoodsMapper gm;
	@Autowired
	private TypeMapper tm;
	
	// 查询所有
	public List<User> findUser(User t) throws Exception {
		return userMapper.select(t);
	};

	// 数量
	public int countUser(User t) throws Exception {
		return userMapper.selectCount(t);
	};

	// 通过ID查询
	public User findOneUser(Integer id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	};

	// 新增
	public void addGoods(Goods t) throws Exception {
		gm.insert(t);
	};

	// 修改
	public void updateGoods(Goods t) throws Exception {
		gm.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteGoods(Integer id) throws Exception {
		gm.deleteByPrimaryKey(id);
	};

	// 登录
	public User loginUser(User user) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPasswordEqualTo(user.getPassword()).andUsernameEqualTo(user.getUsername());
		List<User> userList = userMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};

	// 通过用户名判断是否存在，（新增时不能重名）
	public Goods existUserWithUserName(String userName) throws Exception {
		GoodsExample example = new GoodsExample();
		 com.xiaoshu.entity.GoodsExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(userName);
		List<Goods> userList = gm.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};

	// 通过角色判断是否存在
	public User existUserWithRoleId(Integer roleId) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(roleId);
		List<User> userList = userMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	}

	public PageInfo<GoodsVo> findUserPage(GoodsVo goodsVo, int pageNum, int pageSize, String ordername, String order) {
		PageHelper.startPage(pageNum, pageSize);
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"userid";
		order = StringUtil.isNotEmpty(order)?order:"desc";
		List<GoodsVo> userList = gm.findAll(goodsVo);
		PageInfo<GoodsVo> pageInfo = new PageInfo<GoodsVo>(userList);
		return pageInfo;
	}

	public List<Type> findType() {
		// TODO Auto-generated method stub
		return tm.selectAll();
	}



}
