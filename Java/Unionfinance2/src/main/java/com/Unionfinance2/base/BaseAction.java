package com.Unionfinance2.base;

import java.util.Map;



import javax.annotation.Resource;
import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.Unionfinance2.service.UserService;
import com.Unionfinance2.service.EntryService;
import com.Unionfinance2.service.IncomeService;
import com.Unionfinance2.service.CostService;
import com.Unionfinance2.service.BalanceService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware,RequestAware{

	/**
	 * 这是一些对于session、request的一种定义以便在后来对其直接的引用
	 */
	private static final long serialVersionUID = 1L;
	
	protected Map<String,Object> session;
	protected Map<String,Object> request;
	//service 注入
	@Resource
	protected BalanceService balanceService;
	@Resource
	protected CostService costService;
	@Resource
	protected IncomeService incomeService;
	@Resource
	protected EntryService entryService;
	@Resource
	protected UserService userService;
	
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	protected ServletContext getContext(){
		return ServletActionContext.getServletContext();
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	public Map<String,Object> getSession(){
		return session;
	}
	
	public Map<String, Object> getRequest() {
		return request;
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}
