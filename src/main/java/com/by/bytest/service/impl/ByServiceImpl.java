package com.by.bytest.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.by.bytest.entity.Inventory;
import com.by.bytest.entity.StoreCalendar;
import com.by.bytest.pojo.ATPRequest;
import com.by.bytest.pojo.ATPResponse;
import com.by.bytest.pojo.Product;
import com.by.bytest.pojo.ProductList;
import com.by.bytest.pojo.StoreCalendarRequest;
import com.by.bytest.pojo.StoreCalendarResponse;
import com.by.bytest.repos.AvailabilityRepository;
import com.by.bytest.repos.CalendarRepository;
import com.by.bytest.service.ByService;
@Service
public class ByServiceImpl implements ByService {
	@Autowired
	AvailabilityRepository availRepo;
	@Autowired
	CalendarRepository calRepo;
	@Override
	public ProductList getProductList(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
	                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        /*Page<Product> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content= listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());*/

        return null;
	}

	@Override
	public ProductList sortProductList(ProductList productList){
		Comparator<Product> ProdIdDescLaunchDateDesc = Comparator.comparing(Product::getProductId).thenComparing(Product::getLaunchDate).reversed();
		List<Product> sortedProducts = productList.getProductList().stream().sorted(ProdIdDescLaunchDateDesc).collect(Collectors.toList());
		ProductList sortedProductList = new ProductList();
		sortedProductList.setProductList(sortedProducts);
		return sortedProductList;
	}


	
	@Override
	public ATPResponse getProdAvailability(ATPRequest atpReq) throws Exception {
		String productId=atpReq.getProductId();
		
		String reqDateString=atpReq.getReqDate();
		
		Date reqFromDate = new SimpleDateFormat("yyyy-MM-dd").parse(reqDateString);
		validateDate(reqFromDate);
		Calendar c = Calendar.getInstance();
		c.setTime(reqFromDate);
		c.add(Calendar.DAY_OF_MONTH, 10);
		String sReqToDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		Date reqToDate = new SimpleDateFormat("yyyy-MM-dd").parse(sReqToDate);
		List<Inventory> inventories = availRepo.findByProductidAndAvailDateGreaterThanAndAvailDateLessThan(productId, reqFromDate, reqToDate);
		Double dSumQty = 0.0;
		if(inventories!=null) {
			for (Inventory i:inventories) {
				dSumQty+=i.getAvailQty();
			}
		}
		
		ATPResponse atpResponse = new ATPResponse(productId, atpReq.getProdName(), String.valueOf(dSumQty));
		return atpResponse;
	}

	private void validateDate(Date reqFromDate) throws Exception {
		Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-19");
		
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DAY_OF_MONTH, 10);
		String sPlusTen = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		Date PlusTen = new SimpleDateFormat("yyyy-MM-dd").parse(sPlusTen);
		c.setTime(currentDate);
		c.add(Calendar.DAY_OF_MONTH, -10);
		String sMinusTen = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		Date MinusTen = new SimpleDateFormat("yyyy-MM-dd").parse(sMinusTen);
		if(reqFromDate.before(MinusTen)||reqFromDate.after(PlusTen)){
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Please Enter a date within 10 days from now.");
		}
		
	}

	@Override
	public StoreCalendarResponse findStoreAvailability(StoreCalendarRequest storeCalReq) throws ParseException {
		// TODO Auto-generated method stub
		StoreCalendarResponse response = null;
		String storeNo=storeCalReq.getStoreID();
		String date = storeCalReq.getRequestDate();
		response = new StoreCalendarResponse(storeNo,date,"Not Available");
		Instant requestedDateTime = Instant.parse(date);
		String sDOW = requestedDateTime.atZone(ZoneId.of("UTC")).getDayOfWeek().name();
		//String sDay = DayOfWeek.of(requestedDateTime.atZone(ZoneId.of("UTC")).getDayOfWeek().getValue());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		LocalTime requestedTime = LocalTime.parse(formatter.format(requestedDateTime));
		Optional<StoreCalendar> optCal =calRepo.findByLocationIDAndDayAndCutOffTimeGreaterThan(storeNo,sDOW, requestedTime);
		if(optCal.isPresent()) {
			response.setStatus("Available");
		}
		return response;
	}

}
