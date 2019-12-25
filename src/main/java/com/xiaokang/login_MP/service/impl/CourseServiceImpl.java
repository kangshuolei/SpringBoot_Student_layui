package com.xiaokang.login_MP.service.impl;

import com.xiaokang.login_MP.bean.Course;
import com.xiaokang.login_MP.bean.Msg;

import com.xiaokang.login_MP.dao.CourseMapper;
import com.xiaokang.login_MP.service.CourseService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

	@Autowired
	private CourseMapper courseMapper;

	@Override
	@Transactional
	public int selectCount() {
		// TODO Auto-generated method stub
		return courseMapper.selectCount(null);
	}

	@Override
	@Transactional
	public List<Course> page(Integer page, Object object) {
		// TODO Auto-generated method stub
		return courseMapper.selectList(null);
	}

	@Override
	@Transactional
	public String addcus(Course course) {
		// TODO Auto-generated method stub
		try {
			courseMapper.insert(course);
			return "插入成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "服务器繁忙，插入失败，请稍后再试";
		}
	}

	@Override
	@Transactional
	public Msg updatestu(Course course) {
		// TODO Auto-generated method stub
		try {
			courseMapper.updateById(course);
			return Msg.ok("更新成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Msg.failure("服务器繁忙，更新失败，请稍后再试");
		}
	}

	@Override
	@Transactional
	public String deletebath(List<Integer> del_ids) {
		// TODO Auto-generated method stub
		try {
			courseMapper.deleteBatchIds(del_ids);
			return "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败，请稍后再试";
		}
	}

	@Override
	@Transactional
	public String deletestu(Integer id) {
		// TODO Auto-generated method stub
		try {
			courseMapper.deleteById(id);
			return "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败，请稍后再试";
		}
	}

}
