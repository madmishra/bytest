package com.by.bytest.pojo;
import java.util.Date;

public class ATPResponse {
	String storeNo;
	String productId;
	String reqQty;
	String reqDate;
	String status;
	public ATPResponse(String storeNo,String productId,String reqQty,String reqDate,String status) {
		this.storeNo = storeNo;
		this.productId = productId;
		this.reqQty = reqQty;
		this.reqDate = reqDate;
		this.status = status;
	}
	public ATPResponse() {
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getReqQty() {
		return reqQty;
	}
	public void setReqQty(String reqQty) {
		this.reqQty = reqQty;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	
}
