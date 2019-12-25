package com.xiaokang.login_MP.service;

import com.xiaokang.login_MP.bean.CSRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-14
 */
public interface CSRelationService extends IService<CSRelation> {

	/**
	 * 增加学生和课程的关联
	 * 
	 * @param csr
	 */
	void addOne(CSRelation csr);

	/**
	 * 删除学生和课程的关联
	 * 
	 * @param csr
	 */
	void delBySIdAndCSId(CSRelation csr);

}
