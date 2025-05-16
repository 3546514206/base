package com.Unionfinance2.action.manage;

import java.math.BigDecimal;
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
	public static int updatepage;  //修改后返回当前页
	
	@Override
	public String execute() throws Exception {
		//加载所有的拨款信息
		PageCut<Income> pCut=incomeService.getPageCut(page,8);
		request.put("allIncome", pCut);
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
		//判断是否存在拨款信息
		if(pCut.getData().size()==0){
			String mark="没有拨款信息";
			request.put("managerMsg", mark);
		}
		//得到所有的拨款信息，不分页
		List<Income> in = incomeService.CheckName();
		Double sum = 0.0;
		for (Income income : in) {
			sum+=income.getIn_money();
		}
		//保留两位小数
		BigDecimal b = new BigDecimal(sum);
		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		request.put("sum", f1);
		request.put("adss", "execute");
		return SUCCESS;
	}


	public String addIncome() throws Exception{
		boolean boo = incomeService.addIncome(income);
		List<Balance> ba = balanceService.getOneUnion(income.getIn_union());
		String bal = income.getIn_union()+"目前余额为"+ba.get(0).getId().getBalance();
		request.put("bal",bal);
		if(boo){
			request.put("addIncomeMsg", "拨款成功");
		} else {
			request.put("addIncomeMsg", "拨款失败");
		}
		List<Entry> li = entryService.CheckName();
		request.put("allEntry", li);  //得到所有的条目,以便二次添加
		return "addIncome";
	}
	
	public String deleteIncome() {
		boolean boo = incomeService.deleteIncome(income);
		String deleteIncomeMsg = "删除失败";
		if(boo){
			deleteIncomeMsg = "删除成功";
		}
		request.put("deleteIncomeMsg", deleteIncomeMsg);
		PageCut<Income> pCut=incomeService.getPageCut(page,8);
		request.put("allIncome", pCut);
		if(pCut.getData().size()==0){
			String mark="没有收入信息";
			request.put("managerMsg", mark);
		}
		return "deleteIncome";
	}
	
	public String toUpdateIncome(){	//到修改界面，查询出所修改信息
		//得到所有的条目
		updatepage = page;
		Income income =incomeService.viewIncome(in_id);
		request.put("updateIncome", income);
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
		return "toUpdateIncome";
	}
	
	public String updateIncome() {	//确认修改信息,修改资料
		boolean boo = incomeService.updateIncome(income);
		//修改之后打印下余额
		List<Balance> ba = balanceService.getOneUnion(income.getIn_union());
		if(boo){
			String str = "修改成功,"+income.getIn_union()+"当前可用余额为"+ba.get(0).getId().getBalance();
			request.put("updateIncomeMsg", str);
		} else {
			request.put("updateIncomeMsg", "修改失败");
		}
			PageCut<Income> pCut=incomeService.getPageCut(updatepage,8);
			request.put("allIncome", pCut);
		return "updateIncome";
	}
	
	//用在管理员页面的多条件查询
	public String Inquiry() {
		//前台接过来的数据传到Service层处理
		if(null==starttime && null==endtime && null==union && null==entry){  //查询后分页数据丢失
			starttime = (String) session.get("starttime");
			endtime = (String) session.get("endtime");
			union = (String) session.get("union");
			entry = (String) session.get("entry");
		}
		PageCut<Income> in = incomeService.getSomeCheck(page,8,starttime, endtime, union, entry);
		List<Income> mo = incomeService.getSumCheck(starttime, endtime, union, entry);
		request.put("allIncome", in);
		double presum = 0;
		for(int i = 0;i<in.getData().size();i++){
			presum += in.getData().get(i).getIn_money();
		}
		//保留两位小数
		BigDecimal b = new BigDecimal(presum);
		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		request.put("presum",f1);  //查出当前页面的元素金额总和
		double sum = 0;
		for (Income income : mo) {
			sum+=income.getIn_money();
		}
		//保留两位小数
		BigDecimal b2 = new BigDecimal(sum);
		double f2 = b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		request.put("sum",f2);  //查询本次查询总的拨款金额
		if(in.getData().size()==0){
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

	
	//备份收入数据
	public String export() {
        ExportUtilIncome Export=new ExportUtilIncome();
        try {
            Export.export(incomeService);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("导出数据有误");
        }
        return null;
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
