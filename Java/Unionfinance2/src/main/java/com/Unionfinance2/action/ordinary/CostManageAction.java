package com.Unionfinance2.action.ordinary;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.Unionfinance2.base.BaseAction;
import com.Unionfinance2.entity.Entry;
import com.Unionfinance2.entity.Cost;
import com.Unionfinance2.util.PageCut;

public class CostManageAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private Cost cost;
	private int page=1;
	private int co_id;  //得到前台传过来的id
	private String starttime;//开始的时间
	private String endtime;//结束的时间
	private String union;
	private String entry;
	
	@Override
	public String execute() throws Exception {
		//前台接过来的数据传到Service层处理
		String unio = new String(union.getBytes("iso8859-1"),"utf-8");
		//分会主席查看所有支出的时候，传过来一个分会名称，其他都为null，但是当查询条件
		if(null==starttime && null==endtime &&  null==entry){
			starttime = "";
			endtime = "";
			entry = "";
		}
		PageCut<Cost> co = costService.getSomeCheck(page,8,starttime, endtime, unio, entry);
		List<Cost> mo = costService.getSumCheck(starttime, endtime, unio, entry);
		request.put("allCost", co);
		double presum = 0;
		for(int i = 0;i<co.getData().size();i++){
			presum += co.getData().get(i).getCo_money();
		}
		request.put("presum",presum);  //查出当前页面的元素金额总和
		double sum = 0;
		for (Cost cost : mo) {
			sum+=cost.getCo_money();
		}
		request.put("sum",sum);  //查询本次查询总的支出金额
		if(co.getData().size()==0){
			String mark="没有你搜索的信息";
			request.put("managerMsg", mark);
		}	
		//加载所有条目信息
		List<Entry> li = entryService.CheckName();
		request.put("allEntry", li);
		session.put("unio", unio);
		request.put("adss", "execute");
		return SUCCESS;
	}
	
	//多条件查询  时间段+条目(分会主席用)
	public String Inquiry() throws UnsupportedEncodingException {
		//前台接过来的数据传到Service层处理
		String unio = new String(union.getBytes("iso8859-1"),"utf-8");
		if(null==starttime && null==endtime && null==entry){
			  //查询后分页数据丢失
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
		PageCut<Cost> co = costService.getSomeCheck(page,8,starttime, endtime, unio, entry);
		List<Cost> mo = costService.getSumCheck(starttime, endtime, unio, entry);
		request.put("allCost", co);
		double presum = 0;
		for(int i = 0;i<co.getData().size();i++){
			presum += co.getData().get(i).getCo_money();
		}
		request.put("presum",presum);  //查出当前页面的元素金额总和
		double sum = 0;
		for (Cost cost : mo) {
			sum+=cost.getCo_money();
		}
		request.put("sum",sum);  //查询本次查询总的支出金额
		if(co.getData().size()==0){
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
		
		public Cost getCost() {
			return cost;
		}

		public void setCost(Cost cost) {
			this.cost = cost;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getCo_id() {
			return co_id;
		}

		public void setCo_id(int co_id) {
			this.co_id = co_id;
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
