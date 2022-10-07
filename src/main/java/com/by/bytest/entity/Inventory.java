package com.by.bytest.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String productid;
	private String productName;
	private String UOM;
	private Date availDate;
	private Double availQty;
	public Inventory(String productid, String productName, String uOM, Double availQty,Date availDate) {
		super();
		this.productid = productid;
		this.productName = productName;
		UOM = uOM;
		this.availDate = availDate;
		this.availQty = availQty;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUOM() {
		return UOM;
	}
	public void setUOM(String uOM) {
		UOM = uOM;
	}
	public Date getAvailDate() {
		return availDate;
	}
	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}
	public Double getAvailQty() {
		return availQty;
	}
	public void setAvailQty(Double availQty) {
		this.availQty = availQty;
	}  
	

}
