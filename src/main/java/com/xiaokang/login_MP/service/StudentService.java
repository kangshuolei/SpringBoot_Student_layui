package com.xiaokang.login_MP.service;

import com.xiaokang.login_MP.bean.Msg;
import com.xiaokang.login_MP.bean.Student;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-09
 */
public interface StudentService extends IService<Student> {

	/**
	 * 查找数据库student表的条数
	 * 
	 * @return
	 */
	int selectCount();

	/**
	 * 添加学生
	 * 
	 * @param student
	 * @return
	 */
	String addstu(Student student);

	/**
	 * 更新学生信息
	 * 
	 * @param student
	 * @return
	 */
	Msg updatestu(Student student);

	/**
	 * 根据ID删除学生
	 * 
	 * @param id
	 * @return
	 */
	String deletestu(Integer id);

	/**
	 * 根据多个ID批量删除学生
	 * 
	 * @param del_ids
	 * @return
	 */
	String deletebath(List<Integer> del_ids);

	/**
	 * 根据课程ID获得该课程没有关联的学生列表并分页
	 * 
	 * @param pages
	 * @param csId
	 * @return
	 */
	IPage<Student> getStudentNotByCourseId(IPage<Student> pages, Integer csId);

	/**
	 * 根据课程ID获得该课程关联的学生列表并分页
	 * 
	 * @param pages
	 * @param csId
	 * @return
	 */
	IPage<Student> getStudentByCourseId(IPage<Student> pages, Integer csId);

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
