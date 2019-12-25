package com.xiaokang.login_MP.service.impl;

import com.xiaokang.login_MP.config.UUIDUtils;
import com.xiaokang.login_MP.bean.Msg;
import com.xiaokang.login_MP.bean.User;
import com.xiaokang.login_MP.dao.UserMapper;
import com.xiaokang.login_MP.service.UserService;
import com.xiaokang.login_MP.util.MD5Util;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	// jackson

	// 用户登录验证
	@Override
	@Transactional
	public Msg getlogin(User user, HttpServletRequest request, HttpServletResponse response) {

		user = userMapper.selectOne(new QueryWrapper<User>().eq("u_name", user.getuName()).eq("u_pwd",
				MD5Util.MD5EncodeUtf8(user.getuPwd())));
		
		String userJoin = JSON.toJSONString(user);
		if (user != null) {
			String uuid = UUIDUtils.getUUID();
			Cookie cookie = new Cookie("userCookie", uuid);

			response.addCookie(cookie);
			// 这种方法是把用户存在一个叫RedisUserKey的集合里面
			/*
			 * BoundHashOperations<String, Object, Object> boundHashOps =
			 * stringRedisTemplate.boundHashOps("RedisUserKey"); boundHashOps.put(uuid,
			 * userJoin);
			 */

			stringRedisTemplate.opsForValue().set("user" + "/" + uuid, userJoin, 3, TimeUnit.DAYS);
			

			return Msg.ok("");
		} else {

			return Msg.failure("用户名或密码错误");

		}
	}

	// 插入用户注册数据
	@Override
	@Transactional
	public Msg insert(User user) {

		try {
			User users = userMapper.selectOne(new QueryWrapper<User>().eq("u_name", user.getuName()));
			if (users != null) {
				return Msg.failure("用户名已存在");
			} else {
				String uPwd = user.getuPwd();
				user.setuPwd(MD5Util.MD5EncodeUtf8(uPwd));
				userMapper.insert(user);
				return Msg.ok("用户注册成功");
			}
		} catch (Exception e) {

			e.printStackTrace();
			return Msg.failure("注册失败，服务器忙，请稍后重试");
		}

	}

	// 获取数据库记录条数
	@Override
	@Transactional
	public int selectCount() {

		return userMapper.selectCount(null);
	}

	// 获取数据库全部记录
	@Override
	@Transactional
	public List<User> selectAll(Page<User> userPage) {

		return userMapper.selectList(null);
	}

	@Override
	@Transactional
	public Msg updatepwd(Long id, String uPwd, String newPassword, String okPassword) {

		User users = userMapper.selectOne(new QueryWrapper<User>().eq("id", id));
		if (!MD5Util.MD5EncodeUtf8(uPwd).equals(users.getuPwd())) {
			return Msg.failure("原密码错误");
		} else {
			if (!newPassword.equals(okPassword)) {
				return Msg.failure("新密码不一致");
			} else {
				User user = new User();
				user.setId(id);
				user.setuPwd(MD5Util.MD5EncodeUtf8(newPassword));
				try {
					userMapper.updateById(user);
					return Msg.ok("修改成功");
				} catch (Exception e) {
					
					e.printStackTrace();
					return Msg.failure("修改失败，请稍后重试");
				}

			}
		}

	}

	@Override
	@Transactional
	public Msg updateuser(User user) {

		try {
			userMapper.updateById(user);
			return Msg.ok("修改成功");
		} catch (Exception e) {

			e.printStackTrace();
			return Msg.failure("服务器繁忙，请稍后重试");
		}

	}

	@Override
	@Transactional
	public Msg deleteuser(Long id) {

		try {
			userMapper.deleteById(id);
			return Msg.ok("删除成功");
		} catch (Exception e) {

			e.printStackTrace();
			return Msg.failure("服务器繁忙，请稍后重试");
		}

	}

	@Override
	@Transactional
	public Msg restuser(User user) {

		try {
			String uPwd = user.getuPwd();
			user.setuPwd(MD5Util.MD5EncodeUtf8(uPwd));
			userMapper.updateById(user);
			return Msg.ok("重置成功");
		} catch (Exception e) {

			e.printStackTrace();
			return Msg.failure("服务器繁忙，请稍后重试");
		}

	}

}
