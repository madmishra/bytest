package com.by.bytest.pojo;

public class StoreCalendarResponse {
	String storeID;
	String requestDate;
	String status;
	
	public StoreCalendarResponse(String storeID, String requestDate, String status) {
		super();
		this.storeID = storeID;
		this.requestDate = requestDate;
		this.status = status;
	}
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
