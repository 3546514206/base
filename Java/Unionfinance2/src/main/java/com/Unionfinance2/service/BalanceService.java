package com.Unionfinance2.service;

import java.util.List;

import com.Unionfinance2.entity.Balance;
import com.Unionfinance2.entity.BalanceId;
import com.Unionfinance2.util.PageCut;

public interface BalanceService {
	
	//计算每个工会的余额
	public List<Balance> getAllBanlace();
	public PageCut<Balance> getSomePageCut(int curr, int pageSize, String pass, String replace); //条件查询
	//单个工会查询余额
	public List<Balance> getOneUnion(String name);
}
