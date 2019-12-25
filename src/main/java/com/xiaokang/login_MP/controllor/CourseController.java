package com.xiaokang.login_MP.controllor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaokang.login_MP.bean.Course;
import com.xiaokang.login_MP.bean.Msg;
import com.xiaokang.login_MP.service.CourseService;

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
 * 前端控制器
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-13
 */
@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;

	// 获取课程信息列表
	@RequestMapping("/getcourseList/")
	@ResponseBody
	public Object getList(HttpServletRequest request) {
		Integer page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));
		Integer limit = Integer.valueOf(Integer.parseInt(request.getParameter("limit")));

		if (request.getParameter("csName") != null && request.getParameter("csName") != "") {

			IPage<Course> pages = new Page<>(page, limit);

			// Integer csName
			// =Integer.valueOf(Integer.parseInt(request.getParameter("csName")));
			String csName = request.getParameter("csName");
			
			List<Course> course = courseService.page(pages, new QueryWrapper<Course>().eq("cs_name", csName))
					.getRecords();

			int count = course.size();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "ok");
			map.put("count", count);
			map.put("data", course);
			return map;
		} else {

			IPage<Course> pages = new Page<>(page, limit);
			List<Course> course = courseService.page(pages, null).getRecords();

			int count = courseService.selectCount();
			// data
			// count
			// msg
			// code 0
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "ok");
			map.put("count", count);
			map.put("data", course);
			return map;
		}

	}

	// 添加课程
	@RequestMapping(value = "/addcus", produces = "application/html;charset=utf-8")
	@ResponseBody
	public String addcus(@RequestBody @Valid Course course, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return bindingResult.getFieldError().getDefaultMessage();
		} else {
			return courseService.addcus(course);
		}

	}

	@RequestMapping(value = "/updatecsu/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Msg updatestu(@RequestBody @Valid Course course, BindingResult bindingResult, HttpServletRequest request) {

		


		if (bindingResult.hasErrors()) {
			return Msg.failure(bindingResult.getFieldError().getDefaultMessage());
		} else {
			return courseService.updatestu(course);
		}

	}

	@RequestMapping(value = "/deletecurs/{ids}", method = RequestMethod.DELETE, produces = "application/html;charset=utf-8")
	@ResponseBody
	public String deletestu(@PathVariable("ids") String ids) {
		if (ids.contains(",")) {
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split(",");
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			return courseService.deletebath(del_ids);
		} else {
			Integer id = Integer.parseInt(ids);
			return courseService.deletestu(id);
		}

	}

	@RequestMapping("toGetStudentsByCoursePage/{type}/{csId}")
	public ModelAndView toGetStudentsByCoursePage(@PathVariable Integer type, @PathVariable Integer csId) {
		

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("csId", csId);
		mv.addObject("type", type);
		return mv;
	}

}
