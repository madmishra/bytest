package com.by.bytest.utils;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.by.bytest.entity.Availability;
import com.by.bytest.entity.Capacity;
import com.by.bytest.repos.AvailabilityRepository;
import com.by.bytest.repos.CapacityRepository;
@Component
public class DataLoader {
	private AvailabilityRepository ar;
	private CapacityRepository cr;

	public DataLoader(AvailabilityRepository availRepo,CapacityRepository capRepo) {
		this.ar = availRepo;
		this.cr = capRepo;
	}
	
	@PostConstruct
	public void run() throws Exception {
		ar.save(new Availability("Store001", "Prod1", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-19"), 1.0));
		ar.save(new Availability("Store001", "Prod1", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-20"), 3.0));
		ar.save(new Availability("Store001", "Prod1", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-21"), 0.0));
		cr.save(new Capacity("Store001",  new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-19"), 0.0));
		cr.save(new Capacity("Store001",  new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-20"), 2.0));
		cr.save(new Capacity("Store001",  new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-21"), 2.0));
	}

}
