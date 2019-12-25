package com.xiaokang.login_MP.service.impl;

import com.xiaokang.login_MP.bean.CSRelation;
import com.xiaokang.login_MP.dao.CSRelationMapper;
import com.xiaokang.login_MP.service.CSRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-14
 */
@Service
public class CSRelationServiceImpl extends ServiceImpl<CSRelationMapper, CSRelation> implements CSRelationService {

	@Autowired
	private CSRelationMapper csRelationMapper;

	@Override
	@Transactional
	public void addOne(CSRelation csr) {
		// TODO Auto-generated method stub
		csRelationMapper.insert(csr);
	}

	@Override
	@Transactional
	public void delBySIdAndCSId(CSRelation csr) {
		// TODO Auto-generated method stub
		csRelationMapper.delete(new QueryWrapper<CSRelation>().eq("id", csr.getId()).eq("cs_id", csr.getCsId()));
	}

}
