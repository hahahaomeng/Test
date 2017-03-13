package com.order.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.order.entity.User;
import com.order.service.UserService;
import com.order.util.CreateUUID;
import com.order.util.MD5;
import com.order.util.Mail;
import com.order.util.UploadFile;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserService userService;

	@Override
	public User getModel() {
		return this.user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 商家注册
	 * 
	 * @throws Exception
	 */
	public String register() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String isCheckEmail = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkEmail");
		if (null == isCheckEmail || !"true".equals(isCheckEmail)) {
			json.accumulate("code", 500);
			json.accumulate("errMsg", "csrf");
			response.getWriter().print(json.toString());
		} else {
			this.user.setNotice("http://oh59g24op.bkt.clouddn.com/user-img-big.jpg");
			this.userService.save(this.user);
			json.accumulate("code", 200);
			json.accumulate("data", null);
			response.getWriter().print(json.toString());
		}	
		return NONE;
	}

	/**
	 * 检测用户名是否存在
	 * 
	 * @throws IOException
	 */
	public String checkUserName() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		User existUser = this.userService.findByUserName(this.user
				.getUserName());
		if (existUser == null) {
			json.accumulate("code", 200);
		} else {
			json.accumulate("code", 300);
			json.accumulate("errMsg", "用户名已存在");
		}
		out.println(json.toString());
		out.flush();
		out.close();
		return NONE;
	}

	/**
	 * 商家登录
	 * 
	 * @throws Exception
	 */
	public String login() throws Exception {
		// json
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		User existUser = this.userService.findByUserName(this.user
				.getUserName());
		String pass = MD5.encoderByMd5(this.user.getPassword()).toString();
		if (existUser == null) {
			json.accumulate("code", 300);
			json.accumulate("errMsg", "username or password error");
		} else if (existUser.getPassword().equals(pass)
				&& "owner".equals(existUser.getType())) {
			User user = new User();
			user.setUserId(existUser.getUserId());
			json.accumulate("code", 200);
			json.accumulate("data", user);
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("user", existUser);
		} else if(existUser.getState().equals("3")){
			json.accumulate("code", 400);
			json.accumulate("message", "You have been blocked");
		}
		else {
			json.accumulate("code", 300);
			json.accumulate("errMsg", "username or password error");
		}
		out.println(json.toString());
		out.flush();
		out.close();
		return NONE;
	}

	/**
	 * 邮箱验证
	 * 
	 * @return
	 * @throws IOException
	 */
	public String checkCode() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String code = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("checkCode");
		if (user.getCheckCode().equals(code)) {
			json.accumulate("code", 200);
			ServletActionContext.getRequest().getSession()
					.setAttribute("checkEmail", "true");
		} else {
			json.accumulate("code", 300);
			json.accumulate("errMsg", "激活码不正确");
		}
		response.getWriter().print(json.toString());
		return NONE;
	}

	/**
	 * 发送激活邮件
	 * 
	 * @return
	 * @throws IOException
	 */
	public String sendEmail() throws IOException {
		String checkCode = CreateUUID.createUUID().toString().substring(0, 6);
		Mail.send(user.getEmail(), checkCode);
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("checkCode", checkCode);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.accumulate("code", 200);
		out.println(json.toString());
		out.flush();
		out.close();
		return NONE;
	}

	/**
	 * 判断邮箱是否存在
	 * 
	 * @throws IOException
	 */
	public String checkEmail() throws IOException {
		// 查找所有用户
		// System.out.println(user.getEmail());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		List<User> list2 = this.userService.findAllUser();
		String email = user.getEmail();
		// System.out.println(list2.size());
		int j = 0;
		for (User a : list2) {
			if (a.getEmail().equals(email) == true) {
				j = 1;
			}
		}
		if (j == 1) {
			json.accumulate("code", 300);
			json.accumulate("errMsg", "该邮箱已经注册");
		} else {
			json.accumulate("code", 200);
		}
		out.println(json.toString());
		out.flush();
		out.close();
		return NONE;
	}

	/**
	 * 获取个人信息
	 * 
	 * @throws IOException
	 */
	public String getPersonInfo() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		User user1 = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		JSONObject json = new JSONObject();
		json.accumulate("code", 200);
		json.accumulate("data", userService.findByUserId(user1.getUserId()));
		response.getWriter().println(json.toString());
		return NONE;
	}

	/**
	 * 修改头像
	 * 
	 * @return
	 * @throws IOException
	 */
	public String modShopLogo() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String url = UploadFile.upload(user.getNotice());
		User user1 = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		User user2 = userService.findByUserId(user1.getUserId());
		user2.setNotice(url);
		userService.update(user2);
		json.accumulate("code", 200);
		json.accumulate("data", null);
		response.getWriter().print(json.toString());
		return NONE;
	}

	/**
	 * 修改个人信息
	 * 
	 * @throws IOException
	 */
	public String modPersonInfo() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		User user1 = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		User user2 = userService.findByUserId(user1.getUserId());
		user2.setPhone(user.getPhone());
		userService.update(user2);
		JSONObject json = new JSONObject();
		json.accumulate("code", 200);
		json.accumulate("data", null);
		response.getWriter().println(json.toString());
		return NONE;
	}

	/**
	 * 注销
	 * 
	 * @return
	 * @throws IOException
	 */
	public String logout() throws IOException {
		ServletActionContext.getRequest().getSession()
				.setAttribute("user", null);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		json.accumulate("code", 200);
		json.accumulate("data", null);
		response.getWriter().println(json.toString());
		return NONE;
	}
}
