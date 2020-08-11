package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.GoodsMapper;
import com.xiaoshu.dao.GoodstypeMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Goods;
import com.xiaoshu.entity.GoodsExample;
import com.xiaoshu.entity.GoodsVo;
import com.xiaoshu.entity.Goodstype;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class GoodsService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	private GoodsMapper gm;

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


	// 通过用户名判断是否存在，（新增时不能重名）
	public Goods existGoodsWithGoodsName(String userName) throws Exception {
		GoodsExample example = new GoodsExample();
		 com.xiaoshu.entity.GoodsExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(userName);
		List<Goods> userList = gm.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};


	public PageInfo<GoodsVo> findGoodsPage(GoodsVo goods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<GoodsVo> userList = gm.findAllGoods(goods);
		PageInfo<GoodsVo> pageInfo = new PageInfo<GoodsVo>(userList);
		return pageInfo;
	}

	@Autowired
	private GoodstypeMapper gt;
	public List<Goodstype> findAll() {
		// TODO Auto-generated method stub
		return gt.selectAll();
	}


}
