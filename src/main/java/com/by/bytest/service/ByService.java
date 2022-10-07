package com.by.bytest.service;

import java.text.ParseException;

import com.by.bytest.pojo.ATPRequest;
import com.by.bytest.pojo.ATPResponse;
import com.by.bytest.pojo.ProductList;
import com.by.bytest.pojo.StoreCalendarRequest;
import com.by.bytest.pojo.StoreCalendarResponse;

public interface ByService {
	ProductList getProductList(int pageNo, int pageSize, String sortBy, String sortDir);
	ProductList sortProductList(ProductList productList);
	ATPResponse getProdAvailability(ATPRequest atpReq) throws Exception;
	StoreCalendarResponse findStoreAvailability(StoreCalendarRequest storeCalReq) throws ParseException;

}
