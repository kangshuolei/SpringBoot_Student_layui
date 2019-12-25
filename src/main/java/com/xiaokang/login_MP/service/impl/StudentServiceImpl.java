package com.xiaokang.login_MP.service.impl;

import com.xiaokang.login_MP.bean.Msg;
import com.xiaokang.login_MP.bean.Student;
import com.xiaokang.login_MP.dao.StudentMapper;
import com.xiaokang.login_MP.service.StudentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-09
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	@Transactional
	public int selectCount() {
		// TODO Auto-generated method stub
		return studentMapper.selectCount(null);
	}

	@Override
	@Transactional
	public String addstu(Student student) {
		try {
			studentMapper.insert(student);
			return "插入成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "服务器繁忙，插入失败，请稍后再试";
		}

		

	}

	@Override
	@Transactional
	public Msg updatestu(Student student) {
		// TODO Auto-generated method stub
		try {
			studentMapper.updateById(student);
			return Msg.ok("更新成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Msg.failure("服务器繁忙，更新失败，请稍后再试");
		}

	}

	@Override
	@Transactional
	public String deletestu(Integer id) {
		// TODO Auto-generated method stub
		try {
			studentMapper.deleteById(id);
			return "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败，请稍后再试";
		}

	}

	@Override
	@Transactional
	public String deletebath(List<Integer> del_ids) {
		// TODO Auto-generated method stub
		try {
			studentMapper.deleteBatchIds(del_ids);
			return "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败，请稍后再试";
		}

	}

	@Override
	@Transactional
	public IPage<Student> getStudentNotByCourseId(IPage<Student> pages, Integer csId) {
		// TODO Auto-generated method stub
		return studentMapper.getStudentNotByCourseId(pages, csId);
	}

	@Override
	@Transactional
	public IPage<Student> getStudentByCourseId(IPage<Student> pages, Integer csId) {
		// TODO Auto-generated method stub
		return studentMapper.getStudentByCourseId(pages, csId);
	}

	@Override
	@Transactional
	public List<Student> getStudentNotByCourseId(Integer csId) {
		// TODO Auto-generated method stub
		return studentMapper.getStudentNotByCourseId(csId);
	}

	@Override
	@Transactional
	public List<Student> getStudentByCourseId(Integer csId) {
		// TODO Auto-generated method stub
		return studentMapper.getStudentByCourseId(csId);
	}

	

}
