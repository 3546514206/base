package com.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.service.UserService;


public class RegisterAction extends ActionSupport {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void validate() {
		super.clearErrorsAndMessages();
		if("".equals(username)) {
			super.addActionError("用户名不能为空！");
		}
		if("".equals(password)) {
			super.addActionError("密码不能为空！");
		}
	}
	
	public String execute() {
		if(userService.userRegister(username, password)) {
			super.addActionMessage("注册成功！");
		} else {
			super.addActionError("注册失败,该用户名已存在！");
		}
		return "success";
	}
}
