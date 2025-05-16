package com.Unionfinance2.service;

import java.util.List;

import com.Unionfinance2.entity.User;
import com.Unionfinance2.util.PageCut;

public interface UserService {


	//登录
	public User login(String account,String password);
	//添加用户
	public boolean addUser(User user);
	//查询所有用户
	public List<User> selectAllUser();
	//查询条件
	public List<User> selectOne(String condition);
	//查询子用户
	public User viewUser(int id);
	public List<User> findUserByAccount(String account);
	//修改
	public boolean updateUser(User user);
	//删除
	public boolean deleteUser(User user);

}
