package com.xiaokang.login_MP.controllor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaokang.login_MP.bean.CSRelation;

import com.xiaokang.login_MP.bean.Msg;
import com.xiaokang.login_MP.bean.Student;

import com.xiaokang.login_MP.service.CSRelationService;
import com.xiaokang.login_MP.service.StudentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

/**
 * <p>
 * InnoDB free: 6144 kB 前端控制器
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-09
 */
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CSRelationService csRelationService;

	// 获取学生信息列表
	@RequestMapping("/getstuList/")
	@ResponseBody
	public Object getList(HttpServletRequest request) {
		Integer page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));
		Integer limit = Integer.valueOf(Integer.parseInt(request.getParameter("limit")));

		if (request.getParameter("stuNum") != null && request.getParameter("stuNum") != "") {

			IPage<Student> pages = new Page<>(page, limit);

			Integer stuNum = Integer.valueOf(Integer.parseInt(request.getParameter("stuNum")));

			List<Student> student = studentService.page(pages, new QueryWrapper<Student>().eq("stu_num", stuNum))
					.getRecords();

			int count = student.size();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "ok");
			map.put("count", count);
			map.put("data", student);
			return map;
		} else {

			IPage<Student> pages = new Page<>(page, limit);
			List<Student> student = studentService.page(pages, null).getRecords();
			int count = studentService.selectCount();
			// data
			// count
			// msg
			// code 0
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "ok");
			map.put("count", count);
			map.put("data", student);
			return map;
		}

	}

	// 添加学生
	@RequestMapping(value = "/addstu", produces = "application/html;charset=utf-8")
	@ResponseBody
	public String addstu(@RequestBody @Valid Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return bindingResult.getFieldError().getDefaultMessage();
		} else {
			return studentService.addstu(student);
		}

	}

	@RequestMapping(value = "/updatestu/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Msg updatestu(@RequestBody @Valid Student student, BindingResult bindingResult, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			return Msg.failure(bindingResult.getFieldError().getDefaultMessage());
		} else {
			return studentService.updatestu(student);
		}

	}

	@RequestMapping(value = "/deletestu/{ids}", method = RequestMethod.DELETE, produces = "application/html;charset=utf-8")
	@ResponseBody
	public String deletestu(@PathVariable("ids") String ids) {
		if (ids.contains(",")) {
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split(",");
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			return studentService.deletebath(del_ids);
		} else {
			Integer id = Integer.parseInt(ids);
			return studentService.deletestu(id);
		}

	}

	@RequestMapping("getStudentListByCourse/{type}/{csId}")
	@ResponseBody
	public Msg getStudentListByCourse(@PathVariable Integer type, @PathVariable Integer csId,
			HttpServletRequest request) {

		Integer page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));
		Integer limit = Integer.valueOf(Integer.parseInt(request.getParameter("limit")));
		if (type == 1) { // 查看没有选择该课程的学生列表

			IPage<Student> pages = new Page<>(page, limit);
			// List<Student> student = studentService.page(pages,null).getRecords();
			IPage<Student> student = studentService.getStudentNotByCourseId(pages, csId);
			List<Student> studentsize = studentService.getStudentNotByCourseId(csId);

			return Msg.okCountData(studentsize.size(), student.getRecords());

		} else {// 查看选择该课程的学生列表

			IPage<Student> pages = new Page<>(page, limit);
			// List<Student> student = studentService.page(pages,null).getRecords();
			IPage<Student> student = studentService.getStudentByCourseId(pages, csId);
			List<Student> studentsize = studentService.getStudentByCourseId(csId);

			return Msg.okCountData(studentsize.size(), student.getRecords());
		}
	}

	@RequestMapping("addStudentToCourse/{ids}/{csId}")
	@ResponseBody
	public Msg addStudentToCourse(@PathVariable String ids, @PathVariable Integer csId) {

		try {
			String[] idArr = ids.split(",");
			for (String sId : idArr) {
				CSRelation csr = new CSRelation();
				csr.setCsId(csId);
				csr.setId(Integer.parseInt(sId));
				csRelationService.addOne(csr);
			}
			return Msg.ok("插入成功");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Msg.failure("服务器忙，请稍后重试");
		}

	}

	@RequestMapping("delStudentToCourse/{ids}/{csId}")
	@ResponseBody
	public Msg delStudentToCourse(@PathVariable String ids, @PathVariable Integer csId) {

		try {
			String[] idArr = ids.split(",");
			for (String sId : idArr) {
				CSRelation csr = new CSRelation();
				csr.setCsId(csId);
				csr.setId(Integer.parseInt(sId));
				csRelationService.delBySIdAndCSId(csr);
			}
			return Msg.ok("删除成功");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Msg.failure("服务器忙，请稍后重试");
		}

	}

}
