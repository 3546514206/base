package com.Unionfinance2.service;

import java.util.List;

import com.Unionfinance2.entity.Entry;
import com.Unionfinance2.util.PageCut;

public interface EntryService {
	//添加条目
	public boolean addEntry(Entry entry);
	//删除条目
	public boolean deleteEntry(Entry entry);
	//修改条目信息
	public boolean updateEntry(Entry entry);
	public Entry viewEntry(int id);
	//获取所有条目
	public List<Entry> CheckName();
	//条件查询
	public List<Entry> selectOne(String pass,String replace);
}
