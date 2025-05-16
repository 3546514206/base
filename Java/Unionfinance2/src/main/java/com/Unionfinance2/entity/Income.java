package com.Unionfinance2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="z_income")
public class Income {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int in_id;
	@Column(length=30)
	private String in_time;
	@Column(length=50)
	private String in_entry;
	@Column(length=10)
	private Double in_money;
	@Column(length=8)
	private String in_operator;
	@Column(length=30)
	private String in_union;
	@Column(length=255)
	private String in_remark;
	public int getIn_id() {
		return in_id;
	}
	public void setIn_id(int in_id) {
		this.in_id = in_id;
	}
	public String getIn_time() {
		return in_time;
	}
	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}
	public String getIn_entry() {
		return in_entry;
	}
	public void setIn_entry(String in_entry) {
		this.in_entry = in_entry;
	}
	public Double getIn_money() {
		return in_money;
	}
	public void setIn_money(Double in_money) {
		this.in_money = in_money;
	}
	public String getIn_operator() {
		return in_operator;
	}
	public void setIn_operator(String in_operator) {
		this.in_operator = in_operator;
	}
	public String getIn_union() {
		return in_union;
	}
	public void setIn_union(String in_union) {
		this.in_union = in_union;
	}
	public String getIn_remark() {
		return in_remark;
	}
	public void setIn_remark(String in_remark) {
		this.in_remark = in_remark;
	}
	@Override
	public String toString() {
		return "Income [in_id=" + in_id + ", in_time=" + in_time + ", in_entry=" + in_entry + ", in_money=" + in_money
				+ ", in_operator=" + in_operator + ", in_union=" + in_union + ", in_remark=" + in_remark + "]";
	}
	
	
	
	
}
