package com.Unionfinance2.service;
import java.util.List;

import com.Unionfinance2.entity.Income;
import com.Unionfinance2.util.PageCut;

public interface IncomeService {
	//添加收入
	public boolean addIncome(Income income);
	//删除收入
	public boolean deleteIncome(Income income);
	//修改收入
	public boolean updateIncome(Income income);
	public Income viewIncome(int id);
	//获取所有信息
	public List<Income> CheckName();
	//分页查询
	public PageCut<Income> getPageCut(int curr,int pageSize);
	public PageCut<Income> getSomeCheck(int currentPage, int pageSize,String starttime,String endtime,String union,String entry); // 多条件查询测试
	public List<Income> getSumCheck(String starttime,String endtime,String union,String entry); //查询之后求查询条件下的拨款金额总和
	

}
