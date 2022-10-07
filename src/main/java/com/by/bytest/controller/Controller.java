package com.by.bytest.controller;

import java.text.ParseException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.by.bytest.pojo.ATPRequest;
import com.by.bytest.pojo.ATPResponse;
import com.by.bytest.pojo.ProductList;
import com.by.bytest.pojo.StoreCalendarRequest;
import com.by.bytest.pojo.StoreCalendarResponse;
import com.by.bytest.service.ByService;
import com.by.bytest.utils.AppConstants;

@RestController
public class Controller {
	private ByService service;

    public Controller(ByService svc) {
    	service = svc;
    }

    /**
     * Based on the response, this method will return SUCCESS/FAILURE response
     *
     * @param request
     * @return 
     * @return
     * @throws ApiException 
     * @throws Exception
     */
    @GetMapping(value = "/getProducts")
    public  ProductList getProducts(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
    	
        return service.getProductList(pageNo, pageSize, sortBy, sortBy);
    }
    
    @GetMapping(value = "/")
    public  String Hello() {
    	
        return "Hello Blue Yonder Folks!!!";
    }
    @RequestMapping(value = "/sortProducts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/sortProducts")
    public  @ResponseBody ProductList sortProducts(@RequestBody ProductList products) {
    	
        return service.sortProductList(products);
    }
    @RequestMapping(value = "/getInvPicture", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/getInvPicture")
    public  @ResponseBody ATPResponse getProdAvailability(@RequestBody ATPRequest atpReq) throws Exception {
    	
        //return service.getProdAvailability_noExecutor(atpReq);
        return service.getProdAvailability(atpReq);
    }
    @RequestMapping(value = "/findStoreAvailability", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/findStoreAvailability")
    public  @ResponseBody StoreCalendarResponse findStoreAvailability(@RequestBody StoreCalendarRequest storeCalReq) throws ParseException {
    	
        //return service.getProdAvailability_noExecutor(atpReq);
        return service.findStoreAvailability(storeCalReq);
    }
}

