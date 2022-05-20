package com.by.bytest.service;

import java.text.ParseException;

import com.by.bytest.pojo.ATPRequest;
import com.by.bytest.pojo.ATPResponse;
import com.by.bytest.pojo.ProductList;

public interface ByService {
	ProductList getProductList(int pageNo, int pageSize, String sortBy, String sortDir);
	ProductList sortProductList(ProductList productList);
	ATPResponse getProdAvailability(ATPRequest atpReq) throws ParseException;
	ATPResponse getProdAvailability_noExecutor(ATPRequest atpReq) throws ParseException;

}
