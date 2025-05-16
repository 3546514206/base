package com.Unionfinance2.action.manage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Unionfinance2.base.BaseAction;
import com.Unionfinance2.entity.Entry;
import com.Unionfinance2.entity.User;

//增加方法的中转站，提前加载需要的信息，以便增加时使用
public class ChangeAction extends BaseAction{
	
	private static final long serialVersionUID = 6372579968636067251L;
	private int page=1;
	private String identity;  //获得用户身份

	//用户模块中转站
	public String addUser() { 
		return "addUser";
	}
	
	//拨款模块中转
	public String addIncome(){
		//得到所有的条目
		List<Entry> li = entryService.CheckName();
		request.put("allEntry", li);
		//得到所有的工会，下拉菜单中显示
		Set<String> la = new HashSet<String>(); 
		if(userService.selectAllUser().size()>0){
			for (User user : userService.selectAllUser()) {
				la.add(user.getCompany());
			}
		}
		session.put("allLabour", la);  
		return "addIncome";
	}
	
	//支出模块中转
		public String addCost(){
			//得到所有的条目
			List<Entry> li = entryService.CheckName();
			request.put("allEntry", li);
			//得到所有的工会，下拉菜单中显示
			Set<String> la = new HashSet<String>(); 
			if(userService.selectAllUser().size()>0){
				for (User user : userService.selectAllUser()) {
					la.add(user.getCompany());
				}
			}
			session.put("allLabour", la);
			return "addCost";
		}
		
	//获得个人资料，修改个人信息的时候用
	public String getMyself(){
		User user = null;
		if(identity.equals("cost")){
			user = (User)session.get("cost");
		} else if(identity.equals("ordinary")) {
			user = (User)session.get("ordinary");
		} else if(identity.equals("manager")) {
			user = (User)session.get("manager");
		}
		request.put("user", user);
		return "getMyself";
	}

	//退出登录后,防倒退再次进入
	public String logOff(){
		request.put("identity", identity);
		return "logOff";
	}
	//退出登录
	public String out(){
		session.remove("user");
		if(identity.equals("manager")){
			session.remove("manager");//清除session中的user信息	
		}else if(identity.equals("cost")){
			session.remove("cost");
		}else if(identity.equals("ordinary")){
			session.remove("ordinary");
		}
		return "out";
	}

	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

}
