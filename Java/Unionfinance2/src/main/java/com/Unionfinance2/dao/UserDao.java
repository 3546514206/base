package com.Unionfinance2.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Unionfinance2.base.BaseDao;
import com.Unionfinance2.entity.User;
import com.Unionfinance2.service.UserService;

@Service
public class UserDao extends BaseDao<User> implements UserService {


	public User login(String account, String password) {	//登录
		String hql = "from User u where u.account='"+account+"' and u.password= '"+password+"'";
		return (User)this.uniqueResult(hql);

	}
	
	@Override
	public boolean addUser(User user) {  //增加用户
		String hql = "from User u where u.account='"+user.getAccount()+"'";
		User userDataBase = (User) this.uniqueResult(hql);
		if(userDataBase==null){
			this.saveEntity(user);
			return true;
		}
		return false;
	}

	@Override
	public List<User> selectAllUser() {  //查找所有用户
		List<User> list = this.selectAll();
		return list;
	}

	@Override
	public User viewUser(int id){  //通过id查询用户
		User user = this.getEntity(id);
		return user;
	}
	@Override
	public boolean updateUser(User user) {	//修改用户信息
		return this.updateEntity(user);
	}
	@Override
	public boolean deleteUser(User user) {  //删除用户
		return this.deleteEntity(user);
	}

	@Override
	public List<User> selectOne(String condition) {  //模糊查询
		if(condition.equals("拨款管理") || condition.equals("拨款管") || condition.equals("拨款") || condition.equals("拨")){
			condition = "manage";
		}else if(condition.equals("支出管理") || condition.equals("支出管") || condition.equals("支出") || condition.equals("支")){
			condition = "cost";
		}else if(condition.equals("分会主席") || condition.equals("分会主") || condition.equals("分会") || condition.equals("分")){
			condition = "ordinary";
		}
		String hql = "select * from z_user u where 1=1 and u.account like "+"'%"+condition+"%'"+"or u.name like"+"'%"+condition+
				"%'"+"or u.identity like"+"'%"+condition+"%'";
		List<User> l = this.executeSQLQuery(hql);
		return l;
	}

	@Override
	public List<User> findUserByAccount(String account) {
		String sql = "select * from z_user where account = "+"'"+account+"'";
		List<User> l = this.executeSQLQuery(sql);
		return l;
	}

	
}
