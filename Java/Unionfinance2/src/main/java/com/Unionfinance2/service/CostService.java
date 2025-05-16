package com.Unionfinance2.service;
import java.util.List;

import com.Unionfinance2.entity.Cost;
import com.Unionfinance2.entity.Income;
import com.Unionfinance2.util.PageCut;

public interface CostService {
	//添加支出
	public boolean addCost(Cost cost);
	//删除支出
	public boolean deleteCost(Cost cost);
	//修改支出
	public boolean updateCost(Cost cost);
	public Cost viewCost(int id);
	//查重，获取所有信息
	public List<Cost> CheckName();
	//分页查询
	public PageCut<Cost> getPageCut(int curr,int pageSize);
	public PageCut<Cost> getSomeCheck(int currentPage, int pageSize,String starttime,String endtime,String union,String entry); // 多条件查询测试
	public List<Cost> getSumCheck(String starttime,String endtime,String union,String entry); //查询之后求查询条件下的支出金额总和
}
