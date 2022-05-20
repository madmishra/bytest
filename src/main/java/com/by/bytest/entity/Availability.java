package com.by.bytest.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Availability {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String storeNo;
	private String productId;
	private Date date;
	private Double availQty;  
	public Availability() {
		// TODO Auto-generated constructor stub
	}
	public Availability(String store, String productId,Date date,Double availQty) {
		this.storeNo = store;
		this.productId = productId;
		this.date =  date;
		this.availQty = availQty;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getAvailQty() {
		return availQty;
	}
	public void setAvailQty(Double availQty) {
		this.availQty = availQty;
	}
	

}
