package com.by.bytest.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Capacity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	String storeNo;
	Date date;
	Double  noOfOrdersAccepted;
	public Capacity() {
		// TODO Auto-generated constructor stub
	}
	public Capacity(String storeNo,Date date,Double  noOfOrdersAccepted) {
		this.storeNo = storeNo;
		this.date =  date;
		this.noOfOrdersAccepted = noOfOrdersAccepted;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getNoOfOrdersAccepted() {
		return noOfOrdersAccepted;
	}
	public void setNoOfOrdersAccepted(Double noOfOrdersAccepted) {
		this.noOfOrdersAccepted = noOfOrdersAccepted;
	}
	
}
