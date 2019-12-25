package com.xiaokang.login_MP.controllor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import com.xiaokang.login_MP.bean.Msg;
import com.xiaokang.login_MP.bean.User;
import com.xiaokang.login_MP.group.Insert;
import com.xiaokang.login_MP.group.Update;
import com.xiaokang.login_MP.service.UserService;

import io.swagger.annotations.ApiOperation;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * InnoDB free: 6144 kB 前端控制器
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-02
 */
@Controller
public class UserController {

	@Autowired
	private UserService userSevice;

	// Redis
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	// 验证码
	@Autowired
	private DefaultKaptcha defaultKaptcha;

	@ApiOperation(value = "登录页面", notes = "index登录页面")
	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	@RequestMapping("pt_index")
	public String ptindex() {
		return "pt_index";
	}

	@ApiOperation(value = "登录的页面", notes = "login_success成功页面")
	@RequestMapping("login_success")
	public String login_success() {
		return "index";
	}

	@ApiOperation(value = "注册的页面", notes = "register的注册页面")
	@RequestMapping("register")
	public String register() {
		return "register";
	}

	// 用户登录信息
	@ApiOperation(value = "返回code和msg", notes = "获取登录信息的状态")
	@PostMapping("/login")
	@ResponseBody
	public Msg login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		String tryCode = user.tryCode;

		String rightCode = (String) request.getSession().getAttribute("rightCode");
		// System.out.println("rightCode:" + rightCode + " ———— tryCode:" + tryCode);

		if (rightCode.equals(tryCode)) {
			
			return userSevice.getlogin(user, request, response);

		} else {
			return Msg.failure("验证码错误");

		}

	}

	// 用户登录验证
	@ApiOperation(value = "跳转成功或失败页面", notes = "判断登录转发")
	@RequestMapping(value = "/toMainPage")
	public ModelAndView toMainPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv;
		String struuid = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase("userCookie")) {
				struuid = cookie.getValue();
			}
		}

		if (struuid == null) {
			System.out.println("验证不通过");

			System.out.println("UUID不存在");

		}

		// Redis里保存当前cookie里对应的信息 key=UUIDUtils
		/*
		 * BoundHashOperations<String, Object, Object> boundHashOps
		 * =stringRedisTemplate.boundHashOps("RedisUserKey"); String userJson =
		 * (String)boundHashOps.get(struuid);
		 */

		String userJson;
		try {
			userJson = stringRedisTemplate.opsForValue().get("user" + "/" + struuid);
			JSONObject pa = JSONObject.parseObject(userJson);
			if ("管理员".equals(pa.getString("uRank")) && userJson != null) {
				mv = new ModelAndView("index");
				mv.addObject("user", pa.getString("uName"));
				mv.addObject("id", pa.getString("id"));
				mv.addObject("uPwd", pa.getString("uPwd"));
				

			} else if ("普通用户".equals(pa.getString("uRank")) && userJson != null) {
				mv = new ModelAndView("pt_index");
				mv.addObject("user", pa.getString("uName"));
				mv.addObject("id", pa.getString("id"));
				mv.addObject("uPwd", pa.getString("uPwd"));

			} else {
				mv = new ModelAndView("redirect:login");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			mv = new ModelAndView("redirect:login");
		}
		return mv;
	}

	// 用户信息注册
	@ApiOperation(value = "获取注册信息", notes = "获取注册信息")
	@PostMapping(value = "/registerinfo")
	@ResponseBody
	public Msg register(@RequestBody @Validated({ Insert.class }) User user, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {

		if (bindingResult.hasErrors()) {
			return Msg.failure(bindingResult.getFieldError().getDefaultMessage());
		} else {

			String tryCode = user.tryCode;

			String rightCode = (String) request.getSession().getAttribute("rightCode");
			if (rightCode.equals(tryCode)) {
				try {
					return userSevice.insert(user);
				} catch (Exception e) {
					e.printStackTrace();
					return Msg.failure("注册失败，服务器忙，请稍后重试");
				}
			} else {
				return Msg.failure("验证码错误");
			}
		}

	}

	// 获取图形验证码
	@ApiOperation(value = "获取图形验证码", notes = "获取图形验证码")
	@RequestMapping("/defaultKaptcha")
	public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// 生产验证码字符串并保存到session中
			String createText = defaultKaptcha.createText();
			httpServletRequest.getSession().setAttribute("rightCode", createText);
			// 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = defaultKaptcha.createImage(createText);
			ImageIO.write(challenge, "jpg", jpegOutputStream);
		} catch (IllegalArgumentException e) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		httpServletResponse.setHeader("Cache-Control", "no-store");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}

	// 向前端发送全部记录数据
	@ApiOperation(value = "向前端发送全部记录数据", notes = "向前端发送全部记录数据")
	@RequestMapping(value = "/findAll")
	@ResponseBody
	public Object findAll(HttpServletRequest request) {

		Integer page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));
		Integer limit = Integer.valueOf(Integer.parseInt(request.getParameter("limit")));
		IPage<User> pages = new Page<>(page, limit);

		List<User> user = userSevice.page(pages, null).getRecords();

		int count = userSevice.selectCount();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "ok");
		map.put("count", count);
		map.put("data", user);
		return map;
	}

	@RequestMapping(value = "/updatepwd/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Msg updatepwd(@PathVariable("id") Long id, @RequestBody String str, HttpServletRequest request) {
		JSONObject pa = JSONObject.parseObject(str);

		String uPwd = pa.getString("uPwd");
		String newPassword = pa.getString("newPassword");
		String okPassword = pa.getString("okPassword");

		return userSevice.updatepwd(id, uPwd, newPassword, okPassword);

	}

	@RequestMapping("/getuserList/")
	@ResponseBody
	public Object getuserList(HttpServletRequest request) {
		Integer page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));
		Integer limit = Integer.valueOf(Integer.parseInt(request.getParameter("limit")));
		IPage<User> pages = new Page<>(page, limit);
		List<User> user = userSevice.page(pages).getRecords();
		int count = userSevice.selectCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "ok");
		map.put("count", count);
		map.put("data", user);
		return map;

	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.PUT)
	@ResponseBody
	public Msg updateuser(@RequestBody @Validated({ Update.class }) User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return Msg.failure(bindingResult.getFieldError().getDefaultMessage());
		} else {

			return userSevice.updateuser(user);
		}

	}

	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteuser(@PathVariable("id") Long id) {

		return userSevice.deleteuser(id);

	}

	@RequestMapping(value = "/restuser", method = RequestMethod.PUT)
	@ResponseBody
	public Msg restuser(@RequestBody User user) {

		return userSevice.restuser(user);

	}

}
