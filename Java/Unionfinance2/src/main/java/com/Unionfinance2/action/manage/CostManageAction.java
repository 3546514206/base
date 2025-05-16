package com.Unionfinance2.action.manage;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Unionfinance2.base.BaseAction;
import com.Unionfinance2.entity.Entry;
import com.Unionfinance2.entity.Income;
import com.Unionfinance2.entity.User;
import com.Unionfinance2.entity.Balance;
import com.Unionfinance2.entity.Cost;
import com.Unionfinance2.util.ExportUtilCost;
import com.Unionfinance2.util.ExportUtilIncome;
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
	public static int updatepage;  //修改后返回当前页
	public static double upatemoney;
	
	@Override
	public String execute() throws Exception {
		PageCut<Cost> pCut=costService.getPageCut(page,8);
		request.put("allCost", pCut);
		//加载所有工会信息
				Set<String> la = new HashSet<String>(); 
				if(userService.selectAllUser().size()>0){
					for (User user : userService.selectAllUser()) {
						la.add(user.getCompany());
					}
				} 
				session.put("allLabour", la);
		//加载所有条目信息
		List<Entry> li = entryService.CheckName();
		request.put("allEntry", li); 
		//判断是否存在支出信息
		if(pCut.getData().size()==0){
			String mark="没有支出信息";
			request.put("managerMsg", mark);
		}
		//得到所有的支出信息，不分页
				List<Cost> in = costService.CheckName();
				Double sum = 0.0;
				for (Cost cost : in) {
					sum+=cost.getCo_money();
				}
				//保留两位小数
				BigDecimal b = new BigDecimal(sum);
				double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		request.put("sum", f1);
		request.put("adss", "execute");
		return SUCCESS;
	}

	public String addCost() throws Exception{  //增加方法
		List<Balance> ba = balanceService.getOneUnion(cost.getCo_union());
		Double prebal = cost.getCo_money();
		if(ba.size() == 0){
			request.put("addCostMsg", "当前工会余额为0，支出失败！！");
		}else if(ba.get(0).getId().getBalance() < prebal){
			String str = "当前工会余额为不足,余额仅剩"+ba.get(0).getId().getBalance()+",支出失败！！";
			request.put("addCostMsg", str);
		}else if(ba.get(0).getId().getBalance() >= prebal){
			boolean boo = costService.addCost(cost);
			if(boo){
				List<Balance> te = balanceService.getOneUnion(cost.getCo_union());
				Double inbal = te.get(0).getId().getBalance();
				String st = "支出成功！"+cost.getCo_union()+"当前可用余额为"+inbal;
				request.put("addCostMsg", st);
			} else {
				request.put("addCostMsg", "服务器端错误,添加失败,建议您再次尝试");
			}
		}	
		//加载所有的条目，以便二次添加
		List<Entry> li = entryService.CheckName();
		request.put("allEntry", li); 
		return "addCost";
	}
	
	public String deleteCost() {  //删除方法
		boolean boo = costService.deleteCost(cost);
		String deleteCostMsg = "删除失败";
		if(boo){
			deleteCostMsg = "删除成功";
		}
		request.put("managerMsg", deleteCostMsg);
		PageCut<Cost> pCut=costService.getPageCut(page,8);
		request.put("allCost", pCut);
		if(pCut.getData().size()==0){
			String mark="没有支出信息";
			request.put("managerMsg", mark);
		}
		return "deleteCost";
	}
	
	public String toUpdateCost(){	//到修改界面，查询出所修改信息
		//得到所有的条目
				updatepage = page;
				Cost cost =costService.viewCost(co_id);
				upatemoney = cost.getCo_money();
				request.put("updateCost", cost);
				List<Entry> li = entryService.CheckName();
				request.put("allEntry", li);  
				//得到所有的工会信息
				Set<String> la = new HashSet<String>(); 
				if(userService.selectAllUser().size()>0){
					for (User user : userService.selectAllUser()) {
						la.add(user.getCompany());
					}
				}
				session.put("allLabour", la);
		return "toUpdateCost";
	}
	
	public String updateCost() {	//确认修改信息,修改资料
		List<Balance> ba = balanceService.getOneUnion(cost.getCo_union());
		Double prebal = cost.getCo_money();
		Double differ = prebal - upatemoney;
		if(ba.size() == 0){
			request.put("updateCostMsg", "当前工会余额为0，修改失败！！");
		}else if(ba.get(0).getId().getBalance() < differ){
			String str = "当前工会余额为不足,余额仅剩"+ba.get(0).getId().getBalance()+",修改失败！！";
			request.put("updateCostMsg", str);
		}else if(ba.get(0).getId().getBalance() > differ){
			boolean boo = costService.updateCost(cost);
			if(boo){
				List<Balance> te = balanceService.getOneUnion(cost.getCo_union());
				Double inbal = te.get(0).getId().getBalance();
				String st = "修改成功！"+cost.getCo_union()+"当前可用余额为"+inbal;
				request.put("updateCostMsg", st);
			} else {
				request.put("updateCostMsg", "服务器端错误,修改失败,建议您再次尝试");
			}
		}
			PageCut<Cost> pCut=costService.getPageCut(updatepage,8);
			request.put("allCost", pCut);
		return "updateCost";
	}
	
	//多条件查询  时间段+工会+条目
	public String Inquiry() {
		//前台接过来的数据传到Service层处理
		if(null==starttime && null==endtime && null==union && null==entry){  //查询后分页数据丢失
			starttime = (String) session.get("starttime");
			endtime = (String) session.get("endtime");
			union = (String) session.get("union");
			entry = (String) session.get("entry");
		}
		PageCut<Cost> co = costService.getSomeCheck(page,8,starttime, endtime, union, entry);
		List<Cost> mo = costService.getSumCheck(starttime, endtime, union, entry);
		request.put("allCost", co);
		double presum = 0;
		for(int i = 0;i<co.getData().size();i++){
			presum += co.getData().get(i).getCo_money();
		}
		//保留两位小数
		BigDecimal b2 = new BigDecimal(presum);
		double f2 = b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		request.put("presum",f2);  //查出当前页面的元素金额总和
		double sum = 0;
		for (Cost cost : mo) {
			sum+=cost.getCo_money();
		}
		//保留两位小数
		BigDecimal b = new BigDecimal(sum);
		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		request.put("sum",f1);  //查询本次查询总的支出金额
		if(co.getData().size()==0){
			String mark="没有你搜索的信息";
			request.put("managerMsg", mark);
		}
		request.put("adss", "Inquiry");		
		session.put("starttime", starttime);
		session.put("endtime", endtime);
		session.put("union", union);
		session.put("entry", entry);
		//加载所有条目信息
		List<Entry> li = entryService.CheckName();
		request.put("allEntry", li);
		return SUCCESS;	
	}
	
		//备份支出数据
		public String export() {
	        ExportUtilCost Export=new ExportUtilCost();
	        try {
	            Export.export(costService);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("导出数据有误");
	        }
	        return null;
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
