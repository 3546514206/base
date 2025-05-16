package com.Unionfinance2.dao;
import java.util.List;
import org.springframework.stereotype.Service;
import com.Unionfinance2.base.BaseDao;
import com.Unionfinance2.entity.Income;
import com.Unionfinance2.service.IncomeService;
import com.Unionfinance2.util.PageCut;

@Service
public class IncomeDao extends BaseDao<Income> implements IncomeService{
	@Override
	public boolean addIncome(Income income) {
		this.saveEntity(income);
		return true;
	}

	@Override
	public boolean deleteIncome(Income income) {
		return this.deleteEntity(income);
	}

	@Override
	public boolean updateIncome(Income income) {
		return this.updateEntity(income);
	}
	
	@Override
	public Income viewIncome(int id){
		Income income = this.getEntity(id);
		return income;
	}

	@Override
	public List<Income> CheckName() {
		return selectAll();
	}
	
	@Override
	public PageCut<Income> getPageCut(int curr, int pageSize) {
		String hql = "select count(*) from Income";
		String selectHql =  "from Income";
		int count = ((Long) this.uniqueResult(hql)).intValue();
		PageCut<Income> pc = new PageCut<Income>(curr,pageSize,count);
		pc.setData(this.getEntityLimitList(selectHql, (curr-1)*pageSize, pageSize));
		return pc;
	}

	@Override
	public PageCut<Income> getSomeCheck(int currentPage, int pageSize,String starttime, String endtime, String union, String entry) {
		String selecthql = null;
		int count = 0; 
		String hql = null;
		selecthql="from Income i where 1 = 1 ";
		hql="select count(*) from Income i where 1=1 ";
		if(!("").equals(union)){
			selecthql+="and i.in_union="+"'"+union+"'";
			hql+="and i.in_union="+"'"+union+"'";
		}
		if(!("").equals(entry)){
			selecthql+="and i.in_entry="+"'"+entry+"'";
			hql+="and i.in_entry="+"'"+entry+"'";
		}
		if(!("").equals(starttime) && !("").equals(endtime)){
			selecthql+="and i.in_time between "+"'"+starttime+"'"+"and"+"'"+endtime+"'";
			hql+="and i.in_time between "+"'"+starttime+"'"+"and"+"'"+endtime+"'";
		}
		count = ((Long) this.uniqueResult(hql)).intValue();
		PageCut<Income> pc = new PageCut<Income>(currentPage, pageSize, count);
		pc.setData(this.getEntityLimitList(selecthql,(currentPage - 1) * pageSize, pageSize));
		return pc;
	}

	@Override
	public List<Income> getSumCheck(String starttime, String endtime, String union, String entry) {
		String hql = null;
		hql="select * from z_income i where 1=1 ";
		if(!("").equals(union)){
			hql+="and i.in_union="+"'"+union+"'";
		}
		if(!("").equals(entry)){
			hql+="and i.in_entry="+"'"+entry+"'";
		}
		if(!("").equals(starttime) && !("").equals(endtime)){
			hql+="and i.in_time between "+"'"+starttime+"'"+"and"+"'"+endtime+"'";
		}
		List<Income> l = this.executeSQLQuery(hql);
		return l;
	}

}
