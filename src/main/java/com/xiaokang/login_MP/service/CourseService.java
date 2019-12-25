package com.xiaokang.login_MP.service;

import com.xiaokang.login_MP.bean.Course;
import com.xiaokang.login_MP.bean.Msg;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-13
 */
public interface CourseService extends IService<Course> {

	/**
	 * 获取课程列表并按条件分页
	 * 
	 * @param page
	 * @param object
	 * @return
	 */
	List<Course> page(Integer page, Object object);

	/**
	 * 获取数据库course表的条数
	 * 
	 * @return
	 */
	int selectCount();

	/**
	 * 增加课程信息
	 * 
	 * @param course
	 * @return
	 */
	String addcus(Course course);

	/**
	 * 更新课程信息
	 * 
	 * @param course
	 * @return
	 */
	Msg updatestu(Course course);

	/**
	 * 根据多个ID批量删除课程
	 * 
	 * @param del_ids
	 * @return
	 */
	String deletebath(List<Integer> del_ids);

	/**
	 * 根据ID删除课程
	 * 
	 * @param id
	 * @return
	 */
	String deletestu(Integer id);

}
