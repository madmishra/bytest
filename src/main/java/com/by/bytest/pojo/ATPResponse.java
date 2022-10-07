package com.by.bytest.pojo;
import java.util.Date;

public class ATPResponse {
	String productId;
	String prodName;
	String availQty;
	public ATPResponse(String productId, String prodName, String availQty) {
		super();
		this.productId = productId;
		this.prodName = prodName;
		this.availQty = availQty;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getAvailQty() {
		return availQty;
	}
	public void setAvailQty(String availQty) {
		this.availQty = availQty;
	}
	
}
