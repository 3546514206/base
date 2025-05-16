package com.Unionfinance2.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import com.Unionfinance2.base.BaseDao;
import com.Unionfinance2.entity.Balance;
import com.Unionfinance2.service.BalanceService;
import com.Unionfinance2.util.Garbled;
import com.Unionfinance2.util.PageCut;

@Service
public class BalanceDao extends BaseDao<Balance> implements BalanceService{

	@Override
	public PageCut<Balance> getSomePageCut(int curr, int pageSize, String pass, String replace) {
		String hql;
		String selecthql;
		hql = "select count(*) from Balance where "+pass+"='"+replace+"'";
		selecthql="from Balance where "+pass+"='"+replace+"'";
		int count = ((Long) this.uniqueResult(hql)).intValue();
		PageCut<Balance> pc = new PageCut<Balance>(curr,pageSize,count);
		pc.setData(this.getEntityLimitList(selecthql, (curr-1)*pageSize, pageSize));
		return pc;
	}

	@Override
	public List<Balance> getOneUnion(String name) {
		String sql = "select * from balance where in_union = '"+name+"'";	
		List<Balance> b = this.executeSQLQuery(sql);
		return b;
	}

	@Override
	public List<Balance> getAllBanlace() {
		List<Balance> b = this.selectAll();
		return b;
	}

}

