package com.xiaokang.login_MP.dao;

import com.xiaokang.login_MP.bean.Student;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * InnoDB free: 6144 kB Mapper 接口
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-09
 */
public interface StudentMapper extends BaseMapper<Student> {

	/**
	 * 根据课程ID获得该课程关联的学生列表并分页
	 * 
	 * @param pages
	 * @param csId
	 * @return
	 */
	IPage<Student> getStudentByCourseId(IPage<Student> pages, @Param("csId") Integer csId);

	/**
	 * 根据课程ID获得该课程没有关联的学生列表并分页
	 * 
	 * @param pages
	 * @param csId
	 * @return
	 */
	IPage<Student> getStudentNotByCourseId(IPage<Student> pages, @Param("csId") Integer csId);

	/**
	 * 根据课程ID获得该课程没有关联的学生列表，用于获取分页需要的条数
	 * 
	 * @param csId
	 * @return
	 */
	List<Student> getStudentNotByCourseId(Integer csId);

	/**
	 * 根据课程ID获得该课程关联的学生列表，用于获取分页需要的条数
	 * 
	 * @param csId
	 * @return
	 */
	List<Student> getStudentByCourseId(Integer csId);
	
}
