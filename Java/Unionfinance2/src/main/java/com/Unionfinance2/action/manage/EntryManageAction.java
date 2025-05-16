package com.Unionfinance2.action.manage;

import java.util.List;

import com.Unionfinance2.base.BaseAction;
import com.Unionfinance2.entity.Entry;

public class EntryManageAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private Entry entry;
	private int page=1;
	private int en_id;  //得到前台传过来的id
	private String pass;
	private String replace;
	
	@Override
	public String execute() throws Exception {
		
		//获得所有条目信息
		List<Entry> en = entryService.CheckName();
		request.put("allEntry", en);
		if(en.size()==0){
			String mark="没有条目";
			request.put("managerMsg", mark);
		}
		return SUCCESS;
	}
	
	public String addEntry() throws Exception{  //增加方法
		boolean boo = entryService.addEntry(entry);
		//查重
		if(boo){
			request.put("addEntryMsg", "添加成功");
		} else {
			request.put("addEntryMsg", "添加失败,条目重复");
		}
		//加载后返回管理条目界面
		List<Entry> en = entryService.CheckName();
		request.put("allEntry", en);
		return "addEntry";
	}
	
	public String deleteEntry() {  //删除方法
		boolean boo = entryService.deleteEntry(entry);
		String deleteEntryMsg = "删除失败";
		if(boo){
			deleteEntryMsg = "删除成功";
		}
		request.put("managerMsg", deleteEntryMsg);
		//加载所有条目信息
		List<Entry> en = entryService.CheckName();
		request.put("allEntry", en);
		if(en.size()==0){
			String mark="没有条目";
			request.put("managerMsg", mark);
		}
		return "deleteEntry";
	}
	
	public String toUpdateEntry(){	//到修改界面，查询出所修改信息
		Entry entry =entryService.viewEntry(en_id);
		request.put("updateEntry", entry);
		return "toUpdateEntry";
	}
	
	public String updateEntry() {	//确认修改信息,修改资料
		String type = entry.getEn_type();
		if("拨款".equals(type)){
			entry.setEn_type("0");
		}else if("支出".equals(type)){
			entry.setEn_type("1");
		}
		boolean boo = entryService.updateEntry(entry);
		if(boo){
			request.put("updateEntryMsg", "修改成功");
		} else {
			request.put("updateEntryMsg", "修改失败");
		}
		List<Entry> en = entryService.CheckName();
		request.put("allEntry", en);
		return "updateEntry";
	}
	
	public String Inquiry(){  //多条件查询
		List<Entry> li = entryService.selectOne(pass, replace);
		request.put("allEntry", li);
		if(li.size()==0){
			String mark="没有你搜索的条目";
			request.put("managerMsg", mark);
		}
		return SUCCESS;	
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getEn_id() {
		return en_id;
	}

	public void setEn_id(int en_id) {
		this.en_id = en_id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getReplace() {
		return replace;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}
	
	
}
