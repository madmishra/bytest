package com.by.bytest.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.by.bytest.entity.Availability;
import com.by.bytest.entity.Capacity;
import com.by.bytest.entity.StoreCalendar;
import com.by.bytest.pojo.ATPRequest;
import com.by.bytest.pojo.ATPResponse;
import com.by.bytest.pojo.Product;
import com.by.bytest.pojo.ProductList;
import com.by.bytest.pojo.StoreCalendarRequest;
import com.by.bytest.pojo.StoreCalendarResponse;
import com.by.bytest.repos.AvailabilityRepository;
import com.by.bytest.repos.CalendarRepository;
import com.by.bytest.repos.CapacityRepository;
import com.by.bytest.service.ByService;
@Service
public class ByServiceImpl implements ByService {
	@Autowired
	AvailabilityRepository availRepo;
	@Autowired
	CapacityRepository capRepo;
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
	public ATPResponse getProdAvailability_noExecutor(ATPRequest atpReq) throws ParseException {
		
		String storeNo=atpReq.getStoreNo();
		String productId=atpReq.getProductId();
		String reqDateString=atpReq.getReqDate();
		String reqQty = atpReq.getReqQty();
		
		Date reqDate = new SimpleDateFormat("yyyy-MM-dd").parse(reqDateString);
		
		Optional<Availability> optAvail =  availRepo.findByStoreNoAndProductIdAndDateAndAvailQtyGreaterThan(storeNo, productId ,reqDate , 0.0);
		Optional<Capacity> optCap =  capRepo.findByStoreNoAndDateAndNoOfOrdersAcceptedGreaterThan(storeNo, reqDate, 0.0);
		return generateResponse(atpReq, optAvail, optCap);
	}

	private ATPResponse generateResponse(ATPRequest atpReq, Optional<Availability> optAvail,
			Optional<Capacity> optCap) {
		ATPResponse atpResponse = null;
		if(optAvail.isPresent()) {
			if(optCap.isPresent()) {
				atpResponse = new ATPResponse(atpReq.getStoreNo(),atpReq.getProductId(), atpReq.getReqQty(), atpReq.getReqDate(), "Available");
			}else {
				atpResponse = new ATPResponse(atpReq.getStoreNo(),atpReq.getProductId(), atpReq.getReqQty(), atpReq.getReqDate(), "No Capacity");
			}
		}else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "no content to be returned.");
		}
	
		
		return atpResponse;
	}
	@Override
	public ATPResponse getProdAvailability(ATPRequest atpReq) throws ParseException {
		String storeNo=atpReq.getStoreNo();
		String productId=atpReq.getProductId();
		String reqDateString=atpReq.getReqDate();
		String reqQty = atpReq.getReqQty();
		
		Date reqDate = new SimpleDateFormat("yyyy-MM-dd").parse(reqDateString);
		ExecutorService execService = Executors.newFixedThreadPool(2);
		Future<Optional<Availability>> futureAvail = execService.submit(()->{return availRepo.findByStoreNoAndProductIdAndDateAndAvailQtyGreaterThan(storeNo, productId ,reqDate , 0.0);});
		Future<Optional<Capacity>> futureCapacity = execService.submit(()->{return capRepo.findByStoreNoAndDateAndNoOfOrdersAcceptedGreaterThan(storeNo, reqDate, 0.0);});
		ATPResponse atpResponse = null;
		try {
			Optional<Availability> optAvail = futureAvail.get();
			Optional<Capacity> optCap = futureCapacity.get();
			atpResponse = generateResponse(atpReq, optAvail, optCap);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return atpResponse;
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
