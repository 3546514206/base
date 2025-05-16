package com.Unionfinance2.action.manage;

import java.util.List;

import com.Unionfinance2.base.BaseAction;
import com.Unionfinance2.entity.User;

public class UserManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private int userId;	//得到前台的传来的id
	private String condition;//得到查询的内容
	
	public String execute(){
		
		//得到所有用户
		List<User> pCut=userService.selectAllUser();
		request.put("allUser", pCut);
		if(pCut.size()==0){
			String mark="没有用户";
			request.put("manageMeg", mark);
		}
		return SUCCESS;
	}
	
	public String addUser() {  //添加用户
		boolean boo = userService.addUser(user);
		//查重
		if(boo){
			request.put("addMsg", "添加成功");
		} else {
			request.put("addMsg", "添加失败！该账号已被使用");
		}
		return "addUser";
	}
	
	public String toUpdateUser(){	//到修改界面，查询出所修改用户信息
		User user = userService.viewUser(userId);
		request.put("updateUser", user);
		return "toUpdateUser";
	}
	
	public String updateUser() {	//确认修改信息,修改个人资料
		boolean boo = userService.updateUser(user);
		String result = "updateUser";
		if(boo){
			request.put("updateUserMsg", "修改成功");
		} else {
			request.put("updateUserMsg", "修改失败");
		}
		User userM = (User)session.get("manager");
		User userC = (User)session.get("cost");
		User userO = (User)session.get("ordinary");
		if(userM!=null && userM.getId()==user.getId()){	//当修改的是自己本人的时候，将修改后信息存入session
			User userData = userService.viewUser(user.getId());
			session.put("manager", userData);
			return "toUpdateMyself";
		}
		if(userC!=null&&userC.getId()==user.getId()){
			User userData = userService.viewUser(user.getId());
			session.put("cost", userData);
			return "toUpdateMyself";
		}
		if(userO!=null&&userO.getId()==user.getId()){
			User userData = userService.viewUser(user.getId());
			session.put("ordinary", userData);
			return "toUpdateMyself";
		}
		//加载所有的user
		List<User> pCut=userService.selectAllUser();
		request.put("allUser", pCut);
		return result;
	}

	public String deleteUser() {	//删除该用户，并查询所有用户
		User me = (User) session.get("manager");  //防止拨款管理删除自己
		if(me.getId() == user.getId()){
			request.put("deleteUserMsg", "抱歉,您不能删除自己");
		} else { 
			boolean boo = userService.deleteUser(user);
			if(boo){
				request.put("deleteUserMsg", "删除成功");
			} else {
				request.put("deleteUserMsg", "删除失败");
			}
		}
		//加载所有的user
		List<User> pCut=userService.selectAllUser();
		request.put("allUser", pCut);
		return "deleteUser";
	}
	
	public String Inquiry(){  //模糊查询
		List<User> u = userService.selectOne(condition);
		request.put("allUser", u);
		if(u.size()==0){
			String mark="没有你查询的用户";
			request.put("managerMsg", mark);
		}
		return SUCCESS;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}

