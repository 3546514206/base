package com.Unionfinance2.action.manage;

import java.io.UnsupportedEncodingException;
import java.util.List;
import com.Unionfinance2.base.BaseAction;
import com.Unionfinance2.entity.Balance;
import com.Unionfinance2.util.ExportUtilBalance;
import com.Unionfinance2.util.ExportUtilIncome;

public class ViewBalanceAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String union;
	
	@Override
	public String execute() throws Exception {
		List<Balance> ba = balanceService.getAllBanlace();
		request.put("allBalance", ba);
		if(ba.size()==0){
			String mark="没有余额信息";
			request.put("managerMsg", mark);
		}
		return SUCCESS;
	}
	
	public String Inquiry() throws UnsupportedEncodingException{
		String unio = new String(union.getBytes("iso8859-1"),"utf-8");
		List<Balance> ba = balanceService.getOneUnion(unio);
		if(ba.size()==0){
			String mark="您的工会余额为0";
			request.put("managerMsg", mark);
		}
		request.put("allBalance", ba);
		
	return SUCCESS;	
	}
	
	//备份余额数据
		public String export() {
	        ExportUtilBalance Export=new ExportUtilBalance();
	        try {
	            Export.export(balanceService);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("导出数据有误");
	        }
	        return null;
	    }
	
	public String getUnion() {
		return union;
	}
	public void setUnion(String union) {
		this.union = union;
	}
	
	
	

}
