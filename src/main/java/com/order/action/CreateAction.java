package com.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.order.util.CheckCode;

public class CreateAction extends ActionSupport {

	public String create() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应图片,一定不能缺少这句话,否则错误.
		response.setContentType("image/jpeg");
		CheckCode.createCheckCode(request, response);
		
		return NONE;
	}
	
	public String check() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkCode = request.getParameter("checkCode");
		String randCheckCode = (String) request.getSession().getAttribute("randCheckCode");
		System.out.println(checkCode + " " + randCheckCode);
		if (randCheckCode.equalsIgnoreCase(checkCode)) {
			return SUCCESS;
		}
		return ERROR;
	}
}
