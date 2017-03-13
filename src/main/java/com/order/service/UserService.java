package com.order.service;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.order.dao.UserDAO;
import com.order.entity.User;
import com.order.util.CreateUUID;
import com.order.util.MD5;
import com.order.util.Mail;

public class UserService {
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void save(User user) throws Exception {
		user.setType("0");
		user.setState("1");
		String password = user.getPassword();
		user.setPassword(MD5.encoderByMd5(password));
		ActionContext ac = ActionContext.getContext();
		String code = (String) ac.getSession().get("checkCode");
		user.setCheckCode(code);
		this.userDAO.save(user);
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName) {
		List user = this.userDAO.findByUsername(userName);
		if (user.size() > 0) {
			return (User) user.get(0);
		}
		return null;
	}

	/**
	 * 根据用户id查找用户
	 */
	public User findByUserId(Long userId) {
		User user = this.userDAO.findById(userId);
		return user;
	}

	/**
	 * 返回所有用户
	 */
	public List<User> findAllUser() {
		List<User> list = this.userDAO.findAll();
		return list;
	}

	/**
	 * 修改用户信息
	 * @param user
	 */
	public void update(User user) {
		userDAO.attachDirty(user);
	}
}
