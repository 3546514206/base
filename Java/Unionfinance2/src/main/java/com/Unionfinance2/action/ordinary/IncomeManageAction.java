package com.Unionfinance2.action.ordinary;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Unionfinance2.base.BaseAction;
import com.Unionfinance2.entity.Balance;
import com.Unionfinance2.entity.Entry;
import com.Unionfinance2.entity.Income;
import com.Unionfinance2.entity.User;
import com.Unionfinance2.util.ExportUtilIncome;
import com.Unionfinance2.util.PageCut;

public class IncomeManageAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private Income income;
	private int page=1;
	private int in_id;  //得到前台传过来的id
	private String starttime;//开始的时间
	private String endtime;//结束的时间
	private String union; //工会
	private String entry;//条目
	
	@Override
	public String execute() throws Exception {
		String unio = new String(union.getBytes("iso8859-1"),"utf-8");
		//分会主席查看所有支出的时候，传过来一个分会名称，其他都为null，但是当查询条件
		if(null==starttime && null==endtime &&  null==entry){
			starttime = "";
			endtime = "";
			entry = "";
		}
		PageCut<Income> in = incomeService.getSomeCheck(page,8,starttime, endtime, unio, entry);
		List<Income> mo = incomeService.getSumCheck(starttime, endtime, unio, entry);
		request.put("allIncome", in);
		double presum = 0;
		for(int i = 0;i<in.getData().size();i++){
			presum += in.getData().get(i).getIn_money();
		}
		request.put("presum",presum);  //查出当前页面的元素金额总和
		double sum = 0;
		for (Income income : mo) {
			sum+=income.getIn_money();
		}
		request.put("sum",sum);  //查询本次查询总的拨款金额
		if(in.getData().size()==0){
			String mark="没有你搜索的信息";
			request.put("managerMsg", mark);
		}
		//加载所有条目信息
		List<Entry> li = entryService.CheckName();
		request.put("allEntry", li);
		request.put("adss", "execute");
		return SUCCESS;
	}
	
	//用在分会主席页面的多条件查询
	@SuppressWarnings("unused")
	public String Inquiry() throws UnsupportedEncodingException {
		System.out.println("调用的查询方法！！！");
		//前台接过来的数据传到Service层处理
		String unio = new String(union.getBytes("iso8859-1"),"utf-8");
		if(null==starttime && null==endtime && null==entry){  //查询后分页数据丢失
			starttime = (String) session.get("starttime");
			endtime = (String) session.get("endtime");
			entry = (String) session.get("entry");
		}
		//分会主席查看所有支出的时候，传过来一个分会名称，其他都为null，但是当查询条件
		if(null==starttime && null==endtime &&  null==entry){
			starttime = "";
			endtime = "";
			entry = "";
		}
		PageCut<Income> in = incomeService.getSomeCheck(page,8,starttime, endtime, unio, entry);
		List<Income> mo = incomeService.getSumCheck(starttime, endtime, unio, entry);
		request.put("allIncome", in);
		double presum = 0;
		for(int i = 0;i<in.getData().size();i++){
			presum += in.getData().get(i).getIn_money();
		}
		request.put("presum",presum);  //查出当前页面的元素金额总和
		double sum = 0;
		for (Income income : mo) {
			sum+=income.getIn_money();
		}
		request.put("sum",sum);  //查询本次查询总的拨款金额
		if(in.getData().size()==0){
			String mark="没有你搜索的信息";
			request.put("managerMsg", mark);
		}
		request.put("adss", "Inquiry");		
		session.put("starttime", starttime);
		session.put("endtime", endtime);
		session.put("unio", unio);
		session.put("entry", entry);
		//加载所有条目信息
		List<Entry> li = entryService.CheckName();
		request.put("allEntry", li);
		return SUCCESS;	
	}
	
	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getIn_id() {
		return in_id;
	}

	public void setIn_id(int in_id) {
		this.in_id = in_id;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getUnion() {
		return union;
	}

	public void setUnion(String union) {
		this.union = union;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}
	

		
}
