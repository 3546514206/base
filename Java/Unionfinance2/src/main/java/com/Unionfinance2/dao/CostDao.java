package com.Unionfinance2.dao;
import java.util.List;
import org.springframework.stereotype.Service;
import com.Unionfinance2.base.BaseDao;
import com.Unionfinance2.entity.Cost;
import com.Unionfinance2.entity.Income;
import com.Unionfinance2.service.CostService;
import com.Unionfinance2.util.PageCut;

@Service
public class CostDao extends BaseDao<Cost> implements CostService{
	@Override
	public boolean addCost(Cost cost) {
		this.saveEntity(cost);
		return true;
	}

	@Override
	public boolean deleteCost(Cost cost) {
		return this.deleteEntity(cost);
	}

	@Override
	public boolean updateCost(Cost cost) {
		return this.updateEntity(cost);
	}
	
	@Override
	public Cost viewCost(int id){
		Cost cost = this.getEntity(id);
		return cost;
	}

	@Override
	public List<Cost> CheckName() {
		return selectAll();
	}

	@Override
	public PageCut<Cost> getPageCut(int curr, int pageSize) {
		String hql = "select count(*) from Cost";
		String selectHql =  "from Cost";
		int count = ((Long) this.uniqueResult(hql)).intValue();
		PageCut<Cost> pc = new PageCut<Cost>(curr,pageSize,count);
		pc.setData(this.getEntityLimitList(selectHql, (curr-1)*pageSize, pageSize));
		return pc;
	}

	@Override
	public PageCut<Cost> getSomeCheck(int currentPage, int pageSize, String starttime, String endtime, String union,
			String entry) {
		String selecthql = null;
		int count = 0; 
		String hql = null;
		selecthql="from Cost c where 1 = 1 ";
		hql="select count(*) from Cost c where 1=1 ";
		if(!("").equals(union)){
			selecthql+="and c.co_union="+"'"+union+"'";
			hql+="and c.co_union="+"'"+union+"'";
		}
		if(!("").equals(entry)){
			selecthql+="and c.co_entry="+"'"+entry+"'";
			hql+="and c.co_entry="+"'"+entry+"'";
		}
		if(!("").equals(starttime) && !("").equals(endtime)){
			selecthql+="and c.co_time between "+"'"+starttime+"'"+"and"+"'"+endtime+"'";
			hql+="and c.co_time between "+"'"+starttime+"'"+"and"+"'"+endtime+"'";
		}
		count = ((Long) this.uniqueResult(hql)).intValue();
		PageCut<Cost> pc = new PageCut<Cost>(currentPage, pageSize, count);
		pc.setData(this.getEntityLimitList(selecthql,(currentPage - 1) * pageSize, pageSize));
		return pc;
	}

	@Override
	public List<Cost> getSumCheck(String starttime, String endtime, String union, String entry) {
		String hql = null;
		hql="select * from z_cost c where 1=1 ";
		if(!("").equals(union)){
			hql+="and c.co_union="+"'"+union+"'";
		}
		if(!("").equals(entry)){
			hql+="and c.co_entry="+"'"+entry+"'";
		}
		if(!("").equals(starttime) && !("").equals(endtime)){
			hql+="and c.co_time between "+"'"+starttime+"'"+"and"+"'"+endtime+"'";
		}
		List<Cost> c = this.executeSQLQuery(hql);
		return c;
	}

	
	
}
