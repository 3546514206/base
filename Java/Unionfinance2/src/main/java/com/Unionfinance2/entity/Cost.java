package com.Unionfinance2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="z_cost")
public class Cost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int co_id;
	@Column(length=30)
	private String co_time;
	@Column(length=30)
	private String co_entry;
	@Column(length=10)
	private Double co_money;
	@Column(length=8)
	private String co_operator;  //经手人
	@Column(length=8)
	private String co_fortor;  //报账人
	@Column(length=30)
	private String co_union;
	@Column(length=255)
	private String co_remark;
	public int getCo_id() {
		return co_id;
	}
	public void setCo_id(int co_id) {
		this.co_id = co_id;
	}
	public String getCo_time() {
		return co_time;
	}
	public void setCo_time(String co_time) {
		this.co_time = co_time;
	}
	public String getCo_entry() {
		return co_entry;
	}
	public void setCo_entry(String co_entry) {
		this.co_entry = co_entry;
	}
	public Double getCo_money() {
		return co_money;
	}
	public void setCo_money(Double co_money) {
		this.co_money = co_money;
	}
	public String getCo_operator() {
		return co_operator;
	}
	public void setCo_operator(String co_operator) {
		this.co_operator = co_operator;
	}
	public String getCo_union() {
		return co_union;
	}
	public void setCo_union(String co_union) {
		this.co_union = co_union;
	}
	public String getCo_remark() {
		return co_remark;
	}
	public void setCo_remark(String co_remark) {
		this.co_remark = co_remark;
	}
	public String getCo_fortor() {
		return co_fortor;
	}
	public void setCo_fortor(String co_fortor) {
		this.co_fortor = co_fortor;
	}
	@Override
	public String toString() {
		return "Cost [co_id=" + co_id + ", co_time=" + co_time + ", co_entry=" + co_entry + ", co_money=" + co_money
				+ ", co_operator=" + co_operator + ", co_fortor=" + co_fortor + ", co_union=" + co_union
				+ ", co_remark=" + co_remark + "]";
	}
	
}
