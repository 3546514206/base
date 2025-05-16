package com.Unionfinance2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="z_entry")
public class Entry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int en_id;
	@Column(length=8)
	private String en_type;
	@Column(length=50)
	private String en_name;
	public int getEn_id() {
		return en_id;
	}
	public void setEn_id(int en_id) {
		this.en_id = en_id;
	}
	public String getEn_type() {
		return en_type;
	}
	public void setEn_type(String en_type) {
		this.en_type = en_type;
	}
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	@Override
	public String toString() {
		return "Entry [en_id=" + en_id + ", en_type=" + en_type + ", en_name=" + en_name + "]";
	}
	
	
}
