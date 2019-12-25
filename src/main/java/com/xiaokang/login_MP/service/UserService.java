package com.xiaokang.login_MP.service;

import com.xiaokang.login_MP.bean.Msg;
import com.xiaokang.login_MP.bean.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-02
 */
public interface UserService extends IService<User> {

	/**
	 * 获取登陆信息
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	Msg getlogin(User user, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 插入用户
	 * 
	 * @param user
	 * @return
	 */
	Msg insert(User user);

	/**
	 * 查询数据库user表条数
	 * 
	 * @return
	 */
	int selectCount();

	/**
	 * 获取用户列表并分页
	 */
	List<User> selectAll(Page<User> userPage);

	/**
	 * 修改密码，
	 * 
	 * @param id
	 * @param uPwd        要修改的密码
	 * @param newPassword 新密码
	 * @param okPassword  确认新密码
	 * @return
	 */
	Msg updatepwd(Long id, String uPwd, String newPassword, String okPassword);

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	Msg updateuser(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	Msg deleteuser(Long id);

	/**
	 * 重置密码
	 * 
	 * @param user
	 * @return
	 */
	Msg restuser(User user);

}
