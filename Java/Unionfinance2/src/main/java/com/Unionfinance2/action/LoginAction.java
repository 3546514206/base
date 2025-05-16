package com.Unionfinance2.action;
/*登录action
 * 
 * */
import com.Unionfinance2.base.BaseAction;
import com.Unionfinance2.entity.User;

public class LoginAction extends BaseAction {
  
	private static final long serialVersionUID = 1L;
	
	private User user;   //获得一个User对象，来比对登录
	private String randStr;  //验证码
	
	@Override
	public String execute() throws Exception {	
		User userDataBase;
		/* 为了解决带验证码刷新主页这个问题
		 * 当在主页登录过后刷新时，验证码action没有生成验证码
		 * 通过这个逻辑，做了如下判断，解决了这个问题
		 * */
		
		if(user!=null){ 
			userDataBase = userService.login(user.getAccount(),user.getPassword());
			if(userDataBase!=null){
				String vCode=(String) session.get("randStr");	
				session.remove("randStr");
				if(vCode == null) {  //刷新时action未生成验证码			
					if(userDataBase.getIdentity().equals("cost")){
						session.put("user", userDataBase);
						session.put("cost", userDataBase);	
						return "cost";
					} else if(userDataBase.getIdentity().equals("ordinary")) {
						session.put("user", userDataBase);
						session.put("ordinary", userDataBase);
						return "ordinary";
					}	
					else {
						session.put("user", userDataBase);
						session.put("manager", userDataBase);
						return "manage";
					}
				}	
				 if(!vCode.equals(randStr)){  //登录时action生成了验证码，只是比对失败后返回界面
					request.put("loginMeg", "验证码错误");
					return "login";
				}
				
				session.put("user", userDataBase);//将用户对象放进session	
				if(userDataBase.getIdentity().equals("cost")) {
					session.put("cost", userDataBase);
					return "cost";
				}else if(userDataBase.getIdentity().equals("ordinary")) {
					session.put("ordinary", userDataBase);
					return "ordinary";
				}
				else {
					session.put("manager", userDataBase);
					return "manage";
				}
			} else{
				request.put("loginMeg", "用户名和密码输入错误");
			}		
		}
		return "login";
	}

	//get，set方法
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getRandStr() {
		return randStr;
	}

	public void setRandStr(String randStr) {
		this.randStr = randStr;
	}

}